package com.vti.gold.service.Impl;

import com.vti.gold.dto.CartDTO;
import com.vti.gold.entity.Cart;
import com.vti.gold.entity.Gold;
import com.vti.gold.entity.User;
import com.vti.gold.repository.CartRepository;
import com.vti.gold.repository.GoldRepository;
import com.vti.gold.repository.UserRepository;
import com.vti.gold.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class CartServiceImpl implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GoldRepository goldRepository;


    @Override
    public List<CartDTO> getCart(Integer userId) {

        return cartRepository.findByUser_Id(userId).stream().map(this::convertDTO).collect(Collectors.toList());
    }

    @Override
    public CartDTO addToCart(Integer userId, Integer goldId, Integer quantity) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User không tồn tại"));

        Gold gold = goldRepository.findById(goldId).orElseThrow(() -> new RuntimeException("Gold không tồn tại"));

        Cart cart = cartRepository.findByUser_IdAndGold_Id(userId, goldId).orElse(new Cart());

        cart.setUser(user);

        cart.setGold(gold);

        if (cart.getQuantity() == null) {

            cart.setQuantity(quantity);

        } else {

            cart.setQuantity(cart.getQuantity() + quantity);

        }

        cartRepository.save(cart);

        return convertDTO(cart);
    }

    @Override
    public CartDTO updateQuantity(Integer userId, Integer goldId, Integer quantity) {

        Cart cart = cartRepository.findByUser_IdAndGold_Id(userId, goldId).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        cart.setQuantity(quantity);

        cartRepository.save(cart);

        return convertDTO(cart);
    }

    @Override
    public void removeItem(Integer userId, Integer goldId) {

        cartRepository.deleteByUser_IdAndGold_Id(userId, goldId);
    }

    @Override
    public void clearCart(Integer userId) {

        cartRepository.deleteAllByUser_Id(userId);
    }

    private CartDTO convertDTO(Cart cart) {

        CartDTO dto = new CartDTO();

        dto.setCartId(cart.getCartId());
        dto.setGoldId(cart.getGold().getId());
        dto.setGoldName(cart.getGold().getName());
        dto.setType(cart.getGold().getType());
        dto.setWeight(cart.getGold().getWeight());
        dto.setPrice(cart.getGold().getPrice());
        dto.setImage(cart.getGold().getImage());
        dto.setQuantity(cart.getQuantity());
        dto.setStock(cart.getGold().getQuantity());

        return dto;
    }
}