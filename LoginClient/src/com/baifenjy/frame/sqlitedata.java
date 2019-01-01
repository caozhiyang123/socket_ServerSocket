package com.baifenjy.frame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.omg.CORBA.PRIVATE_MEMBER;

public class sqlitedata {
    public  Vector rowData, columnName;  
    
    public void mainjj() {
        Connection c = null;
        Statement stmt = null;

        rowData = new Vector();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:d:\\web\\data.data\\");//tim.data  
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Meter;");//COMPANY
            ResultSetMetaData data = rs.getMetaData();
            
            columnName = new Vector();  
            System.out.println("dd");
            
            for (int i = 1; i <= data.getColumnCount(); i++) {
                columnName.add(data.getColumnName(i));//这里是列名
            }
            
            while (rs.next()) {

                    Vector line1 = new Vector();
                    for (int k = 1; k <= data.getColumnCount(); k++) {

                        line1.add(rs.getString(data.getColumnName(k)));//这里是添加行数据
                    }
                    rowData.add(line1);
                }
                    
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        
    }

}