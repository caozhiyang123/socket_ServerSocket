package com.baifenjy.frame;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class AnalyseDataFrame extends JFrame{
    public static void main(String[] args) {
        new AnalyseDataFrame();
    }
    
    public AnalyseDataFrame()
    {
        this.setTitle("DemoScrollPane");
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setSize(20, 30);
        //设置水平滚动条一直显示
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        //设置垂直滚动条需要时显示
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        //定义一个JPanel面板
        JPanel panel = new JPanel();
        panel.setLayout(null);
        Panel panel2 = new Panel();
        Panel panel3 = new Panel();
        panel.add(panel2);
        panel2.setBounds(0, 0, 500, 500);
        panel2.add(new JButton("按钮1"));
        panel2.add(new JButton("按钮2"));
        panel2.add(new JButton("按钮3"));
        panel2.add(new JButton("按钮4"));
        panel2.add(new JButton("按钮5"));
        panel2.add(new JButton("按钮6"));
        panel2.add(new JButton("按钮7"));
        panel2.add(new JButton("按钮8"));
        panel2.add(new JButton("按钮8"));
        panel2.add(new JButton("按钮8"));
        panel2.add(new JButton("按钮8"));
        panel2.add(new JButton("按钮8"));
        panel2.add(new JButton("按钮8"));
        panel2.add(new JButton("按钮8"));
        panel2.add(new JButton("按钮8"));
        panel2.add(new JButton("按钮8"));
        panel2.add(new JButton("按钮8"));
        panel2.add(new JButton("按钮8"));
        panel2.add(new JButton("按钮8"));
        panel2.add(new JButton("按钮8"));
        panel2.add(new JButton("按钮8"));
        
        panel.add(panel3);
        panel3.setBounds(500, 0, 500, 500);
        panel3.add(new JButton("按钮11"));
        panel3.add(new JButton("按钮12"));
        panel3.add(new JButton("按钮13"));
        panel3.add(new JButton("按钮14"));
        panel3.add(new JButton("按钮15"));
        panel3.add(new JButton("按钮16"));
        panel3.add(new JButton("按钮17"));
        panel3.add(new JButton("按钮18"));
        panel3.add(new JButton("按钮12"));
        panel3.add(new JButton("按钮13"));
        panel3.add(new JButton("按钮14"));
        panel3.add(new JButton("按钮15"));
        panel3.add(new JButton("按钮16"));
        panel3.add(new JButton("按钮17"));
        panel3.add(new JButton("按钮18"));
        panel3.add(new JButton("按钮12"));
        panel3.add(new JButton("按钮13"));
        panel3.add(new JButton("按钮14"));
        panel3.add(new JButton("按钮15"));
        panel3.add(new JButton("按钮16"));
        panel3.add(new JButton("按钮17"));
        panel3.add(new JButton("按钮18"));
        panel3.add(new JButton("按钮12"));
        panel3.add(new JButton("按钮13"));
        panel3.add(new JButton("按钮14"));
        panel3.add(new JButton("按钮15"));
        panel3.add(new JButton("按钮16"));
        panel3.add(new JButton("按钮17"));
        panel3.add(new JButton("按钮18"));
        
        jScrollPane.setViewportView(panel);
        this.add(jScrollPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,250);
        this.setVisible(true);
    }
}
