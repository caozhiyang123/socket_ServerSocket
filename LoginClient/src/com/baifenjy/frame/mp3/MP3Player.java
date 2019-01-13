package com.baifenjy.frame.mp3;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MP3Player {
    private String filename;
    private BufferedInputStream buffer = null;
    private Player player;
    
    private static MP3Player mpsPlayer = null;
    private MP3Player(){}
    public static MP3Player getInstance(){
        if(mpsPlayer == null){
            synchronized (MP3Player.class) {
                mpsPlayer = new MP3Player();
                return mpsPlayer;
            }
        }
        return mpsPlayer;
    }

    public void init(String filename) {
        try {
            this.filename = filename;
            buffer = new BufferedInputStream(new FileInputStream(filename));
            player = new Player(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void play() {
        try {
            player.play();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    

    public  void disPlaySong(String localPath,long id) {
        init(localPath);
        play(); 
    }
    
    public  void stopDisplay(){
        if(player!=null){
            player.close();
        }
    }
}