package com.baifenjy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baifenjy.utils.DBConnector;
import com.baifenjy.vo.TeacherAndOrder;

public class TeacherAndOrderDao {
    private static final String TEC_ORDER_TB = "tec_order_tb";
    private static final String TEC_ID = "tec_id";
    private static final String ORD_ID = "ord_id";
    private static final String START_TEACH_TIME = "start_teach_time";
    private static final String IS_PARENT_PAYED = "is_parent_payed";
    private static final String parent_cost = "parent_cost";
    private static final String IS_TEACHER_PAYED = "is_teacher_payed";
    private static final String TEACHER_COST = "teacher_cost";
    private static final String CREATE_AT = "create_at";
    private static final String UPDATE_AT = "update_at";
    private static final String UPDATED = "updated";
    private static final String USED = "used";
    
    
    
    public List<TeacherAndOrder> queryByOrderId(String orderId){
        List<TeacherAndOrder> tecOrds = new ArrayList<TeacherAndOrder>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            conn = DBConnector.getInstance().getConnection();
            String sql = String.format("select * from %s where %s = ?", TEC_ORDER_TB,ORD_ID);
            pst = conn.prepareStatement(sql);
            pst.setString(1, orderId);
            rst = pst.executeQuery();
            while(rst.next()){
                TeacherAndOrder tecOrd = new TeacherAndOrder();
                tecOrd.setOrderId(rst.getString(ORD_ID));
                tecOrd.setTeacherId(rst.getLong(TEC_ID));
                tecOrds.add(tecOrd);
            }
            return tecOrds;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBConnector.getInstance().release(conn, pst, rst);
        }
        return tecOrds;
    }

    public List<String> queryOrderIdsByTecId(long tecId) {

        List<String> orderIds = new ArrayList<String>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            conn = DBConnector.getInstance().getConnection();
            String sql = String.format("select * from %s where %s = ?", TEC_ORDER_TB,TEC_ID);
            pst = conn.prepareStatement(sql);
            pst.setLong(1, tecId);
            rst = pst.executeQuery();
            while(rst.next()){
                TeacherAndOrder tecOrd = new TeacherAndOrder();
                orderIds.add(rst.getString(ORD_ID));
            }
            return orderIds;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBConnector.getInstance().release(conn, pst, rst);
        }
        return orderIds;
    
    }
}
