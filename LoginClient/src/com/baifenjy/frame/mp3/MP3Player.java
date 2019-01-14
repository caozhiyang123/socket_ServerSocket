package com.baifenjy.frame.mp3;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class MP3Player extends Thread{
    private String filename;
    private BufferedInputStream buffer = null;
    private Player player;
    
    public MP3Player(String filename){
        try {
            buffer = new BufferedInputStream(new FileInputStream(filename));
            player = new Player(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public synchronized void run() {
        try {
            player.play();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    

    public  void stopDisplay(){
        if(player!=null){
            player.close();
        }
    }
}