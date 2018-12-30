package com.baifenjy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.baifenjy.utils.DBConnector;
import com.baifenjy.utils.DBUtil;
import com.baifenjy.vo.User;

public class UserDao
{

    /**
     * do login
     * @param user
     * @return
     */
    public static boolean login(User user) {
        
        Connection conn = null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn = DBConnector.getInstance().getConnection();
            ps=conn.prepareStatement("select * from user where username=? and password=?");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            rs=ps.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.release(conn, ps, rs);
        }
        return false;
    }
    
    /**
     * first register 
     * @param user
     */
    public static void register(User user) {
        
        String phone = user.getUsername();
        String password = user.getPassword();
        
        Connection conn = null;
        PreparedStatement ps=null;
        try {
            conn = DBConnector.getInstance().getConnection();
            ps = conn.prepareStatement("insert into user(username,password) values(?,?)");
            ps.setString(1, phone);
            ps.setString(2, password);
            int count = ps.executeUpdate();
            if (count == 1) {
                System.out.println("register success!");
            } else {
                System.out.println("register failed!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.release(conn, ps, null);
        }
        
    }
}
