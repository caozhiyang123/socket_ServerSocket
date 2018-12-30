package com.baifenjy.core;


import org.apache.log4j.PropertyConfigurator;

import com.baifenjy.controller.ConfigController;
import com.baifenjy.controller.LoginController;
import com.baifenjy.controller.OrderController;
import com.baifenjy.controller.TeacherController;

public class Application
{
    private static ConfigController configController;
    private static LoginController loginServer;
    private static OrderController orderServer;
    private static TeacherController teacherServer;
    
    public static final String CONFIG_DIR = "config/";
    
    public static void main(String[] args)
    {
        System.setProperty("WORKDIR", "logs");
        PropertyConfigurator.configure(CONFIG_DIR + "log4j.properties");
        
        configController = new ConfigController();
//        loginServer = new LoginServer();
//        orderServer = new OrderController();
        teacherServer = new TeacherController();
    }
}
