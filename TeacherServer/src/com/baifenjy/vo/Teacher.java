package com.baifenjy.vo;

import java.io.Serializable;

public class Teacher implements Serializable{
    private static final long serialVersionUID = 1L;

    private long id;
    
    private String orderIds;
    
    private String name;
    
    private int age;
    
    private int sex;
    
    private String email;
    
    private String phoneNum;
    
    private String qqNum;
    
    private String weChatNum;
    
    private String address;
    
    private String idCard;
    
    private String college;
    
    private String profession;
    
    private String otherImports;
    
    private String certification;
    
    private String canTeacherGrade;
    
    private String canTeacherSubject;
    
    private String canTeacherArea;
    
    private String teachExperience;
    
    private String createAt;

    private String updateAt;
    
    private String item;
    
    private int updated;
    
    public Teacher() {
        super();
    }
    public Teacher( String orderIds,String name, int age, int sex, String email, String phoneNum, String qqNum,
            String weChatNum, String address, String idCard, String college, String profession,
            String otherImports, String certification, String canTeacherGrade, String canTeacherSubject,
            String canTeacherArea, String teachExperience,String createAt,String updateAt,String item,int updated) {
        this.orderIds = orderIds;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.email = email;
        this.phoneNum = phoneNum;
        this.qqNum = qqNum;
        this.weChatNum = weChatNum;
        this.address = address;
        this.idCard = idCard;
        this.college = college;
        this.profession = profession;
        this.otherImports = otherImports;
        this.certification = certification;
        this.canTeacherGrade = canTeacherGrade;
        this.canTeacherSubject = canTeacherSubject;
        this.canTeacherArea = canTeacherArea;
        this.teachExperience = teachExperience;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.item = item;
        this.updated = updated;
    }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    public String getOrderIds()
    {
        return orderIds;
    }
    public void setOrderIds(String orderIds)
    {
        this.orderIds = orderIds;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getSex() {
        return sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getIdCard() {
        return idCard;
    }
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    public String getCollege() {
        return college;
    }
    public void setCollege(String college) {
        this.college = college;
    }
    public String getProfession() {
        return profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }
    public String getOtherImports() {
        return otherImports;
    }
    public void setOtherImports(String otherImports) {
        this.otherImports = otherImports;
    }
    public String getCertification() {
        return certification;
    }
    public void setCertification(String certification) {
        this.certification = certification;
    }
    public String getCanTeacherGrade() {
        return canTeacherGrade;
    }
    public void setCanTeacherGrade(String canTeacherGrade) {
        this.canTeacherGrade = canTeacherGrade;
    }
    public String getCanTeacherSubject() {
        return canTeacherSubject;
    }
    public void setCanTeacherSubject(String canTeacherSubject) {
        this.canTeacherSubject = canTeacherSubject;
    }
    public String getCanTeacherArea() {
        return canTeacherArea;
    }
    public void setCanTeacherArea(String canTeacherArea) {
        this.canTeacherArea = canTeacherArea;
    }
    public String getTeachExperience() {
        return teachExperience;
    }
    public void setTeachExperience(String teachExperience) {
        this.teachExperience = teachExperience;
    }
    
    public String getCreateAt()
    {
        return createAt;
    }
    public void setCreateAt(String createAt)
    {
        this.createAt = createAt;
    }
    
    public String getUpdateAt() {
        return updateAt;
    }
    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
    public String getItem()
    {
        return item;
    }
    public void setItem(String item)
    {
        this.item = item;
    }
    public int getUpdated() {
        return updated;
    }
    public void setUpdated(int updated) {
        this.updated = updated;
    }
    @Override
    public String toString() {
        return "姓名:" + name + ", 年龄:" + age + ", 性别:"
                + sex + ", 邮箱:" + email + ", 电话:" + phoneNum + ", qq:" + qqNum + ", 微信:"
                + weChatNum + ", 地址:" + address + ", 大学:" + college
                + ", 专业:" + profession + ", 其他:" + otherImports + ", 证书/资格证:"
                + certification + ", 教授年级:" + canTeacherGrade + ", 教授科目:"
                + canTeacherSubject + ", 教授区域:" + canTeacherArea + ", 老师经验:"
                + teachExperience;
    }
    
}
