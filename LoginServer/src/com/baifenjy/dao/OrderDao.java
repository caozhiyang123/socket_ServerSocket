package com.baifenjy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.baifenjy.utils.DBConnector;
import com.baifenjy.vo.Order;

public class OrderDao
{
    private static final String TBNAME = "order_edu";
    private static final String ORDER_ID = "order_id";
    private static final String STUDENT_NAME = "student_name";
    private static final String STUDENT_AGE = "student_age";
    private static final String STUDENT_SEX = "student_sex";
    private static final String STUDENT_GRADE = "student_grade";
    private static final String STUDENT_SUBJECT = "student_subject";
    private static final String ADDRESS = "address";
    private static final String OTHER_IMPORTANTS = "other_importants";
    private static final String COST = "cost";
    private static final String PARENTS_NAME = "parents_name";
    private static final String PHONE_NUM = "phone_num";
    private static final String QQ_NUM = "qq_num";
    private static final String WE_CHAT_NUM = "we_chat_num";
    private static final String MESSAGE_RESOURCE = "message_resource";
    private static final String CREATE_AT = "create_at";
    private static final String UPDATE_AT = "update_at";
    

    public boolean addNewOrder(Order order)
    {
        Connection conn = null;
        PreparedStatement pst = null;
        try
        {
            conn = DBConnector.getInstance().getConnection();
            String sql = String.format("insert into %s(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",TBNAME,ORDER_ID,STUDENT_NAME,STUDENT_AGE
                    ,STUDENT_SEX,STUDENT_GRADE,STUDENT_SUBJECT,ADDRESS,OTHER_IMPORTANTS,COST,PARENTS_NAME
                    ,PHONE_NUM,QQ_NUM,WE_CHAT_NUM,MESSAGE_RESOURCE,CREATE_AT,UPDATE_AT);
            pst = conn.prepareStatement(sql);
            pst.setString(1, order.getOrderId());
            pst.setString(2, order.getStudentName());
            pst.setInt(3, order.getStudentAge());
            pst.setInt(4, order.getStudentSex());
            pst.setString(5, order.getStudentGrade());
            pst.setString(6, order.getStudentSubject());
            pst.setString(7, order.getAddress());
            pst.setString(8, order.getOtherImportants());
            pst.setString(9, order.getCost());
            pst.setString(10, order.getParentsName());
            pst.setString(11, order.getPhoneNum());
            pst.setString(12, order.getQqNum());
            pst.setString(13, order.getWeChatNum());
            pst.setString(14, order.getMessageResource());
            pst.setString(15, order.getCreateAt());
            pst.setString(16,order.getUpdateAt());
            return pst.executeUpdate() > 0;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }finally{
            DBConnector.getInstance().release(conn, pst, null);
        }
        return false;
    }


    public boolean updateOrder(Order order)
    {
        Connection conn = null;
        PreparedStatement pst = null;
        try
        {
            conn = DBConnector.getInstance().getConnection();
            String sql = String.format("update %s set %s = ? ,%s = ?,%s = ?,%s = ?,%s = ?,%s = ?"
                    + ",%s = ?,%s = ?,%s = ?,%s = ?,%s = ?,%s = ?,%s = ?,%s = ? where %s = ?"
                    ,TBNAME,STUDENT_NAME,STUDENT_AGE,STUDENT_SEX,STUDENT_GRADE,STUDENT_SUBJECT
                    ,ADDRESS,OTHER_IMPORTANTS,COST,PARENTS_NAME,PHONE_NUM,QQ_NUM,WE_CHAT_NUM
                    ,MESSAGE_RESOURCE,UPDATE_AT,ORDER_ID);
            pst = conn.prepareStatement(sql);
            pst.setString(1, order.getStudentName());
            pst.setInt(2, order.getStudentAge());
            pst.setInt(3, order.getStudentSex());
            pst.setString(4, order.getStudentGrade());
            pst.setString(5, order.getStudentSubject());
            pst.setString(6, order.getAddress());
            pst.setString(7, order.getOtherImportants());
            pst.setString(8, order.getCost());
            pst.setString(9, order.getParentsName());
            pst.setString(10, order.getPhoneNum());
            pst.setString(11, order.getQqNum());
            pst.setString(12, order.getWeChatNum());
            pst.setString(13, order.getMessageResource());
            pst.setString(14, order.getUpdateAt());
            pst.setString(15, order.getOrderId());
            return pst.executeUpdate() > 0;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }finally{
            DBConnector.getInstance().release(conn, pst, null);
        }
        return false;
    }


    public Order queryByOrderId(String orderId)
    {
        Order order = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try
        {
            conn = DBConnector.getInstance().getConnection();
            String sql = String.format("select * from %s where %s = ?",TBNAME,ORDER_ID);
            pst = conn.prepareStatement(sql);
            pst.setString(1, orderId);
            rst = pst.executeQuery();
            while(rst.next()){
                order = new Order();
                order.setOrderId(orderId);
                order.setStudentName(rst.getString(STUDENT_NAME));
                order.setStudentAge(rst.getInt(STUDENT_AGE));
                order.setStudentGrade(rst.getString(STUDENT_GRADE));
                order.setStudentSex(rst.getInt(STUDENT_SEX));
                order.setStudentSubject(rst.getString(STUDENT_SUBJECT));
                order.setAddress(rst.getString(ADDRESS));
                order.setOtherImportants(rst.getString(OTHER_IMPORTANTS));
                order.setCost(rst.getString(COST));
                order.setParentsName(rst.getString(PARENTS_NAME));
                order.setPhoneNum(rst.getString(PHONE_NUM));
                order.setQqNum(rst.getString(QQ_NUM));
                order.setWeChatNum(rst.getString(WE_CHAT_NUM));
                order.setCreateAt(rst.getString(CREATE_AT));
                order.setUpdateAt(rst.getString(UPDATE_AT));
                order.setMessageResource(rst.getString(MESSAGE_RESOURCE));
            }
            return order;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DBConnector.getInstance().release(conn, pst, rst);
        }
        return order;
    }


    public Map<String, Order> pageQuery(int begin, int end)
    {
        Map<String, Order> orderMap = new HashMap<String,Order>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try
        {
            conn = DBConnector.getInstance().getConnection();
            String sql = String.format("select * from %s limit ?,? ",TBNAME);
            pst = conn.prepareStatement(sql);
            pst.setInt(1, begin);
            pst.setInt(2, end);
            rst = pst.executeQuery();
            while(rst.next()){
                Order order = new Order();
                order.setOrderId(rst.getString(ORDER_ID));
                order.setStudentName(rst.getString(STUDENT_NAME));
                order.setStudentAge(rst.getInt(STUDENT_AGE));
                order.setStudentGrade(rst.getString(STUDENT_GRADE));
                order.setStudentSex(rst.getInt(rst.getInt(STUDENT_SEX)));
                order.setStudentSubject(rst.getString(STUDENT_SUBJECT));
                order.setAddress(rst.getString(ADDRESS));
                order.setOtherImportants(rst.getString(OTHER_IMPORTANTS));
                order.setCost(rst.getString(COST));
                order.setParentsName(rst.getString(PARENTS_NAME));
                order.setPhoneNum(rst.getString(PHONE_NUM));
                order.setQqNum(rst.getString(QQ_NUM));
                order.setWeChatNum(rst.getString(WE_CHAT_NUM));
                order.setCreateAt(rst.getString(CREATE_AT));
                order.setUpdateAt(rst.getString(UPDATE_AT));
                order.setMessageResource(rst.getString(MESSAGE_RESOURCE));
                orderMap.put(order.getOrderId(), order);
            }
            return orderMap;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DBConnector.getInstance().release(conn, pst, rst);
        }
        return orderMap;
    }


    public int queryTotalRows()
    {
        int totalRows = 0;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try
        {
            conn = DBConnector.getInstance().getConnection();
            String sql = String.format("select count(*) from %s",TBNAME);
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            while(rst.next()){
                totalRows = rst.getInt(1);
            }
            return totalRows;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DBConnector.getInstance().release(conn, pst, rst);
        }
        return totalRows;
    }
    
}
