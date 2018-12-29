package com.baifenjy.server;

import com.baifenjy.vo.Teacher;
import com.mongodb.util.JSON;

public class Demo {
    public static void main(String[] args) {
        String json = "{"+"name:"+"michael"+",age:"+25+",sex:"+1+",email:"+"125@q.com"
                +",phoneNum:"+"18550716602"+",qqNum:"+"1250233433"+",weChatNum:"+"18550716602"+",address:"
                +"上海浦东"+"}";
      Teacher teacher = (Teacher)JSON.parse(json);  
      System.out.println(teacher.toString());
        
    }
}
