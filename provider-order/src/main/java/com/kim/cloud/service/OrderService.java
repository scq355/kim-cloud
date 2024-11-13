package com.kim.cloud.service;

import com.kim.cloud.entities.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);

}
