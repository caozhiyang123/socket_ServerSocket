package com.baifenjy.talk.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Myclient {
 
    private static final int port = 9090;
    private static final String ip = "localhost";
    private static final String SERVER = "server:";
    private static final String ENTER = "\r\n";
    private static final String OK = "ok";
    
    Socket client = null;
            
    class ClientThread extends Thread{
        
        @Override
        public void run()
        {
            try {
                // get io
                InputStream ins = client.getInputStream();
                OutputStream ous = client.getOutputStream();
                Scanner scanner = new Scanner(System.in);
                String msg = readMsg(ins);
                boolean flag = doLogin(ins, ous, msg,scanner);
                if(flag){
                    // send message thread
                    new SendThread(scanner,ous).start();
                    // read message thread
                    new ReadThread(ins).start();
                }
            }catch (Exception e){
               e.printStackTrace();
            }
        }
        
        class ReadThread extends Thread{
            private InputStream ins;
            
            public ReadThread(InputStream ins)
            {
                this.ins = ins;
            }

            @Override
            public void run()
            {
                try {
                    String message = readMsg(ins);
                    while (true) {
                        System.out.println(message);
                        message = readMsg(ins);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        class SendThread extends Thread{
            private Scanner scanner;
            private OutputStream ous;
            
            public SendThread(Scanner scanner, OutputStream ous)
            {
                this.scanner = scanner;
                this.ous = ous;
            }

            @Override
            public void run()
            {
                String message = scanner.nextLine();
                try {
                    while (true) {
                        sendMsg(ous, message);
                        message = scanner.nextLine()+ENTER;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        }

        public boolean doLogin(InputStream ins, OutputStream ous, String msg, Scanner scanner) throws Exception
        {
            System.out.println(SERVER+msg);
            // login message
            String requestName = readMsg(ins);
            System.out.println(SERVER+requestName);
            
            String username = scanner.nextLine();
            // send name
            sendMsg(ous, username + ENTER);
            String requestPwd = readMsg(ins);
            System.out.println(SERVER+requestPwd);
            String pwd = scanner.nextLine();
            //send pass
            sendMsg(ous, pwd + ENTER);
            // login result
            String result = readMsg(ins);
            while(!OK.equals(result)){
                // login failed
                String message = readMsg(ins);
                System.out.println(SERVER+message);
                //login
                username = scanner.nextLine();
                sendMsg(ous, username + ENTER);
                message=readMsg(ins);
                System.out.println(SERVER+message);
                pwd = scanner.nextLine();
                sendMsg(ous, pwd + ENTER);
                // login result
                result = readMsg(ins);
            }
            if(OK.equals(result)){
                System.out.println("login success,welcome to chat room");
                return true;
            }
            return false;
        }
    }
    
	public void initClient() {
	    try {
            client = new Socket(ip, port);
            new ClientThread().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
 
	public String readMsg(InputStream ins) throws Exception {
		int value = ins.read();
		String str = "";
		while (value != 10) {
			// 代表客户单不正常关闭
			if (value == -1) {
				throw new Exception();
			}
			str = str + (char) value;
			value = ins.read();
		}
		str = str.trim();
		return str;
	}
 
	// send message
	public void sendMsg(OutputStream ous, String str) throws Exception {
		byte[] bytes = str.getBytes();
		ous.write(bytes);
		ous.flush();
	}
	
	public static void main(String[] args) {
        Myclient mc = new Myclient();
        mc.initClient();
    }
}