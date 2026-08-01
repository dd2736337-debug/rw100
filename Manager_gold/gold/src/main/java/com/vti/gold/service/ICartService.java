package com.vti.gold.service;

import com.vti.gold.dto.CartDTO;

import java.util.List;

public interface ICartService {

    List<CartDTO> getCart(Integer userId);

    CartDTO addToCart(Integer userId, Integer goldId, Integer quantity);

    CartDTO updateQuantity(Integer userId, Integer goldId, Integer quantity);

    void removeItem(Integer userId, Integer goldId);

    void clearCart(Integer userId);
}