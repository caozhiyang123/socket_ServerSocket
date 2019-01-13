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
import com.baifenjy.controller.OrderController.Client;
import com.baifenjy.io.Request;
import com.baifenjy.io.Response;
import com.baifenjy.server.Server;
import com.baifenjy.service.OrderServiceImpl;
import com.baifenjy.service.SongServiceImpl;
import com.baifenjy.vo.Message;
import com.baifenjy.vo.MessageVO;
import com.baifenjy.vo.Order;
import com.baifenjy.vo.Song;

public class SongController {
    
    private ThreadLocal<SongServiceImpl> songServiceLocal = new ThreadLocal<SongServiceImpl>(){
        @Override
        protected SongServiceImpl initialValue() {
            return  new SongServiceImpl();
        };
    };

    public SongController() {
        fun();
    }
    
    boolean started = false;
    ServerSocket ss = null;
    Socket s = null;
    String request = null;
    String response = null;

    private void fun() {

        ss = Server.getInstance().getOrderServerSocket();
        started = true;
        try {
            while (started) {
                s = ss.accept();
                System.out.println("music client connected success");
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
                    if(Request.PAGE_QUERY_SONGS.equals(request)){
                        String songStr = dis.readUTF();
                        JSONObject songObj = (JSONObject) JSON.parse(songStr);
                        int currentPage = Integer.parseInt(songObj.get("currentPage").toString());
                        int pageSize =  Integer.parseInt(songObj.get("pageSize").toString());
                        Object songVOObj = songObj.get("songVO");
                        String title = "";
                        if(songVOObj!= null){
                            JSONObject songObjVO = (JSONObject)songVOObj;
                            title = songObjVO.get("title") ==null ?"":songObjVO.get("title").toString();
                        }
                        //page begin from 0 in client
                        Song song =  songServiceLocal.get().pageQuery(currentPage+1,pageSize,title);
                        songStr = JSON.toJSONString(song);
                        dos.writeUTF(songStr);
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
