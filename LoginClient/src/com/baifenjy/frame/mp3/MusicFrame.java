package com.baifenjy.frame.mp3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baifenjy.io.Request;
import com.baifenjy.vo.Song;


public class MusicFrame extends JFrame{
    
    private float dval;
    
    public MusicFrame(){
        this.setTitle("大娱乐系统");
        this.setFont(new Font("宋体",Font.PLAIN,12));
        this.setBounds(200, 25,990, 690);
//        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                javax.swing.JOptionPane.showMessageDialog(MusicFrame.this, "welcome to here next time");
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        connect();
        
        final JPanel panel = new JPanel();
        
        createMainPanel(panel);
        this.add(panel);
        
        JPanel top_panel = new JPanel();
        createTopPanel(top_panel);
        panel.add(top_panel);
        
        JPanel left_panel = new JPanel();
        createLeftPanel(left_panel);
        panel.add(left_panel);
        
        //设置水平滚动条不显示,设置垂直滚动条一直显示
        jScrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        createFindMusicScrollPane(panel);
        
        my_favourite_jScrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        createFavouriteScrollPane(panel);
        
    }
    
    private int currentPage ;//当前页码 从0开始
    private final int pageSize = 20;//每页数据条数
    private int rowCount ;//数据库中总记录数
    private int pageCount;//计算的总页数

    private void createFavouriteScrollPane(JPanel panel) {
        my_favourite_jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
        my_favourite_jScrollPane.setBounds(200, 70, 777, 590);
        //定义一个JPanel面板 以整合其他面板
        final JPanel main_center_panel = new JPanel();
        main_center_panel.setPreferredSize(new Dimension(1000,2000)); 
        main_center_panel.setLayout(null);
        
        my_favourite_jScrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));
        my_favourite_jScrollPane.setViewportView(main_center_panel);
        panel.add(my_favourite_jScrollPane);
        
        JPanel top_in_main_my_fav_song = new JPanel();
        main_center_panel.add(top_in_main_my_fav_song);
        createTopInMainOfMyFavSongPanel(top_in_main_my_fav_song);
        
        JPanel below_panel_in_my_fav_song = new JPanel();
        main_center_panel.add(below_panel_in_my_fav_song);
        createBelowPanelInMyFavSong(below_panel_in_my_fav_song);
    }

    private void createTopInMainOfMyFavSongPanel(JPanel top_in_main_my_fav_song) {
        top_in_main_my_fav_song.setLayout(null);
        top_in_main_my_fav_song.setBounds(0, 0, 780, 250);
        top_in_main_my_fav_song.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        
    }
    
    private JTable song_list = null;
    private DefaultTableModel  defaultTableModel = null;
    private Vector rowData;
    private Vector columnName;
    
    private void createBelowPanelInMyFavSong(JPanel below_panel_in_my_fav_song) {
        below_panel_in_my_fav_song.setLayout(null);
        below_panel_in_my_fav_song.setBounds(0, 250, 800, 700);
        below_panel_in_my_fav_song.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        createColumnName(below_panel_in_my_fav_song);
        
        rowData = new Vector();
        columnName = new Vector();
        querySongListFromServer(rowData, columnName,0);
        System.out.println(pageCount+"总页数");
        
        defaultTableModel = new DefaultTableModel(rowData,columnName);
        song_list = new JTable(defaultTableModel);
        createViewTabel(below_panel_in_my_fav_song);
    }
    
    Socket socket;
    DataOutputStream messageDos;
    DataInputStream messageDis;
    private static final String IP = "127.0.0.1";
    private static final int ORDER_PORT = 8899;
    
    public void connect() {
        try {
            socket = new Socket(IP, ORDER_PORT);
            System.out.println("一个客户端登陆中....!");
            messageDos = new DataOutputStream(socket.getOutputStream());
            messageDis = new DataInputStream(socket.getInputStream());

        } catch (ConnectException e) {
            System.out.println("服务端异常.........");
            System.out.println("请确认服务端是否开启.........");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void disconnect() {
        try {
            if(messageDis != null){
                messageDis.close();
            }
            if (messageDos != null){
                messageDos.close();
            }
            if (socket != null){
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createViewTabel(JPanel below_panel_in_my_fav_song) {
        song_list.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        song_list.setBounds(0, 20, 780, 600);
        song_list.getColumnModel().getColumn(0).setPreferredWidth(50);
        song_list.getColumnModel().getColumn(1).setPreferredWidth(70);
        song_list.getColumnModel().getColumn(2).setPreferredWidth(150);
        song_list.getColumnModel().getColumn(3).setPreferredWidth(150);
        song_list.getColumnModel().getColumn(4).setPreferredWidth(150);
        song_list.getColumnModel().getColumn(5).setPreferredWidth(100);
        song_list.getColumnModel().getColumn(6).setPreferredWidth(170);
        song_list.setRowHeight(28);
        song_list.setForeground(Color.white);
        song_list.setBackground(Color.lightGray);
//        song_list.setFont(new Font("黑体",Font.PLAIN,14));
        song_list.setShowGrid(true);
        song_list.setGridColor(Color.GRAY);
//        song_list.validate();
//        song_list.updateUI(); 
        DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
        r.setHorizontalAlignment(JLabel.CENTER);   
        song_list.setDefaultRenderer(Object.class, r);
        
        below_panel_in_my_fav_song.add(song_list);
        
        song_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                song_list.setEnabled(false);
                if(e.getClickCount()%2 == 0){
                    int rowIndex = song_list.getSelectedRow();
                    int[] selectedColumnIndexs = {0,1,2,3,4,5,6};
                    List<String> rows = new ArrayList<String>();
                    for (int columnIndex : selectedColumnIndexs) {
                        rows.add(song_list.getValueAt(rowIndex == -1?0:rowIndex, columnIndex == -1?0:columnIndex).toString());
                    }
                    //播放音乐
                    if(songFlags.get(rows.get(0))!= null && songFlags.get(rows.get(0))){
                        stopDisplay(rows);
                    }else{
                        disPlayMusic(rows);
                    }
                    song_list.setEnabled(true);
                }
            }
        });
    }
    
    private void stopDisplay(List<String> rows){
        songFlags.put(Long.parseLong(rows.get(0)),false);
        mp3Player.stopDisplay();
    }
    
    private Map<Long,Boolean> songFlags = new HashMap<Long,Boolean>();
    private MP3Player mp3Player;
    
    private void disPlayMusic(List<String> rows) {
        songFlags.put(Long.parseLong(rows.get(0)),true);
        mp3Player = new MP3Player(rows.get(rows.size()-1).toString());
        mp3Player.start();
    }

    private void createColumnName(JPanel below_panel_in_my_fav_song) {
        //column name
        JTextField id_text_field = new JTextField("序号");
        id_text_field.setBounds(0, 0, 50, 20);
        id_text_field.setEnabled(false);
        id_text_field.setBorder(null);
        id_text_field.setHorizontalAlignment(SwingConstants.CENTER);
        below_panel_in_my_fav_song.add(id_text_field);
        
        JTextField operation_text_field = new JTextField("操作");
        operation_text_field.setBounds(40, 0, 80, 20);
        operation_text_field.setEnabled(false);
        operation_text_field.setBorder(null);
        operation_text_field.setHorizontalAlignment(SwingConstants.CENTER);
        below_panel_in_my_fav_song.add(operation_text_field);
        
        JTextField title_text_field = new JTextField("音乐标题");
        title_text_field.setBounds(110, 0, 150, 20);
        title_text_field.setEnabled(false);
        title_text_field.setBorder(null);
        title_text_field.setHorizontalAlignment(SwingConstants.CENTER);
        below_panel_in_my_fav_song.add(title_text_field);
        
        JTextField singer_text_field = new JTextField("歌手");
        singer_text_field.setBounds(250, 0, 150, 20);
        singer_text_field.setEnabled(false);
        singer_text_field.setBorder(null);
        singer_text_field.setHorizontalAlignment(SwingConstants.CENTER);
        below_panel_in_my_fav_song.add(singer_text_field);
        
        JTextField album_text_field = new JTextField("专辑");
        album_text_field.setBounds(390, 0, 150, 20);
        album_text_field.setEnabled(false);
        album_text_field.setBorder(null);
        album_text_field.setHorizontalAlignment(SwingConstants.CENTER);
        below_panel_in_my_fav_song.add(album_text_field);
        
        JTextField time_text_field = new JTextField("时长");
        time_text_field.setBounds(530, 0, 100, 20);
        time_text_field.setEnabled(false);
        time_text_field.setBorder(null);
        time_text_field.setHorizontalAlignment(SwingConstants.CENTER);
        below_panel_in_my_fav_song.add(time_text_field);

        JTextField local_path_field = new JTextField("本地路径");
        local_path_field.setBounds(620, 0, 170, 20);
        local_path_field.setEnabled(false);
        local_path_field.setBorder(null);
        local_path_field.setBackground(Color.GRAY);
        local_path_field.setHorizontalAlignment(SwingConstants.CENTER);
        below_panel_in_my_fav_song.add(local_path_field);
    
    }

    private void querySongListFromServer(Vector rowData, Vector columnName, int currentPage) {
        try {
            messageDos.writeUTF(Request.PAGE_QUERY_SONGS);
            String response = messageDis.readUTF();
            if(Request.PAGE_QUERY_SONGS.equals(response)){
                Song songVO = new Song();
                songVO.setCurrentPage(currentPage);
                String songStr = JSON.toJSONString(songVO);
                messageDos.writeUTF(songStr);
                songStr = messageDis.readUTF();
                JSONObject jsonObj = (JSONObject) JSON.parse(songStr);
                JSONArray rowDatas = (JSONArray) jsonObj.get("rowData");
                for (Object rowDataObj : rowDatas) {
                    Vector rowLineData = new Vector();
                    JSONArray jsonLine = (JSONArray)rowDataObj;
                    for (Object object : jsonLine) {
                        rowLineData.add(object.toString());
                    }
                    rowData.add(rowLineData);
                }
                JSONArray columnNameArr = (JSONArray) jsonObj.get("columnName");
                for (Object obj : columnNameArr) {
                    columnName.add(obj.toString());
                }
                this.pageCount = Integer.parseInt(jsonObj.get("pageCount").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createFindMusicScrollPane(final JPanel panel) {
        jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
        jScrollPane.setBounds(200, 70, 777, 590);
        //定义一个JPanel面板 以整合其他面板
        final JPanel main_center_panel = new JPanel();
        main_center_panel.setPreferredSize(new Dimension(1000,2000)); 
        main_center_panel.setLayout(null);
        jScrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));
        jScrollPane.setViewportView(main_center_panel);
        panel.add(jScrollPane);
        
        JPanel top_in_main = new JPanel();
        main_center_panel.add(top_in_main);
        createTopInMainPanel(top_in_main);
        
        
        first_panel = new JPanel();
        main_center_panel.add(first_panel);
        createFirstPanel(first_panel);
        
        second_panel = new JPanel();
        main_center_panel.add(second_panel);
        createSecond_panel(second_panel);
        
        third_panel = new JPanel();
        main_center_panel.add(third_panel);
        createThird_panel(third_panel);
        
        fourth_panel = new JPanel();
        main_center_panel.add(fourth_panel);
        createFourth_panel(fourth_panel);
        
        fifth_panel = new JPanel();
        main_center_panel.add(fifth_panel);
        createFifth_panel(fifth_panel);
        
        sixth_panel = new JPanel();
        main_center_panel.add(sixth_panel);
        createSixth_panel(sixth_panel);
    }
    
    private JScrollPane jScrollPane = null;
    
    private JScrollPane my_favourite_jScrollPane = null;
    
    private JPanel first_panel = null;
    private JPanel second_panel = null;
    private JPanel third_panel = null;
    private JPanel fourth_panel = null;
    private JPanel fifth_panel = null;
    private JPanel sixth_panel = null;

    private void createFirstPanel(JPanel first_panel) {
        first_panel.setBounds(0, 60, 757, 530);
        first_panel.setBorder(new TitledBorder(null, "个性推荐", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null) );
    }

    private void createSecond_panel(JPanel second_panel) {
        second_panel.setBounds(0, 60, 757, 530);
        second_panel.setBorder(new TitledBorder(null, "歌单", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null) );
        
    }

    private void createThird_panel(JPanel third_panel) {
        third_panel.setBounds(0, 60, 757, 530);
        third_panel.setBorder(new TitledBorder(null, "主播电台", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null) );
        
    }

    private void createFourth_panel(JPanel fourth_panel) {
        fourth_panel.setBounds(0, 60, 757, 530);
        fourth_panel.setBorder(new TitledBorder(null, "排行榜", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null) );
        
    }

    private void createFifth_panel(JPanel fifth_panel) {
        fifth_panel.setBounds(0, 60, 757, 530);
        fifth_panel.setBorder(new TitledBorder(null, "歌手", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null) );
        
    }

    private void createSixth_panel(JPanel sixth_panel) {
        sixth_panel.setBounds(0, 60, 757, 530);
        sixth_panel.setBorder(new TitledBorder(null, "最新音乐", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null) );
    }

    private void createTopInMainPanel(JPanel top_in_main) {
        top_in_main.setLayout(null);
        top_in_main.setBounds(50, 0, 650, 50);
        top_in_main.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        
        JButton first_button = new JButton("个性推荐");
        first_button.setBounds(100, 22, 80, 30);
        first_button.setBorderPainted(false);
        top_in_main.add(first_button);
        first_button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                first_panel.setVisible(true);
                second_panel.setVisible(false);
                third_panel.setVisible(false);
                fourth_panel.setVisible(false);
                fifth_panel.setVisible(false);
                sixth_panel.setVisible(false);
            }
        });
        
        JButton second_button = new JButton("歌单");
        second_button.setBounds(180, 22, 80, 30);
        second_button.setBorderPainted(false);
        top_in_main.add(second_button);
        second_button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                first_panel.setVisible(false);
                second_panel.setVisible(true);
                third_panel.setVisible(false);
                fourth_panel.setVisible(false);
                fifth_panel.setVisible(false);
                sixth_panel.setVisible(false);
            }
        });
        
        JButton third_button = new JButton("主播电台");
        third_button.setBounds(260, 22, 80, 30);
        third_button.setBorderPainted(false);
        top_in_main.add(third_button);
        third_button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                first_panel.setVisible(false);
                second_panel.setVisible(false);
                third_panel.setVisible(true);
                fourth_panel.setVisible(false);
                fifth_panel.setVisible(false);
                sixth_panel.setVisible(false);
            }
        });
        
        JButton fouth_button = new JButton("排行榜");
        fouth_button.setBounds(340, 22, 80, 30);
        fouth_button.setBorderPainted(false);
        top_in_main.add(fouth_button);
        fouth_button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                first_panel.setVisible(false);
                second_panel.setVisible(false);
                third_panel.setVisible(false);
                fourth_panel.setVisible(true);
                fifth_panel.setVisible(false);
                sixth_panel.setVisible(false);
            }
        });
        
        JButton fifth_button = new JButton("歌手");
        fifth_button.setBounds(420, 22, 80, 30);
        fifth_button.setBorderPainted(false);
        top_in_main.add(fifth_button);
        fifth_button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                first_panel.setVisible(false);
                second_panel.setVisible(false);
                third_panel.setVisible(false);
                fourth_panel.setVisible(false);
                fifth_panel.setVisible(true);
                sixth_panel.setVisible(false);
            }
        });
        
        JButton sixth_button = new JButton("最新音乐");
        sixth_button.setBounds(500, 22, 80, 30);
        sixth_button.setBorderPainted(false);
        top_in_main.add(sixth_button);
        sixth_button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                first_panel.setVisible(false);
                second_panel.setVisible(false);
                third_panel.setVisible(false);
                fourth_panel.setVisible(false);
                fifth_panel.setVisible(false);
                sixth_panel.setVisible(true);
            }
        });
        
    }

    private void createMainPanel(JPanel panel) {
        panel.setPreferredSize(new Dimension(1200,1000)); 
        panel.setLayout(null);
        panel.setBorder(null);
        panel.setBounds(0, 0, 983, 660);
        panel.setVisible(true);
    }


    private void createTopPanel(JPanel top_panel) { 
        top_panel.setLayout(null);
        top_panel.setBounds(0, 0, 985, 70);
        top_panel.setBorder(null);
        top_panel.setBackground(new Color(220,20,60));
        top_panel.setVisible(true);
        
        JLabel logIcon = new JLabel("本地音乐");
        logIcon.setBounds(20, 15, 130, 40);
        logIcon.setForeground(Color.WHITE);
        logIcon.setFont(new Font("宋体",Font.PLAIN,15));
        top_panel.add(logIcon);
        
        JTextField query_music_field = new JTextField("搜索音乐");
        query_music_field.setBounds(200, 20, 150, 30);
        top_panel.add(query_music_field);
    }
    
    private void createLeftPanel(JPanel left_panel) {
        left_panel.setVisible(true);
        left_panel.setLayout(null);
        left_panel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));
        left_panel.setBounds(0, 70, 200, 590);
        createMenuBar(left_panel);
    }

    
    private void creatRightPanel(JPanel rightPanel) {
        rightPanel.setLayout(null);
        rightPanel.setBounds(200, 70, 783, 590);
        rightPanel.setBorder(null);
        rightPanel.setVisible(true);
    }
    
    private void createMenuBar(JPanel leftPanel) {
        JLabel recommend_label = new JLabel("推荐");
        recommend_label.setFont(new Font("宋体",Font.PLAIN,12));
        recommend_label.setBounds(10, 20, 180, 30);
        leftPanel.add(recommend_label);
        
        JButton find_music_button = new JButton("发现音乐");
        find_music_button.setFont(new Font("宋体",Font.PLAIN,12));
        find_music_button.setForeground(Color.WHITE);
        find_music_button.setBounds(10, 50, 180, 30);
        find_music_button.setBorder(null);
        find_music_button.setBackground(Color.GRAY);
        leftPanel.add(find_music_button);
        find_music_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jScrollPane.setVisible(true);
                my_favourite_jScrollPane.setVisible(false);
            }
        });
        
        JButton private_FM_button = new JButton("私人FM");
        private_FM_button.setFont(new Font("宋体",Font.PLAIN,12));
        private_FM_button.setForeground(Color.WHITE);
        private_FM_button.setBounds(10, 80, 180, 30);
        private_FM_button.setBorder(null);
        private_FM_button.setBackground(Color.GRAY);
        leftPanel.add(private_FM_button);
        private_FM_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        JButton mv_button = new JButton("视频");
        mv_button.setFont(new Font("宋体",Font.PLAIN,12));
        mv_button.setForeground(Color.WHITE);
        mv_button.setBounds(10, 110, 180, 30);
        mv_button.setBorder(null);
        mv_button.setBackground(Color.GRAY);
        leftPanel.add(mv_button);
        mv_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        JButton friends_button = new JButton("朋友");
        friends_button.setFont(new Font("宋体",Font.PLAIN,12));
        friends_button.setForeground(Color.WHITE);
        friends_button.setBounds(10, 140, 180, 30);
        friends_button.setBorder(null);
        friends_button.setBackground(Color.GRAY);
        leftPanel.add(friends_button);
        friends_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        JLabel my_music_label = new JLabel("我的音乐");
        my_music_label.setFont(new Font("宋体",Font.PLAIN,12));
        my_music_label.setBounds(10, 170, 180, 30);
        leftPanel.add(my_music_label);
        
        JButton local_music_button = new JButton("本地音乐");
        local_music_button.setFont(new Font("宋体",Font.PLAIN,12));
        local_music_button.setForeground(Color.WHITE);
        local_music_button.setBounds(10, 200, 180, 30);
        local_music_button.setBorder(null);
        local_music_button.setBackground(Color.GRAY);
        leftPanel.add(local_music_button);
        local_music_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        JButton down_manage_button = new JButton("下载管理");
        down_manage_button.setFont(new Font("宋体",Font.PLAIN,12));
        down_manage_button.setForeground(Color.WHITE);
        down_manage_button.setBounds(10, 230, 180, 30);
        down_manage_button.setBorder(null);
        down_manage_button.setBackground(Color.GRAY);
        leftPanel.add(down_manage_button);
        down_manage_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        JLabel build_song_label = new JLabel("创建的单歌");
        build_song_label.setFont(new Font("宋体",Font.PLAIN,12));
        build_song_label.setBounds(10, 260, 180, 30);
        leftPanel.add(build_song_label);
        
        JButton my_like_songs_button = new JButton("我喜欢的音乐");
        my_like_songs_button.setFont(new Font("宋体",Font.PLAIN,12));
        my_like_songs_button.setForeground(Color.WHITE);
        my_like_songs_button.setBounds(10, 290, 180, 30);
        my_like_songs_button.setBorder(null);
        my_like_songs_button.setBackground(Color.GRAY);
        leftPanel.add(my_like_songs_button);
        my_like_songs_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                my_favourite_jScrollPane.setVisible(true);
                jScrollPane.setVisible(false);
            }
        });
        
    }
    
    private static MusicFrame musicFrame = null;
    
    public static void main(String[] args) {
        setUI();
        musicFrame = new MusicFrame();
    }

    private static void setUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
