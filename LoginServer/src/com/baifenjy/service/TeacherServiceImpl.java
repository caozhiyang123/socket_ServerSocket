package com.baifenjy.service;

import com.baifenjy.vo.Teacher;

public class TeacherServiceImpl implements TeacherService
{
    @Override
    public boolean saveOrUpdate(Teacher teacher){
        return false;
    }
    
    @Override
    public boolean save(Teacher teacher){
        return false;
        
    }
    
    @Override
    public boolean update(Teacher teacher){
        return false;
        
    }
    
    @Override
    public Teacher queryByName(String name){
     return null;   
    }

    @Override
    public Teacher queryByPhone(String phone){
        
        return null;   
    }
  
    @Override
    public Teacher queryByQq(String qq){
        return null;   
        
    }
  
    @Override
    public Teacher queryByWeChat(String weChat){
        return null;   
        
    }
}
