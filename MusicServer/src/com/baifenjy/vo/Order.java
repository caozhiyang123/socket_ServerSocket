package com.baifenjy.vo;

import java.io.Serializable;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    
    private String orderId;
    
    private String studentName;
    
    private int studentAge;
    
    private int studentSex;
    
    private String studentGrade;
    
    private String studentSubject;
    
    private String address;
    
    private String otherImportants;
    
    private String cost;
    
    private String parentsName;
    
    private String phoneNum;
    
    private String qqNum;
    
    private String weChatNum;
    
    private String messageResource;

    private String createAt;
    
    private String updateAt;
    
    private int updated;
    
    private String orderItem;
    
    public Order()
    {
        super();
    }
    public Order(String orderId,String studentName, int studentAge, int studentSex, String studentGrade,
            String studentSubject, String address, String otherImportants, String cost, String parentsName,
            String phoneNum, String qqNum, String weChatNum, String messageResource,String createAt
            ,String updateAt,int updated,String orderItem) {
        this.orderId = orderId;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentSex = studentSex;
        this.studentGrade = studentGrade;
        this.studentSubject = studentSubject;
        this.address = address;
        this.otherImportants = otherImportants;
        this.cost = cost;
        this.parentsName = parentsName;
        this.phoneNum = phoneNum;
        this.qqNum = qqNum;
        this.weChatNum = weChatNum;
        this.messageResource = messageResource;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.updated = updated;
        this.orderItem = orderItem;
    }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public int getStudentAge() {
        return studentAge;
    }
    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }
    public int getStudentSex() {
        return studentSex;
    }
    public void setStudentSex(int studentSex) {
        this.studentSex = studentSex;
    }
    public String getStudentGrade() {
        return studentGrade;
    }
    public void setStudentGrade(String studentGrade) {
        this.studentGrade = studentGrade;
    }
    public String getStudentSubject() {
        return studentSubject;
    }
    public void setStudentSubject(String studentSubject) {
        this.studentSubject = studentSubject;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getOtherImportants() {
        return otherImportants;
    }
    public void setOtherImportants(String otherImportants) {
        this.otherImportants = otherImportants;
    }
    public String getCost() {
        return cost;
    }
    public void setCost(String cost) {
        this.cost = cost;
    }
    public String getParentsName() {
        return parentsName;
    }
    public void setParentsName(String parentsName) {
        this.parentsName = parentsName;
    }
    
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    
    public String getQqNum() {
        return qqNum;
    }
    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }
    public String getWeChatNum() {
        return weChatNum;
    }
    public void setWeChatNum(String weChatNum) {
        this.weChatNum = weChatNum;
    }
    public String getMessageResource() {
        return messageResource;
    }
    public void setMessageResource(String messageResource) {
        this.messageResource = messageResource;
    }
    
    public String getCreateAt()
    {
        return createAt;
    }
    public void setCreateAt(String createAt)
    {
        this.createAt = createAt;
    }
    public String getUpdateAt()
    {
        return updateAt;
    }
    public void setUpdateAt(String updateAt)
    {
        this.updateAt = updateAt;
    }
    public int getUpdated() {
        return updated;
    }
    public void setUpdated(int updated) {
        this.updated = updated;
    }
    
    public String getOrderItem() {
        return orderItem;
    }
    public void setOrderItem(String orderItem) {
        this.orderItem = orderItem;
    }
    @Override
    public String toString() {
        return "【上海百分家教】订单号 "+ orderId 
                + " 年级 " +studentGrade+" "+(studentSex==1?"男":(studentSex==2?"女":"未知"))
                + " 辅导科目 "+studentSubject 
                + " 时间及老师要求 "+otherImportants 
                + " 费用 "+cost 
                + " 地址"+address
                + "(如想预约，可直接小窗我，非诚勿扰)";
    }
    
    
}
