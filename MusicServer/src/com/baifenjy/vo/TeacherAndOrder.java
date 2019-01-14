package com.baifenjy.vo;

public class TeacherAndOrder {

    public long teacherId;
    public String orderId;
    public String startTeachTime;
    public boolean isParentPayed;
    public int parentCost;
    public boolean isTeacherPayed;
    public int teacherCost;
    public String createAt;
    public String updateAt;
    public int updated;
    public boolean used;
    
    public long getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getStartTeachTime() {
        return startTeachTime;
    }
    public void setStartTeachTime(String startTeachTime) {
        this.startTeachTime = startTeachTime;
    }
    public boolean isParentPayed() {
        return isParentPayed;
    }
    public void setParentPayed(boolean isParentPayed) {
        this.isParentPayed = isParentPayed;
    }
    public int getParentCost() {
        return parentCost;
    }
    public void setParentCost(int parentCost) {
        this.parentCost = parentCost;
    }
    public boolean isTeacherPayed() {
        return isTeacherPayed;
    }
    public void setTeacherPayed(boolean isTeacherPayed) {
        this.isTeacherPayed = isTeacherPayed;
    }
    public int getTeacherCost() {
        return teacherCost;
    }
    public void setTeacherCost(int teacherCost) {
        this.teacherCost = teacherCost;
    }
    public String getCreateAt() {
        return createAt;
    }
    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
    public String getUpdateAt() {
        return updateAt;
    }
    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
    public int getUpdated() {
        return updated;
    }
    public void setUpdated(int updated) {
        this.updated = updated;
    }
    public boolean isUsed() {
        return used;
    }
    public void setUsed(boolean used) {
        this.used = used;
    }
    
}
