package com.baifenjy.service;

import java.util.List;

import com.baifenjy.vo.Teacher;

public interface TeacherService
{
    boolean save(Teacher teacher);

    boolean saveOrUpdate(Teacher teacher);

    boolean update(Teacher teacher);

    Teacher queryByPhone(String phone);

    List<Teacher> queryByOrderId(String orderId);

}
