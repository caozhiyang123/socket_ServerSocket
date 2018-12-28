package com.baifenjy.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import com.baifenjy.dao.DaoFactory;
import com.baifenjy.service.UserServiceImpl;
import com.baifenjy.vo.User;

public class LoginServer {

    private static UserServiceImpl userService;
    static{
        userService = new UserServiceImpl();
    }
    boolean started = false;
    ServerSocket ss = null;
    Socket s = null;
    String request = null;
    String response = null;
    
    public LoginServer(){
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
                // TODO Auto-generated catch block
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
        public void run() {
            try {
                while (connected) {
                    request = dis.readUTF();
                    response = request;
                    dos.writeUTF(response);
                    if (request.equals("login")) {

                        String phone = dis.readUTF();
                        String password = dis.readUTF();
                        System.out.println(phone);
                        System.out.println(password);

                        User user = new User(phone, password);
                        boolean login =userService.login(user);
                        if (login) {
                            dos.writeUTF("success");
                        }else {
                            dos.writeUTF("fail");
                        }

                    } else if (request.equals("reg")) {
                        String phone = dis.readUTF();
                        String password = dis.readUTF();
                        System.out.println(phone);
                        System.out.println(password);

                        User user = new User(phone, password);
                        userService.register(user);
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