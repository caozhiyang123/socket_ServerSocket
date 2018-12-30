package com.baifenjy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baifenjy.dao.DaoFactory;
import com.baifenjy.dao.TeacherAndOrderDao;
import com.baifenjy.dao.TeacherDao;
import com.baifenjy.utils.ThreadLocalSimple;
import com.baifenjy.vo.Teacher;
import com.baifenjy.vo.TeacherAndOrder;
import com.mysql.jdbc.StringUtils;

public class TeacherServiceImpl implements TeacherService
{
    private TeacherDao teacherDao = DaoFactory.getTeacherDao();
    TeacherAndOrderDao teacherAndOrderDao = DaoFactory.getTeacherAndOrderDao();
    
    @Override
    public boolean saveOrUpdate(Teacher teacher){
        if(StringUtils.isNullOrEmpty(teacher.getPhoneNum())){
            return false;
        }
        Teacher teacherOld = teacherDao.queryByPhone(teacher.getPhoneNum());
        if(teacherOld == null){
            String time = ThreadLocalSimple.df.get().format(new Date());
            teacher.setCreateAt(time);
            teacher.setUpdateAt(time);
            teacher.setUpdated(0);
            return save(teacher);
        }else{
            int updated = teacherOld.getUpdated();
            if(updated == teacher.getUpdated()){
                teacher.setUpdated(updated+1);
                teacher.setUpdateAt(ThreadLocalSimple.df.get().format(new Date()));
                return update(teacher);
            }else{
                return false;
            }
        }
    }
    
    @Override
    public boolean save(Teacher teacher){
        return teacherDao.save(teacher);
    }
    
    @Override
    public boolean update(Teacher teacher){
        return teacherDao.update(teacher);
    }

    @Override
    public Teacher queryByPhone(String phone){
        Teacher teacher = teacherDao.queryByPhone(phone);
        initParameters(teacher);
        return teacher;
    }

    private void initParameters(Teacher teacher) {
        if(teacher == null){
            return;
        }
        List<String> orderIds = teacherAndOrderDao.queryOrderIdsByTecId(teacher.getId());
        StringBuffer sb = new StringBuffer();
        for (String id : orderIds) {
            sb.append(id+",");
        }
        teacher.setOrderIds(sb.toString());
        teacher.setItem(teacher.toString());
    }
    
    @Override
    public List<Teacher> queryByOrderId(String orderId){
        List<Teacher> tecs = new ArrayList<Teacher>();
        List<TeacherAndOrder> tecOrds = teacherAndOrderDao.queryByOrderId(orderId);
        for (TeacherAndOrder tecOrd : tecOrds) {
            long tecId = tecOrd.getTeacherId();
            Teacher teacher = teacherDao.queryById(tecId);
            if(teacher == null){
                continue;
            }
            initParameters(teacher);
            tecs.add(teacher);
        }
        return tecs;
    }

    @Override
    public boolean boundOrder(String orderId, String teacherId) {
        TeacherAndOrder teacherAndOrder = new TeacherAndOrder();
        teacherAndOrder.setOrderId(orderId);
        if(StringUtils.isNullOrEmpty(teacherId.trim())){
            return false;
        }
        long teacherID = 0;
        try {
            teacherID = Long.parseLong(teacherId.trim());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        teacherAndOrder.setTeacherId(teacherID);
        teacherAndOrder.setTeacherId(teacherID);
        return teacherAndOrderDao.boundOrder(teacherAndOrder);
    }
    
    @Override
    public boolean releaseOrder(String orderId, String teacherId){
        TeacherAndOrder teacherAndOrder = new TeacherAndOrder();
        teacherAndOrder.setOrderId(orderId);
        if(StringUtils.isNullOrEmpty(teacherId.trim())){
            return false;
        }
        long teacherID = 0;
        try {
            teacherID = Long.parseLong(teacherId.trim());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        teacherAndOrder.setTeacherId(teacherID);
        teacherAndOrder.setTeacherId(teacherID);
        return teacherAndOrderDao.releaseOrder(teacherAndOrder);
    }
}
