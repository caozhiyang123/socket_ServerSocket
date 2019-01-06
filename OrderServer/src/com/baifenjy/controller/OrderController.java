package com.baifenjy.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baifenjy.io.Request;
import com.baifenjy.io.Response;
import com.baifenjy.server.Server;
import com.baifenjy.service.MessageServiceImpl;
import com.baifenjy.service.OrderServiceImpl;
import com.baifenjy.vo.Message;
import com.baifenjy.vo.MessageVO;
import com.baifenjy.vo.Order;

public class OrderController
{
    private static  ThreadLocal<OrderServiceImpl> orderServiceLocal = new ThreadLocal<OrderServiceImpl>();
    static{
        orderServiceLocal.set(new OrderServiceImpl());
    }
    private static ThreadLocal<MessageServiceImpl> messageServiceLocal = new ThreadLocal<MessageServiceImpl>();
    static{
        messageServiceLocal.set(new MessageServiceImpl());
    }
    
    boolean started = false;
    ServerSocket ss = null;
    Socket s = null;
    String request = null;
    String response = null;
    
    public OrderController(){
        fun();
    }
    
    private void fun() {
        ss = Server.getInstance().getOrderServerSocket();
        started = true;
        try {
            while (started) {
                s = ss.accept();
                System.out.println("order client connected success");
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
                    request = dis.readUTF();
                    response = request;
                    dos.writeUTF(response);
                    if (Request.COMMIT_ORDER.equals(request)) {
                        String orderStr = dis.readUTF();
                        JSONObject jsonObj = (JSONObject) JSON.parse(orderStr);
                        String orderId = jsonObj.getString("orderId");
                        String studentName = jsonObj.getString("studentName");
                        int studentAge = jsonObj.getIntValue("studentAge");
                        int studentSex = jsonObj.getIntValue("studentSex");
                        String studentGrade = jsonObj.getString("studentGrade");
                        String studentSubject =  jsonObj.getString("studentSubject");
                        String address = jsonObj.getString("address");
                        String otherImportants = jsonObj.getString("otherImportants");
                        String cost = jsonObj.getString("cost");
                        String parentsName = jsonObj.getString("parentsName");
                        String qqNum = jsonObj.getString("qqNum");
                        String weChatNum = jsonObj.getString("weChatNum");
                        String phoneNum = jsonObj.getString("phoneNum");
                        String messageResource = jsonObj.getString("messageResource");
                        int updated =  jsonObj.getIntValue("updated");

                        Order order = new Order();
                        order.setOrderId(orderId);
                        order.setStudentName(studentName);
                        order.setStudentAge(studentAge);
                        order.setStudentSex(studentSex);
                        order.setStudentGrade(studentGrade);
                        order.setStudentSubject(studentSubject);
                        order.setAddress(address);
                        order.setOtherImportants(otherImportants);
                        order.setCost(cost);
                        order.setParentsName(parentsName);
                        order.setQqNum(qqNum);
                        order.setWeChatNum(weChatNum);
                        order.setPhoneNum(phoneNum);
                        order.setMessageResource(messageResource);
                        order.setUpdated(updated);

                        boolean saveFlag = orderServiceLocal.get().saveOrUpdate(order);
                        if (saveFlag) {
                            dos.writeUTF(Response.SUCCESS);
                        }else {
                            dos.writeUTF(Response.FAIL);
                        }

                    } else if (Request.QUERY_ORDER.equals(request)) {
                        String orderId = dis.readUTF();
                        Order order = orderServiceLocal.get().queryByOrderId(orderId);
                        dos.writeUTF(JSON.toJSONString(order));
                    }
                    
                    if(Request.PAGE_QUERY_MESSAGE.equals(request)){
                        String messStr = dis.readUTF();
                        JSONObject messObj = (JSONObject) JSON.parse(messStr);
                        int currentPage = Integer.parseInt(messObj.get("currentPage").toString());
                        int pageSize =  Integer.parseInt(messObj.get("pageSize").toString());
                        //page begin from 0 in client
                        Message message =  messageServiceLocal.get().pageQuery(currentPage+1,pageSize);
                        messStr = JSON.toJSONString(message);
                        dos.writeUTF(messStr);
                    }else if(Request.UPDATE_MESSAGE.equals(request)){
                        String messStr = dis.readUTF();
                        JSONObject messObj = (JSONObject) JSON.parse(messStr);
                        MessageVO messageVO = new MessageVO();
                        messageVO.setId(messObj.getLongValue("id"));
                        messageVO.setName(messObj.getString("name"));
                        messageVO.setMessage(messObj.getString("message"));
                        messageVO.setCallMessage(messObj.getString("callMessage"));
                        messageVO.setTime(messObj.getString("time"));
                        boolean flag = messageServiceLocal.get().updateMessageById(messageVO);
                        dos.writeUTF(flag?Response.SUCCESS:Response.FAIL);
                    }
                    if(Request.SAVE_MESSAGE.equals(request)){
                        String messStr = dis.readUTF();
                        JSONObject messObj = (JSONObject) JSON.parse(messStr);
                        MessageVO messageVO = new MessageVO();
                        messageVO.setName(messObj.getString("name"));
                        messageVO.setMessage(messObj.getString("message"));
                        messageVO.setCallMessage(messObj.getString("callMessage"));
                        messageVO.setTime(messObj.getString("time"));
                        boolean flag = messageServiceLocal.get().saveMessage(messageVO);
                        dos.writeUTF(flag?Response.SUCCESS:Response.FAIL);
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
