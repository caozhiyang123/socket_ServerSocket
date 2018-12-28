package com.baifenjy.service;

import com.baifenjy.vo.Teacher;

public interface TeacherService
{
    boolean save(Teacher teacher);

    boolean saveOrUpdate(Teacher teacher);

    boolean update(Teacher teacher);

    Teacher queryByName(String name);

    Teacher queryByPhone(String phone);

    Teacher queryByQq(String qq);

    Teacher queryByWeChat(String weChat);

}
