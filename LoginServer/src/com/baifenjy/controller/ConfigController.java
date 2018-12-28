package com.baifenjy.controller;

import com.baifenjy.utils.ConfigConnector;

public class ConfigController
{
    public ConfigController()
    {
         ConfigConnector configConnector = ConfigConnector.getInstance();
         configConnector.initConfig();
    }
}
