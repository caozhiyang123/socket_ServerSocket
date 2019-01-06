package com.baifenjy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import com.baifenjy.utils.DBConnector;
import com.baifenjy.vo.Message;
import com.baifenjy.vo.MessageVO;

public class MessageDao {
    
    private static final String MESSAGE_TB = "message_tb";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String MESSAGE = "message";
    private static final String CALL_MESSAGE = "call_message";
    private static final String TIME = "time";

    public Message pageQuery(int beginIndex, int length) {
        Message message = new Message();
        Vector rowDatas = new Vector();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            conn = DBConnector.getInstance().getConnection();
            String sql = String.format("select * from %s limit ?,? order by %s desc", MESSAGE_TB,TIME);
            pst = conn.prepareStatement(sql);
            pst.setInt(1, beginIndex);
            pst.setInt(2, length);
            rst = pst.executeQuery();
            while(rst.next()){
                Vector rowData = new Vector();
                rowData.add(rst.getLong(ID));
                rowData.add(rst.getString(NAME));
                rowData.add(rst.getString(MESSAGE));
                rowData.add(rst.getString(CALL_MESSAGE));
                rowData.add(rst.getString(TIME));
                rowDatas.add(rowData);
            }
            message.setRowData(rowDatas);
            
            
            Vector columnName = new Vector();
            ResultSetMetaData metaData = rst.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columnName.add(metaData.getColumnName(i));//这里是列名
            }
            message.setColumnName(columnName);
            return message;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBConnector.getInstance().release(conn, pst, rst);
        }
        
        return message;
    }

    public int queryRowCount() {
        int rowCount = 0;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            conn = DBConnector.getInstance().getConnection();
            String sql = String.format("select count(*) from %s");
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            while(rst.next()){
                rowCount = rst.getInt(0);
            }
            return rowCount;
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBConnector.getInstance().release(conn, pst, rst);
        }
        return rowCount;
    }

    public boolean updateMessageById(MessageVO messageVO) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            conn = DBConnector.getInstance().getConnection();
            String sql = String.format("update %s set %s=?,%s=?,%s=?,%s=? where %s = ?", 
                    MESSAGE_TB,NAME,MESSAGE,CALL_MESSAGE,TIME,ID);
            pst = conn.prepareStatement(sql);
            pst.setString(1, messageVO.getName());
            pst.setString(2, messageVO.getMessage());
            pst.setString(3, messageVO.getCallMessage());
            pst.setString(4, messageVO.getTime());
            pst.setLong(5, messageVO.getId());
            return pst.executeUpdate() >0 ? true:false;
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBConnector.getInstance().release(conn, pst, rst);
        }
        return false;
    }

    public boolean saveMessage(MessageVO messageVO) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            conn = DBConnector.getInstance().getConnection();
            String sql = String.format("insert into %s(%s,%s,%s,%s) values(?,?,?,?)", 
                    MESSAGE_TB,NAME,MESSAGE,CALL_MESSAGE,TIME);
            pst = conn.prepareStatement(sql);
            pst.setString(1, messageVO.getName());
            pst.setString(2, messageVO.getMessage());
            pst.setString(3, messageVO.getCallMessage());
            pst.setString(4, messageVO.getTime());
            return pst.executeUpdate() >0 ? true:false;
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBConnector.getInstance().release(conn, pst, rst);
        }
        return false;
    }

}
