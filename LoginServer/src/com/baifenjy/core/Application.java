package com.baifenjy.core;

import org.apache.log4j.PropertyConfigurator;

import com.baifenjy.controller.ConfigController;
import com.baifenjy.server.LoginServer;
import com.baifenjy.server.OrderServer;

public class Application
{
    private static ConfigController configController;
    private static LoginServer loginServer;
    private static OrderServer orderServer;
    
    public static final String CONFIG_DIR = "config/";
    
    public static void main(String[] args)
    {
        System.setProperty("WORKDIR", "logs");
        PropertyConfigurator.configure(CONFIG_DIR + "log4j.properties");
        
        configController = new ConfigController();
//        loginServer = new LoginServer();
        orderServer = new OrderServer();
    }
}
