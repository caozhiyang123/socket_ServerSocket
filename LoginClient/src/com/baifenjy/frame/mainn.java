package com.baifenjy.frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class mainn extends JFrame {
    //private Vector rowData, columnName;
    private JTable jt = null;
    private JScrollPane jsp = null;
    private JButton jb = null;

    public static void main(String[] args) {
        // 显示应用 GUI
        mainn miann1 = new mainn();
    }
    
    public mainn()
    {
        sqlitedata a = new sqlitedata();
         //初始化JTable  
        a.mainjj();
                 
        jt = new JTable(a.rowData, a.columnName);  
          
        jsp = new JScrollPane(jt);  
        
        this.add(jsp);  
        this.setTitle("by:Zing庄     SqlliteConnect");  
        this.setSize(460, 200);  //这是大小
        this.setLocation(300, 200);  //这是位置
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setResizable(true);  //尺寸是否可变
        this.setVisible(true);  //显示与隐藏
    }
    
}