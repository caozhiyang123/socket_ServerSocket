package com.baifenjy.utils;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class Center {
    public static Point getPoint(int width,int height) {
        Toolkit toolkit=Toolkit.getDefaultToolkit();//应该是单例
        int screenW=toolkit.getScreenSize().width;
        int screenH=toolkit.getScreenSize().height;
        int x=(screenW-width)/2;
        int y=(screenH-height)/2;
        Point p=new Point(x,y);
        return p;
    }
    //函数的重载，参数是包装类尺寸——Dimension
    public static Point getPoint(Dimension d) {
        Point p=getPoint(d.width,d.height);
        return p;
    }
}