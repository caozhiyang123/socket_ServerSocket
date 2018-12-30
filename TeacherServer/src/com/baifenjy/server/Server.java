package com.baifenjy.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server
{
    private static ServerSocket loginSos;
    private static ServerSocket orderSos;
    private static ServerSocket teaSos;
    private static Server server;
    private Server(){}
    public static Server getInstance(){
        if(server == null || teaSos == null){
            synchronized (Server.class)
            {
               try {
                   server = new Server();
//                   loginSos = new ServerSocket(8887);
//                   orderSos = new ServerSocket(8888);
                   teaSos = new ServerSocket(8899);
               } catch (IOException e) {
                   e.printStackTrace();
               }
            }
        }
        return server;
    }
    /**
     *  port 8887
     * @return
     */
    public ServerSocket getLoginServerSocket(){
        return loginSos;
    }
    /**
     *  port 8888
     * @return
     */
    public ServerSocket getOrderServerSocket(){
        return orderSos;
    }
    /**
     *  port 8899
     * @return
     */
    public ServerSocket getTeaServerSocket(){
        return teaSos;
    }
}
