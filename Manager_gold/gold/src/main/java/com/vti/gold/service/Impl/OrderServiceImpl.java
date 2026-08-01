package com.vti.gold.service.Impl;

import com.vti.gold.Specification.OrderSpecification;
import com.vti.gold.dto.OrderDTO;
import com.vti.gold.dto.OrderDetailDTO;
import com.vti.gold.entity.*;
import com.vti.gold.form.OrderCreateForm;
import com.vti.gold.form.OrderDetailCreateForm;
import com.vti.gold.form.OrderUpdateForm;
import com.vti.gold.repository.GoldRepository;
import com.vti.gold.repository.OrderRepository;
import com.vti.gold.repository.UserRepository;
import com.vti.gold.service.IOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {


    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private GoldRepository goldRepository;


    @Autowired
    private ModelMapper modelMapper;


    @Transactional(readOnly = true)
    @Override
    public Page<OrderDTO> findAll(String username, OrderStatus status, Pageable pageable) {

        Specification<Order> specification = Specification.allOf(OrderSpecification.hasUsername(username), OrderSpecification.hasStatus(status));

        Page<Order> page = orderRepository.findAll(specification, pageable);

        return page.map(order -> {

            OrderDTO dto = modelMapper.map(order, OrderDTO.class);

            mapUser(order, dto);

            mapOrderDetails(order, dto);

            return dto;
        });
    }


    @Transactional(readOnly = true)
    @Override
    public Page<OrderDTO> findByUserId(Integer userId, Pageable pageable) {

        Page<Order> page = orderRepository.findByUser_Id(userId, pageable);

        return page.map(order -> {

            OrderDTO dto = modelMapper.map(order, OrderDTO.class);

            mapUser(order, dto);

            mapOrderDetails(order, dto);

            return dto;
        });
    }


    @Transactional(readOnly = true)
    @Override
    public OrderDTO findById(Integer id) {


        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found!"));


        OrderDTO dto = modelMapper.map(order, OrderDTO.class);


        mapUser(order, dto);


        mapOrderDetails(order, dto);


        return dto;
    }

    @Override
    @Transactional
    public OrderDTO create(OrderCreateForm form) {
        if (form == null) {
            throw new RuntimeException("Dữ liệu đơn hàng không hợp lệ.");
        }
        if (form.getUserId() == null) {
            throw new RuntimeException("User không hợp lệ.");
        }

        // Kiểm tra User
        User user = userRepository.findById(form.getUserId()).orElseThrow(() -> new RuntimeException("User not found!"));

        // Kiểm tra danh sách sản phẩm
        if (form.getOrderDetails() == null || form.getOrderDetails().isEmpty()) {
            throw new RuntimeException("Đơn hàng phải có ít nhất 1 sản phẩm.");
        }


        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setStockReduced(false);

        BigDecimal totalPrice = BigDecimal.ZERO;

        for (OrderDetailCreateForm detailForm : form.getOrderDetails()) {

            // Kiểm tra số lượng
            if (detailForm.getQuantity() == null || detailForm.getQuantity() <= 0) {
                throw new RuntimeException("Số lượng phải lớn hơn 0.");
            }

            // Kiểm tra sản phẩm
            Gold gold = goldRepository.findById(detailForm.getGoldId()).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với id = " + detailForm.getGoldId()));

            // Kiểm tra tồn kho
            if (gold.getQuantity() < detailForm.getQuantity()) {
                throw new RuntimeException("Sản phẩm \"" + gold.getName() + "\" chỉ còn " + gold.getQuantity() + " sản phẩm trong kho.");
            }

            // Tạo chi tiết đơn
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setGold(gold);
            orderDetail.setQuantity(detailForm.getQuantity());
            orderDetail.setPrice(gold.getPrice());

            order.addOrderDetail(orderDetail);

            // Cộng tiền
            totalPrice = totalPrice.add(gold.getPrice().multiply(BigDecimal.valueOf(detailForm.getQuantity())));
        }

        // Kiểm tra tổng tiền
        if (totalPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Tổng tiền phải lớn hơn 0.");
        }

        order.setTotalPrice(totalPrice);

        // Lưu
        Order savedOrder = orderRepository.save(order);

        // Mapping DTO
        OrderDTO dto = modelMapper.map(savedOrder, OrderDTO.class);

        mapUser(savedOrder, dto);
        mapOrderDetails(savedOrder, dto);

        return dto;
    }


    @Override
    @Transactional
    public OrderDTO update(Integer id, OrderUpdateForm form) {
        if (form == null) {
            throw new RuntimeException("Dữ liệu cập nhật không hợp lệ.");
        }

        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found!"));

        OrderStatus oldStatus = order.getStatus();
        OrderStatus newStatus = form.getStatus();


        if (newStatus == null) {
            throw new RuntimeException("Trạng thái đơn hàng không được để trống.");
        }

        // Không thay đổi trạng thái
        if (oldStatus == newStatus) {

            OrderDTO dto = modelMapper.map(order, OrderDTO.class);

            mapUser(order, dto);
            mapOrderDetails(order, dto);

            return dto;
        }

        // Đã hủy thì không được cập nhật nữa
        if (oldStatus == OrderStatus.CANCEL) {
            throw new RuntimeException("Đơn hàng đã hủy.");
        }

        // Đã hoàn thành thì không được cập nhật nữa
        if (oldStatus == OrderStatus.SUCCESS) {
            throw new RuntimeException("Đơn hàng đã hoàn thành.");
        }

        // ==========================
        // Kiểm tra luồng chuyển trạng thái
        // ==========================

        switch (oldStatus) {

            case PENDING -> {

                if (newStatus != OrderStatus.CONFIRMED && newStatus != OrderStatus.CANCEL) {

                    throw new RuntimeException("Đơn PENDING chỉ được chuyển sang CONFIRMED hoặc CANCEL.");
                }
            }

            case CONFIRMED -> {

                if (newStatus != OrderStatus.SHIPPING && newStatus != OrderStatus.CANCEL) {

                    throw new RuntimeException("Đơn CONFIRMED chỉ được chuyển sang SHIPPING hoặc CANCEL.");
                }
            }

            case SHIPPING -> {

                if (newStatus != OrderStatus.SUCCESS && newStatus != OrderStatus.CANCEL) {

                    throw new RuntimeException("Đơn SHIPPING chỉ được chuyển sang SUCCESS hoặc CANCEL.");
                }
            }

            default -> {
            }
        }

        // ==========================
        // CONFIRMED
        // ==========================
        if (newStatus == OrderStatus.CONFIRMED) {

            if (order.getConfirmedAt() == null) {

                order.setConfirmedAt(java.time.LocalDateTime.now());

            }
        }

        // ==========================
        // SHIPPING
        // ==========================
        if (newStatus == OrderStatus.SHIPPING) {

            if (order.getShippingAt() == null) {

                order.setShippingAt(java.time.LocalDateTime.now());

            }
        }

        // ==========================
        // SUCCESS
        // ==========================
        if (newStatus == OrderStatus.SUCCESS) {

            // Chỉ trừ kho một lần
            if (!Boolean.TRUE.equals(order.getStockReduced())) {

                reduceStock(order);

            }

            if (order.getCompletedAt() == null) {

                order.setCompletedAt(java.time.LocalDateTime.now());

            }
        }

        // ==========================
        // CANCEL
        // ==========================
        if (newStatus == OrderStatus.CANCEL) {

            // Nếu đã trừ kho thì hoàn lại
            if (Boolean.TRUE.equals(order.getStockReduced())) {

                restoreStock(order);

            }

            if (order.getCancelledAt() == null) {

                order.setCancelledAt(java.time.LocalDateTime.now());

            }
        }

        order.setStatus(newStatus);

        Order saved = orderRepository.save(order);

        OrderDTO dto = modelMapper.map(saved, OrderDTO.class);

        mapUser(saved, dto);

        mapOrderDetails(saved, dto);

        return dto;
    }


    @Override
    @Transactional
    public void delete(Integer id) {

        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found!"));

        // Chỉ cho phép xóa đơn PENDING hoặc CANCEL
        if (order.getStatus() != OrderStatus.PENDING && order.getStatus() != OrderStatus.CANCEL) {

            throw new RuntimeException("Chỉ được xóa đơn PENDING hoặc CANCEL.");
        }


        // Nếu đơn đã từng trừ kho thì hoàn kho trước khi xóa
        if (Boolean.TRUE.equals(order.getStockReduced())) {

            restoreStock(order);

        }

        orderRepository.delete(order);
    }


    private void mapUser(Order order, OrderDTO dto) {

        if (order.getUser() != null) {

            dto.setUserId(order.getUser().getId());

            dto.setUsername(order.getUser().getUsername());

            dto.setFullName(order.getUser().getFullName());
        }
    }


    private void mapOrderDetails(Order order, OrderDTO dto) {

        if (order.getOrderDetails() == null) {
            dto.setOrderDetails(List.of());
            return;
        }

        List<OrderDetailDTO> details = order.getOrderDetails().stream().map(detail -> {

            OrderDetailDTO detailDTO = new OrderDetailDTO();

            detailDTO.setId(detail.getId());

            detailDTO.setQuantity(detail.getQuantity());

            detailDTO.setPrice(detail.getPrice());

            if (detail.getGold() != null) {

                detailDTO.setGoldId(detail.getGold().getId());

                detailDTO.setGoldName(detail.getGold().getName());

                detailDTO.setGoldImage(detail.getGold().getImage());
            }

            return detailDTO;

        }).toList();

        dto.setOrderDetails(details);
    }

    private void reduceStock(Order order) {

        for (OrderDetail detail : order.getOrderDetails()) {

            Gold gold = detail.getGold();

            if (gold == null) {
                throw new RuntimeException("Sản phẩm không tồn tại.");
            }

            if (gold.getQuantity() < detail.getQuantity()) {
                throw new RuntimeException("Sản phẩm " + gold.getName() + " không đủ tồn kho!");
            }

            gold.setQuantity(gold.getQuantity() - detail.getQuantity());

            goldRepository.save(gold);
        }

        order.setStockReduced(true);
    }

    private void restoreStock(Order order) {

        for (OrderDetail detail : order.getOrderDetails()) {

            Gold gold = detail.getGold();
            if (gold == null) {
                throw new RuntimeException("Sản phẩm không tồn tại.");
            }

            gold.setQuantity(gold.getQuantity() + detail.getQuantity());

            goldRepository.save(gold);
        }

        order.setStockReduced(false);
    }

}