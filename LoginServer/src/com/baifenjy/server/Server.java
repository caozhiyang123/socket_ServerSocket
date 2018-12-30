package com.baifenjy.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server
{
    private static ServerSocket sos;
    private static Server server;
    private Server(){}
    public static Server getInstance(){
        if(sos == null){
            synchronized (Server.class)
            {
                server = new Server();
                try
                {
                    sos = new ServerSocket(8888);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return server;
    }
    public ServerSocket getServerSocket(){
        return sos;
    }
}
