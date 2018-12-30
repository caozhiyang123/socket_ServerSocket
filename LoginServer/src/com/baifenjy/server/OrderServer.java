package com.baifenjy.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import com.baifenjy.io.Request;
import com.baifenjy.io.Response;
import com.baifenjy.service.OrderServiceImpl;
import com.baifenjy.vo.Order;
import com.baifenjy.vo.User;

public class OrderServer
{
    private static  OrderServiceImpl orderService;
    static{
        orderService = new OrderServiceImpl();
    }
    boolean started = false;
    ServerSocket ss = null;
    Socket s = null;
    String request = null;
    String response = null;
    
    public OrderServer(){
        fun();
    }
    
    private void fun() {
        ss = Server.getInstance().getServerSocket();
        started = true;
        try {
            while (started) {
                s = ss.accept();
                System.out.println("a client connected success");
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
                    if (request.equals(Request.COMMIT_ORDER)) {
                        
                        String orderId = dis.readUTF();
                        String studentName = dis.readUTF();
                        String studentAge = dis.readUTF();
                        String studentSex = dis.readUTF();
                        String studentGrade = dis.readUTF();
                        String studentSubject = dis.readUTF();
                        String address = dis.readUTF();
                        String otherImportants = dis.readUTF();
                        String cost = dis.readUTF();
                        String parentsName = dis.readUTF();
                        String qqNum = dis.readUTF();
                        String weChatNum = dis.readUTF();
                        String phoneNum = dis.readUTF();
                        String messageResource = dis.readUTF();

                        Order order = new Order();
                        order.setOrderId(orderId);
                        order.setStudentName(studentName);
                        order.setStudentAge(Integer.parseInt(studentAge));
                        order.setStudentSex("男".equals(studentSex)?1:("女".equals(studentSex)?0:3));
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

                        boolean saveFlag = orderService.saveOrUpdate(order);
                        if (saveFlag) {
                            dos.writeUTF(Response.SUCCESS);
                        }else {
                            dos.writeUTF(Response.FAIL);
                        }

                    } else if (request.equals(Request.QUERY_ORDER)) {
                        String orderId = dis.readUTF();
                        Order order = orderService.queryByOrderId(orderId);
                        dos.writeUTF(order.getOrderId());
                        dos.writeUTF(order.getStudentName()== null?"":order.getStudentName());
                        dos.writeUTF(order.getStudentAge()+"");
                        dos.writeUTF(order.getStudentSex()==1?"男":(order.getStudentSex()==0?"女":"未知"));
                        dos.writeUTF(order.getStudentGrade()== null?"":order.getStudentGrade());
                        dos.writeUTF(order.getStudentSubject()== null?"":order.getStudentSubject());
                        dos.writeUTF(order.getAddress() == null?"":order.getAddress());
                        dos.writeUTF(order.getOtherImportants() == null?"":order.getOtherImportants());
                        dos.writeUTF(order.getCost()== null?"":order.getCost());
                        dos.writeUTF(order.getParentsName()== null?"":order.getParentsName());
                        dos.writeUTF(order.getQqNum()== null?"":order.getQqNum());
                        dos.writeUTF(order.getWeChatNum()== null?"":order.getWeChatNum());
                        dos.writeUTF(order.getPhoneNum()== null?"":order.getPhoneNum());
                        dos.writeUTF(order.getMessageResource()== null?"":order.getMessageResource());
                        dos.writeUTF(order.getCreateAt()== null?"":order.getCreateAt());
                        dos.writeUTF(order.getUpdateAt()== null?"":order.getUpdateAt());
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
