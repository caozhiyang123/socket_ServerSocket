package com.baifenjy.service;

import java.util.Date;
import java.util.Map;

import com.baifenjy.dao.OrderDao;
import com.baifenjy.utils.ThreadLocalSimple;
import com.baifenjy.vo.Order;

public class OrderServiceImpl implements OrderService
{
    private static OrderDao orderDao = new OrderDao();
    
    @Override
    public boolean saveOrUpdate(Order order){
        Order orderOld = queryByOrderId(order.getOrderId());
        if(orderOld == null){
            return addNewOrder(order);
        }else{
            if(orderOld.getUpdated() == order.getUpdated()){
                order.setUpdated(order.getUpdated()+1);
                return updateOrder(order);
            }else{
                return false;
            }
        }
    }
    
    @Override
    public boolean addNewOrder(Order order){
        String time = ThreadLocalSimple.df.get().format(new Date());
        order.setCreateAt(time);
        order.setUpdateAt(time);
        order.setUpdated(0);
        return orderDao.addNewOrder(order);
    }
    
    @Override
    public boolean updateOrder(Order order){
        order.setUpdateAt(ThreadLocalSimple.df.get().format(new Date()));
        return orderDao.updateOrder(order);
    }
    
    @Override
    public Order queryByOrderId(String orderId){
        Order order = orderDao.queryByOrderId(orderId);
        initParamters(order);
       return  order;
    }
    
    private void initParamters(Order order) {
        if(order == null){
            return ;
        }
        order.setOrderItem(order.toString());
    }

    @Override
    public Map<String,Order> pageQuery(int pageNum,int pageSize){
        return orderDao.pageQuery(pageNum,(pageSize-1)*pageNum);
    }
    
    @Override
    public int queryTotalRows(){
        return orderDao.queryTotalRows();
    }
}
