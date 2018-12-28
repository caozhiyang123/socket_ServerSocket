package com.baifenjy.dao;

public class DaoFactory
{
    public static UserDao getUserDao(){ return new UserDao();}
    public static OrderDao getOrderDao(){ return new OrderDao();}
}
