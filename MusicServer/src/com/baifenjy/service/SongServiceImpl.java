package com.baifenjy.service;

import com.baifenjy.dao.DaoFactory;
import com.baifenjy.dao.MessageDao;
import com.baifenjy.dao.SongDao;
import com.baifenjy.vo.Message;
import com.baifenjy.vo.Song;
import com.mysql.jdbc.StringUtils;

public class SongServiceImpl implements SongService{

    private ThreadLocal<SongDao> songDaoLocal = new ThreadLocal<SongDao>(){
        @Override
        protected SongDao initialValue() {
            return DaoFactory.getSongDao();
        };
    };
    
    @Override
    public Song pageQuery(int currentPage, int pageSize, String title) {
        Song song = null;
        int rowCount = 0;
        if(StringUtils.isNullOrEmpty(title)){
            //select row data from db
            song = songDaoLocal.get().pageQuery((currentPage-1)*pageSize,pageSize);
            //select rowCount from db
            rowCount = songDaoLocal.get().queryRowCount();
        }else{
            song = songDaoLocal.get().pageQueryByName((currentPage-1)*pageSize,pageSize,title);
            rowCount = songDaoLocal.get().queryRowCountByName(title);
        }
        song.setRowCount(rowCount);
        song.setPageCount((rowCount+song.getPageSize()-1)/song.getPageSize());
        song.setCurrentPage(currentPage);
        return song;
    
    }

}
