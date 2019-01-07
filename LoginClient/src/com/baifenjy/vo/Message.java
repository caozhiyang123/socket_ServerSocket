package com.baifenjy.vo;

import java.io.Serializable;
import java.util.Vector;

public class Message implements Serializable{
    private static final long serialVersionUID = 1L;
    private int currentPage;
    private int pageSize = 30;
    private int rowCount;
    private int pageCount;
    private Vector rowData;
    private Vector<String> columnName;
    
    private MessageVO messageVO;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Vector getRowData() {
        return rowData;
    }

    public void setRowData(Vector rowData) {
        this.rowData = rowData;
    }

    public Vector<String> getColumnName() {
        return columnName;
    }

    public void setColumnName(Vector<String> columnName) {
        this.columnName = columnName;
    }

    public MessageVO getMessageVO() {
        return messageVO;
    }

    public void setMessageVO(MessageVO messageVO) {
        this.messageVO = messageVO;
    }

}
