package com.baifenjy.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends Thread {

    //����һ��Socket����
    Socket socket = null;

    public Client(String host, int port) {
        try {
            //��Ҫ��������IP��ַ�Ͷ˿ںţ����ܻ����ȷ��Socket����
            socket = new Socket(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        //�ͻ���һ���ӾͿ���д���ݸ���������
        new sendMessThread().start();
        try {
            // ��Sock���������
            InputStream s = socket.getInputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = s.read(buf)) != -1) {
                System.out.println(new String(buf, 0, len));
            }
            /*BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()),1024);
            String len = null;
            while ((len = bf.readLine()) != null) {
                System.out.println(len);
            }
            bf.close();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //��Socket����д���ݣ���Ҫ�¿�һ���߳�
    class sendMessThread extends Thread{
        @Override
        public void run() {
            super.run();
            //д����
            Scanner scanner=null;
            OutputStream os= null;
            try {
                scanner=new Scanner(System.in);
                os= socket.getOutputStream();
                String in="";
                do {
                    in=scanner.nextLine();
                    os.write(("client:"+in).getBytes());
                    os.flush();
                } while (!in.equals("bye server"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            scanner.close();
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //�������
    public static void main(String[] args) {
        //��Ҫ����������ȷ��IP��ַ�Ͷ˿ں�
        Client clientTest=new Client("127.0.0.1", 1234);
        clientTest.start();
    }
}