package com.baifenjy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.baifenjy.utils.DBConnector;
import com.baifenjy.vo.Teacher;

public class TeacherDao {
    
    private static final String TABLENAME = "teacher_tb";
    private static final String ID = "id";
    private static final String ORDER_IDS = "order_ids";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String AGE = "age";
    private static final String SEX = "sex";
    private static final String CAN_TEACHER_AREA = "can_teacher_area";
    private static final String CAN_TEACHER_GRADE = "can_teacher_grade";
    private static final String CAN_TEACHER_SUBJECT = "can_teacher_subject";
    private static final String CERTIFICATION = "certification";
    private static final String COLLEGE = "college";
    private static final String EMAIL = "email";
    private static final String ID_CARD = "id_card";
    private static final String PHONE_NUM = "phone_num";
    private static final String QQ_NUM = "qq_num";
    private static final String WE_CHAT_NUM = "we_chat_num";
    private static final String PROFESSION = "profession";
    private static final String TEACH_EXPERIENCE = "teach_experience";
    private static final String OTHER_IMPORTS = "other_imports";
    private static final String CREATE_AT = "create_at";
    private static final String UPDATE_AT = "update_at";
    private static final String UPDATED = "updated";

    public Teacher queryById(long id) {
        Teacher teacher = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet  rst = null;
        try {
            conn = DBConnector.getInstance().getConnection();
            String sql = String.format("select * from %s  where %s = ?", TABLENAME,ID);
            pst = conn.prepareStatement(sql);
            pst.setLong(1, id);
            rst = pst.executeQuery();
            while(rst.next()){
                teacher =  new Teacher();
                teacher.setId(id);
                teacher.setName(rst.getString(NAME)==null?"":rst.getString(NAME));
                teacher.setAge(rst.getInt(AGE));
                teacher.setSex(rst.getInt(SEX));
                teacher.setAddress(rst.getString(ADDRESS)==null?"":rst.getString(ADDRESS));
                teacher.setCanTeacherArea(rst.getString(CAN_TEACHER_AREA)==null?"":rst.getString(CAN_TEACHER_AREA));
                teacher.setCanTeacherGrade(rst.getString(CAN_TEACHER_GRADE)==null?"":rst.getString(CAN_TEACHER_GRADE));
                teacher.setCanTeacherSubject(rst.getString(CAN_TEACHER_SUBJECT)==null?"":rst.getString(CAN_TEACHER_SUBJECT));
                teacher.setCertification(rst.getString(CERTIFICATION)==null?"":rst.getString(CERTIFICATION));
                teacher.setCollege(rst.getString(COLLEGE)==null?"":rst.getString(COLLEGE));
                teacher.setEmail(rst.getString(EMAIL)==null?"":rst.getString(EMAIL));
                teacher.setIdCard(rst.getString(ID_CARD)==null?"":rst.getString(ID_CARD));
                teacher.setPhoneNum(rst.getString(PHONE_NUM)==null?"":rst.getString(PHONE_NUM));
                teacher.setQqNum(rst.getString(QQ_NUM)==null?"":rst.getString(QQ_NUM));
                teacher.setWeChatNum(rst.getString(WE_CHAT_NUM)==null?"":rst.getString(WE_CHAT_NUM));
                teacher.setProfession(rst.getString(PROFESSION)==null?"":rst.getString(PROFESSION));
                teacher.setTeachExperience(rst.getString(TEACH_EXPERIENCE)==null?"":rst.getString(TEACH_EXPERIENCE));
                teacher.setOtherImports(rst.getString(OTHER_IMPORTS)==null?"":rst.getString(OTHER_IMPORTS));
                teacher.setCreateAt(rst.getString(CREATE_AT)==null?"":rst.getString(CREATE_AT));
                teacher.setUpdateAt(rst.getString(UPDATE_AT)==null?"":rst.getString(UPDATE_AT));
            }
            return teacher;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBConnector.getInstance().release(conn, pst, rst);
        }
        return teacher;
    }
    
    public Teacher queryByPhone(String phone) {
        Teacher teacher = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet  rst = null;
        try {
            conn = DBConnector.getInstance().getConnection();
            String sql = String.format("select * from %s where %s = ?", TABLENAME,PHONE_NUM);
            pst = conn.prepareStatement(sql);
            pst.setString(1, phone);
            rst = pst.executeQuery();
            while(rst.next()){
                teacher = new Teacher();
                teacher.setPhoneNum(phone);
                teacher.setId(rst.getLong(ID));
                teacher.setName(rst.getString(NAME)==null?"":rst.getString(NAME));
                teacher.setAge(rst.getInt(AGE));
                teacher.setSex(rst.getInt(SEX));
                teacher.setAddress(rst.getString(ADDRESS)==null?"":rst.getString(ADDRESS));
                teacher.setCanTeacherArea(rst.getString(CAN_TEACHER_AREA)==null?"":rst.getString(CAN_TEACHER_AREA));
                teacher.setCanTeacherGrade(rst.getString(CAN_TEACHER_GRADE)==null?"":rst.getString(CAN_TEACHER_GRADE));
                teacher.setCanTeacherSubject(rst.getString(CAN_TEACHER_SUBJECT)==null?"":rst.getString(CAN_TEACHER_SUBJECT));
                teacher.setCertification(rst.getString(CERTIFICATION)==null?"":rst.getString(CERTIFICATION));
                teacher.setCollege(rst.getString(COLLEGE)==null?"":rst.getString(COLLEGE));
                teacher.setEmail(rst.getString(EMAIL)==null?"":rst.getString(EMAIL));
                teacher.setIdCard(rst.getString(ID_CARD)==null?"":rst.getString(ID_CARD));
                teacher.setQqNum(rst.getString(QQ_NUM)==null?"":rst.getString(QQ_NUM));
                teacher.setWeChatNum(rst.getString(WE_CHAT_NUM)==null?"":rst.getString(WE_CHAT_NUM));
                teacher.setProfession(rst.getString(PROFESSION)==null?"":rst.getString(PROFESSION));
                teacher.setTeachExperience(rst.getString(TEACH_EXPERIENCE)==null?"":rst.getString(TEACH_EXPERIENCE));
                teacher.setOtherImports(rst.getString(OTHER_IMPORTS)==null?"":rst.getString(OTHER_IMPORTS));
                teacher.setCreateAt(rst.getString(CREATE_AT)==null?"":rst.getString(CREATE_AT));
                teacher.setUpdateAt(rst.getString(UPDATE_AT)==null?"":rst.getString(UPDATE_AT));
                teacher.setUpdated(rst.getInt(UPDATED));
            }
            return teacher;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBConnector.getInstance().release(conn, pst, rst);
        }
        return teacher;
    }

    public boolean save(Teacher teacher) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnector.getInstance().getConnection();
            String sql = String.format("insert into %s(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s"
                    + ",%s,%s,%s,%s,%s,%s) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",TABLENAME,NAME
                    ,AGE,SEX,ADDRESS,CAN_TEACHER_AREA,CAN_TEACHER_GRADE,CAN_TEACHER_SUBJECT
                    ,CERTIFICATION,COLLEGE,EMAIL,ID_CARD,PHONE_NUM,QQ_NUM,WE_CHAT_NUM,PROFESSION,TEACH_EXPERIENCE
                    ,OTHER_IMPORTS,CREATE_AT,UPDATE_AT,UPDATED);
            pst = conn.prepareStatement(sql);
            pst.setString(1, teacher.getName());
            pst.setInt(2, teacher.getAge());
            pst.setInt(3, teacher.getSex());
            pst.setString(4, teacher.getAddress());
            pst.setString(5, teacher.getCanTeacherArea());
            pst.setString(6,teacher.getCanTeacherGrade());
            pst.setString(7, teacher.getCanTeacherSubject());
            pst.setString(8, teacher.getCertification());
            pst.setString(9, teacher.getCollege());
            pst.setString(10,teacher.getEmail());
            pst.setString(11, teacher.getIdCard());
            pst.setString(12, teacher.getPhoneNum());
            pst.setString(13, teacher.getQqNum());
            pst.setString(14, teacher.getWeChatNum());
            pst.setString(15, teacher.getProfession());
            pst.setString(16, teacher.getTeachExperience());
            pst.setString(17, teacher.getOtherImports());
            pst.setString(18, teacher.getCreateAt());
            pst.setString(19, teacher.getUpdateAt());
            pst.setInt(20, teacher.getUpdated());
            return  pst.executeUpdate() >0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBConnector.getInstance().release(conn, pst, null);
        }
        return false;
    }

    public boolean update(Teacher teacher) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnector.getInstance().getConnection();
            String sql = String.format("update %s set %s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,"
                    + "%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?",
                    TABLENAME,NAME,AGE,SEX,ADDRESS,CAN_TEACHER_AREA,CAN_TEACHER_GRADE,CAN_TEACHER_SUBJECT
                    ,CERTIFICATION,COLLEGE,EMAIL,ID_CARD,PHONE_NUM,QQ_NUM,WE_CHAT_NUM,PROFESSION,TEACH_EXPERIENCE
                    ,OTHER_IMPORTS,UPDATE_AT,UPDATED);
            pst = conn.prepareStatement(sql);
            pst.setString(1, teacher.getName());
            pst.setInt(2, teacher.getAge());
            pst.setInt(3, teacher.getSex());
            pst.setString(4, teacher.getAddress());
            pst.setString(5, teacher.getCanTeacherArea());
            pst.setString(6,teacher.getCanTeacherGrade());
            pst.setString(7, teacher.getCanTeacherSubject());
            pst.setString(8, teacher.getCertification());
            pst.setString(9, teacher.getCollege());
            pst.setString(10,teacher.getEmail());
            pst.setString(11, teacher.getIdCard());
            pst.setString(12, teacher.getPhoneNum());
            pst.setString(13, teacher.getQqNum());
            pst.setString(14, teacher.getWeChatNum());
            pst.setString(15, teacher.getProfession());
            pst.setString(16, teacher.getTeachExperience());
            pst.setString(17, teacher.getOtherImports());
            pst.setString(18, teacher.getUpdateAt());
            pst.setInt(19, teacher.getUpdated());
            return  pst.executeUpdate() >0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBConnector.getInstance().release(conn, pst, null);
        }
        return false;
    
    }
    

}
