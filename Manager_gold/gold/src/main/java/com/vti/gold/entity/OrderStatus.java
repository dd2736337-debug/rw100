package com.vti.gold.entity;

public enum OrderStatus {

    PENDING,     // Chờ xác nhận

    CONFIRMED,   // Admin đã xác nhận

    SHIPPING,    // Đang giao

    SUCCESS,     // Hoàn thành

    CANCEL       // Hủy đơn
}