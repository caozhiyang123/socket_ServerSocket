package com.baifenjy.service;

import com.baifenjy.vo.Message;
import com.baifenjy.vo.MessageVO;

interface MessageService {

    Message pageQuery(int currentPage, int pageSize);

    boolean updateMessageById(MessageVO messageVO);

    boolean saveMessage(MessageVO messageVO);

}
