package com.baifenjy.service;

import java.util.List;

import com.baifenjy.vo.Teacher;
import com.baifenjy.vo.TeacherAndOrder;

public interface TeacherService
{
    boolean save(Teacher teacher);

    boolean saveOrUpdate(Teacher teacher);

    boolean update(Teacher teacher);

    Teacher queryByPhone(String phone);

    List<Teacher> queryByOrderId(String orderId);

    boolean boundOrder(String orderId, String teacherId);

    boolean releaseOrder(String orderId, String teacherId);

    boolean update(TeacherAndOrder teacherAndOrder);

    boolean save(TeacherAndOrder teacherAndOrder);

    TeacherAndOrder queryByOrderIdAndTeacherId(TeacherAndOrder teacherAndOrder);

}
