package com.vti.gold.controller;

import com.vti.gold.dto.CartDTO;
import com.vti.gold.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private ICartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartDTO>> getCart(
            @PathVariable Integer userId) {

        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<CartDTO> addToCart(
            @RequestParam Integer userId,
            @RequestParam Integer goldId,
            @RequestParam Integer quantity) {

        return ResponseEntity.ok(
                cartService.addToCart(userId, goldId, quantity)
        );
    }

    @PutMapping("/update")
    public ResponseEntity<CartDTO> updateQuantity(
            @RequestParam Integer userId,
            @RequestParam Integer goldId,
            @RequestParam Integer quantity) {

        return ResponseEntity.ok(
                cartService.updateQuantity(userId, goldId, quantity)
        );
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeItem(
            @RequestParam Integer userId,
            @RequestParam Integer goldId) {

        cartService.removeItem(userId, goldId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<Void> clearCart(
            @PathVariable Integer userId) {

        cartService.clearCart(userId);

        return ResponseEntity.ok().build();
    }
}