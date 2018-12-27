package com.baifenjy.talk.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Myserver {
	private static final int port = 9090;
	private static final String ENTER = "\r\n";
	private static final String CLIENT = "client";
	private static final String OK = "ok";
	public static ArrayList<ServerThread> list =new ArrayList<ServerThread>();
	public  void initServer() {
		try {
			ServerSocket server = new ServerSocket(port);// create server and set the specifical port
			System.out.println("server is ready......");
			int i = 0;
			while(true){
				Socket socket =server.accept();// get connection from client all the time
				System.out.println(socket.getInetAddress().getHostAddress()+":"+socket.getPort()+",client is connecting to server......");
				// when client connected to server then start a thread to send message to client
				ServerThread st = new ServerThread(socket);
				st.setName(CLIENT+i);
				st.start();
				list.add(st);// add client the list
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 
	}
	
	class ServerThread extends Thread {
	    
	    public Socket socket;
	    public InputStream ins;
	    public OutputStream ous;
	 
	    public ServerThread(Socket socket) {
	        this.socket = socket;
	    }
	 
	    @Override
	    public void run() {
	        try {
	            // get io 
	            ins = socket.getInputStream();
	            ous = socket.getOutputStream();
	            // message to client
	            String msg = "welcome"+ENTER;
	            sendMsg(ous, msg);
	            // send login message to client 
	            String userinfo = "please input your name:"+ENTER;
	            sendMsg(ous, userinfo);
	            // get username from client
	            String userName = readMsg(ins);
	            // send password message to client
	            String pwd = "please input your password:"+ENTER;
	            sendMsg(ous,  pwd);
	            // get password from client
	            String pass = readMsg(ins);
	            // do login
	            boolean falg = login(userName, pass);
	            // login failed
	            while (!falg) {
	                msg="username or password is wrong"+ENTER;
	                sendMsg(ous, msg);
	                msg = "please input your name again:"+ENTER;
	                sendMsg(ous, msg);
	                // get username from client
	                userName = readMsg(ins);
	                // send password message to client
	                msg = "please input your password again:"+ENTER;
	                sendMsg(ous, msg);
	                // get password from client
	                pass = readMsg(ins);
	                falg = login(userName, pass);
	            }
	 
	            //login success
	            //get message from client
	            if(falg){
	                msg=OK;
	                sendMsg(ous, msg+ENTER);
	                while(!"bye".equals(msg)){
	                    msg=readMsg(ins);
	                    System.out.println(userName+":"+msg+ENTER);
	                    //send message to every client
	                    for (int i = 0; i <Myserver.list.size(); i++) {
	                        ServerThread st =Myserver.list.get(i);
	                        //不该自己转发消息
	                        if(st!=this){
	                            sendMsg(st.ous, userName+":"+msg+ENTER);
	                        }
	                    }
	                    //等待读取下一次的消息
	                    msg=readMsg(ins);
	                }
	            }
	        } catch (Exception e) {
	            System.out.println(this.getName()+":客户端不正常关闭......");
	          //有异常后统一将流关闭
	            try {
	                ins.close();
	                ous.close();
	                socket.close();
	                //remove the closed client from list
	                Myserver.list.remove(this);
	            } catch (IOException er) {
	                er.printStackTrace();
	            }
	        }
	        
	    }
	 
	    /**
	     * login method
	     * @param name
	     * @param passwrod
	     *  */
	    public boolean login(String name, String pwd) {
	        if (name.equals("michael") && pwd.equals("123") || name.equals("abc") && pwd.equals("123")) {
	            return true;
	        }
	        return false;
	    }
	 
	    /**
	     *  send message to client */
	    public void sendMsg(OutputStream os, String s) throws IOException {
	        byte[] bytes = s.getBytes();
	        os.write(bytes);
	        os.flush();
	    }
	 
	    /**
	     * read message from client*/
	    public String readMsg(InputStream ins) throws Exception {
	        int value = ins.read();
	        // 读取整行 读取到回车（13）换行（10）时停止读
	        String str = "";
	        while (value != 10) {
	            // client is closed then return -1
	            if (value == -1) {
	                throw new Exception("client is disconnected");
	            }
	            str = str + ((char) value);
	            value = ins.read();
	        }
	        str = str.trim();
	        return str;
	    }
	 
	}
	
	public static void main(String[] args) {
        Myserver ms = new Myserver();
        ms.initServer();
    }
}