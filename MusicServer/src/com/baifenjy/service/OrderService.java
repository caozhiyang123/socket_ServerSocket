package com.baifenjy.service;

import java.util.Map;

import com.baifenjy.vo.Order;

public interface OrderService
{

    boolean addNewOrder(Order order);

    boolean updateOrder(Order order);

    Order queryByOrderId(String orderId);

    Map<String, Order> pageQuery(int pageNum, int pageSize);

    int queryTotalRows();

    boolean saveOrUpdate(Order order);

}
