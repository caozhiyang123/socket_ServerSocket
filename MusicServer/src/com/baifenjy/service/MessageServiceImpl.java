package com.baifenjy.service;

import com.baifenjy.dao.DaoFactory;
import com.baifenjy.dao.MessageDao;
import com.baifenjy.vo.Message;
import com.baifenjy.vo.MessageVO;
import com.mysql.jdbc.StringUtils;

public class MessageServiceImpl implements MessageService {
    private static ThreadLocal<MessageDao> messageDaoLocal = new ThreadLocal<MessageDao>(){
        @Override
        protected MessageDao initialValue() {
            return DaoFactory.getMessageDao();
        };
    };
    
    @Override
    public Message pageQuery(int currentPage, int pageSize ,String name) {
        Message message = null;
        int rowCount = 0;
        if(StringUtils.isNullOrEmpty(name)){
            //select row data from db
            message = messageDaoLocal.get().pageQuery((currentPage-1)*pageSize,pageSize);
            //select rowCount from db
            rowCount = messageDaoLocal.get().queryRowCount();
        }else{
            message = messageDaoLocal.get().pageQueryByName((currentPage-1)*pageSize,pageSize,name);
            rowCount = messageDaoLocal.get().queryRowCountByName(name);
        }
        message.setRowCount(rowCount);
        message.setPageCount((rowCount+message.getPageSize()-1)/message.getPageSize());
        message.setCurrentPage(currentPage);
        return message;
    }
    
    @Override
    public boolean updateMessageById(MessageVO messageVO) {
       return  messageDaoLocal.get().updateMessageById(messageVO);
    }

    @Override
    public boolean saveMessage(MessageVO messageVO) {
        return messageDaoLocal.get().saveMessage(messageVO);
    }
    
    
}
