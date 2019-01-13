package com.baifenjy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import com.baifenjy.utils.DBConnector;
import com.baifenjy.vo.Song;

public class SongDao {
    
    private static final String SONG_TB = "song_tb";
    private static final String ID = "id";
    private static final String OPERATION = "operation";
    private static final String TITLE = "title";
    private static final String SINGER = "singer";
    private static final String ALBUM = "album";
    private static final String TIME = "time";
    private static final String LOCAL_PATH = "local_path";

    public Song pageQuery(int beginIndex, int length) {
        Song song = new Song();
        Vector rowDatas = new Vector();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            conn = DBConnector.getInstance().getConnection();
            String sql = String.format("select * from %s  order by %s desc limit ?,?", SONG_TB,TIME);
            pst = conn.prepareStatement(sql);
            pst.setInt(1, beginIndex);
            pst.setInt(2, length);
            rst = pst.executeQuery();
            while(rst.next()){
                Vector rowData = new Vector();
                rowData.add(rst.getLong(ID));
                rowData.add(rst.getString(OPERATION));
                rowData.add(rst.getString(TITLE));
                rowData.add(rst.getString(SINGER));
                rowData.add(rst.getString(ALBUM));
                rowData.add(rst.getString(TIME));
                rowData.add(rst.getString(LOCAL_PATH));
                rowDatas.add(rowData);
            }
            song.setRowData(rowDatas);
            
            
            Vector columnName = new Vector();
            ResultSetMetaData metaData = rst.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columnName.add(metaData.getColumnName(i));//这里是列名
            }
            song.setColumnName(columnName);
            return song;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBConnector.getInstance().release(conn, pst, rst);
        }
        
        return song;
    
    }

    public int queryRowCount() {

        int rowCount = 0;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            conn = DBConnector.getInstance().getConnection();
            String sql = String.format("select count(*) from %s",SONG_TB);
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            while(rst.next()){
                rowCount = rst.getInt(1);
            }
            return rowCount;
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBConnector.getInstance().release(conn, pst, rst);
        }
        return rowCount;
    }

    public Song pageQueryByName(int i, int pageSize, String title) {
        return null;
    }

    public int queryRowCountByName(String title) {
        return 0;
    }

}
