package com.baifenjy.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baifenjy.core.Application;
import com.baifenjy.io.Request;
import com.baifenjy.io.Response;
import com.baifenjy.server.Server;
import com.baifenjy.service.TeacherServiceImpl;
import com.baifenjy.vo.Teacher;
import com.mysql.jdbc.StringUtils;

public class TeacherController
{
    private static  TeacherServiceImpl teacherService;
    static{
        teacherService = new TeacherServiceImpl();
    }
    boolean started = false;
    ServerSocket ss = null;
    Socket s = null;
    String request = null;
    String response = null;
    
    public TeacherController(){
        fun();
    }

    private void fun()
    {
        ss = Server.getInstance().getTeaServerSocket();
        started = true;
        try {
            while (started) {
                s = ss.accept();
                System.out.println("teacher client connected success");
                Client c = new Client(s);
                new Thread(c).start();
            }
        } catch (EOFException e) {
            System.out.println("client has closed.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    class Client implements Runnable {

        private Socket s;
        private DataInputStream dis;
        private DataOutputStream dos;
        private boolean connected = false;

        public Client(Socket s) {
            this.s = s;
            try {
                this.dis = new DataInputStream(s.getInputStream());
                this.dos = new DataOutputStream(s.getOutputStream());
                connected = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run()
        {
            try {
                while (connected) {
                    String request = dis.readUTF();
                    String response = request;
                    dos.writeUTF(response);
                    if (request.equals(Request.COMMIT_TEC)) {
                        
                        String teacherStr = dis.readUTF();
                        JSONObject json = (JSONObject) JSON.parse(teacherStr);
                        Teacher teacher = new Teacher();
                        teacher.setName(json.getString("name"));
                        teacher.setAge(Integer.parseInt(json.getString("age")));
                        teacher.setSex(Integer.parseInt(json.getString("sex")));
                        teacher.setEmail(json.getString("email"));
                        teacher.setPhoneNum(json.getString("phoneNum"));
                        teacher.setQqNum(json.getString("qqNum"));
                        teacher.setWeChatNum(json.getString("weChatNum"));
                        teacher.setAddress(json.getString("address"));
                        teacher.setIdCard(json.getString("idCard"));
                        teacher.setCollege(json.getString("college"));
                        teacher.setProfession(json.getString("profession"));
                        teacher.setOtherImports(json.getString("otherImports"));
                        teacher.setCertification(json.getString("certification"));
                        teacher.setCanTeacherGrade(json.getString("canTeacherGrade"));
                        teacher.setCanTeacherSubject(json.getString("canTeacherSubject"));
                        teacher.setCanTeacherArea(json.getString("canTeacherArea"));
                        teacher.setTeachExperience(json.getString("teachExperience"));
                        teacher.setUpdated(Integer.parseInt(json.getString("updated")));
                        
                        
                        boolean saveFlag = teacherService.saveOrUpdate(teacher);
                        if (saveFlag) {
                            dos.writeUTF(Response.SUCCESS);
                        }else {
                            dos.writeUTF(Response.FAIL);
                        }

                    } else if (request.equals(Request.QUERY_TEC_BY_ORDERID)) {
                        String orderId = dis.readUTF();
                        List<Teacher> tecs = teacherService.queryByOrderId(orderId);
                        dos.writeUTF(JSON.toJSONString(tecs));
                    } else if(request.equals(Request.QUERY_TEC_BY_TEACHER_PHONE)){
                        String phone = dis.readUTF();
                        Teacher teacher = teacherService.queryByPhone(phone);
                        dos.writeUTF(JSON.toJSONString(teacher));
                    }
                }
            } catch (SocketException e) {
                System.out.println("一个登陆窗已经关闭....");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (s != null) {
                    try {
                        s.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (dis != null) {
                    try {
                        dis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (dos != null) {
                    try {
                        dos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        
        }

    }
    
}
