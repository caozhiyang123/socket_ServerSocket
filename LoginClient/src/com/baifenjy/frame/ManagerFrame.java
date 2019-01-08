package com.baifenjy.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baifenjy.io.Request;
import com.baifenjy.io.Response;
import com.baifenjy.utils.DatePickUtils;
import com.baifenjy.utils.ThreadLocalSimple;
import com.baifenjy.vo.Message;
import com.baifenjy.vo.MessageVO;
import com.eltima.components.ui.DatePicker;
import com.mysql.jdbc.StringUtils;

public class ManagerFrame extends JFrame{
    
    public static void main(String[] args) {
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            
            java.awt.EventQueue.invokeLater(new Runnable()
            {
                
                @Override
                public void run()
                {
                    try
                    {
                        //UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceBusinessLookAndFeel");
                        //UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel");
                        //UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceSaharaLookAndFeel");
                        //UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel");
                        //UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel");
                        //UIManager.setLookAndFeel(SubstanceLookAndFeel.SKIN_PROPERTY);
                        
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    } 
                    
                }
            });
          
          new ManagerFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
  }

  private  ManagerFrame() {
      this.setTitle("缁狅紕鎮婄化鑽ょ埠_v2.0_娑撳﹥鎹ｉ崝娑㈡妇閺佹瑨鍋涚粔鎴炲Η閺堝妾洪崗顒�寰僟[www.baifenjy.com]");
      this.setFont(new Font("鐎瑰缍�",Font.PLAIN,12));
      this.setBounds(300, 200,1000, 800);
      this.setVisible(true);
      this.addWindowListener(new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
              super.windowClosing(e);
              e.getWindow().dispose();
              System.exit(0);
          }
      });
      
      final JScrollPane jScrollPane = new JScrollPane();
      //鐠佸墽鐤嗗鏉戦挬濠婃艾濮╅弶鈥茬瑝閺勫墽銇�
      jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      //鐠佸墽鐤嗛崹鍌滄纯濠婃艾濮╅弶鈥茬閻╁瓨妯夌粈锟�
      jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      jScrollPane.getVerticalScrollBar().setUnitIncrement(20);
      //鐎规矮绠熸稉锟芥稉鐙玃anel闂堛垺婢� 娴犮儲鏆ｉ崥鍫濆従娴犳牠娼伴弶锟�
      final JPanel panel = new JPanel();
      panel.setPreferredSize(new Dimension(1200,1000)); 
      panel.setLayout(null);
      jScrollPane.setViewportView(panel);
      this.add(jScrollPane);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      connect();

      final JPanel rightPanel = new JPanel();
      panel.add(rightPanel);
      createRightPanel(rightPanel);

      final JPanel leftPanel = new JPanel();
      panel.add(leftPanel);
      createLeftPanel(leftPanel,rightPanel);
      
      
      this.addWindowListener(new WindowAdapter() {
          @Override
          public void windowClosed(WindowEvent e) {
              disconnect();
          }
      });
      
  }
  
  Socket socket;
  DataOutputStream messageDos;
  DataInputStream messageDis;
  private static final String IP = "127.0.0.1";
  private static final int ORDER_PORT = 8888;
  
  public void connect() {
      try {
          socket = new Socket(IP, ORDER_PORT);
          System.out.println("娑擄拷娑擃亜顓归幋椋庮伂閻у妾版稉锟�....!");
          messageDos = new DataOutputStream(socket.getOutputStream());
          messageDis = new DataInputStream(socket.getInputStream());

      } catch (ConnectException e) {
          System.out.println("閺堝秴濮熺粩顖氱磽鐢拷.........");
          System.out.println("鐠囬鈥樼拋銈嗘箛閸旓紕顏弰顖氭儊瀵拷閸氾拷.........");
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

    private void createLeftPanel(JPanel leftPanel,JPanel rightPanel) {
        leftPanel.setLayout(null);
        leftPanel.setBorder(new TitledBorder(null, "缂冩垹鐝粻锛勬倞", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        leftPanel.setBounds(0, 0, 200, 1000);
        leftPanel.setVisible(true);
        createMenuBar(leftPanel,rightPanel);
        
    }
    
    private void createRightPanel(JPanel leftPanel) {
        leftPanel.setLayout(null);
        leftPanel.setBorder(new TitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        leftPanel.setBounds(200, 50, 1100, 948);
        leftPanel.setVisible(true);
        
        JPanel firstPanel = new JPanel();
        leftPanel.add(firstPanel);
        createFirstPanel(firstPanel);
        
        JPanel secondPanel = new JPanel();
        leftPanel.add(secondPanel);
        createSecondPanel(secondPanel);
        
        JPanel thirdPanel = new JPanel();
        leftPanel.add(thirdPanel);
        createThirdPanel(thirdPanel);
        
    }


    private void createFirstPanel(JPanel firstPanel) {
        firstPanel.setLayout(null);
        firstPanel.setBorder(new TitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        firstPanel.setBounds(0, 0, 1100, 30);
        firstPanel.setVisible(true);
        
        JButton addNewMessageButton = new JButton("濞ｈ濮為弬鎵殌鐟凤拷");
        firstPanel.add(addNewMessageButton);
        createAddNewMessageButton(addNewMessageButton);
        
        JButton deleteMessageButton = new JButton("閸掔娀娅庨悾娆掆枅");
        firstPanel.add(deleteMessageButton);
        createDeleteMessageButton(deleteMessageButton);
    }
    

    private void createDeleteMessageButton(JButton deleteMessageButton) {
        deleteMessageButton.setFont(new Font("鐎瑰缍�",Font.PLAIN,12));
        deleteMessageButton.setBounds(140, 5, 100, 20);
        deleteMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 闁瀚ㄧ憰浣稿灩闂勩倗娈戦悾娆掆枅
                
            }
        });
    }

    private void createAddNewMessageButton(JButton addNewMessageButton) {
        addNewMessageButton.setFont(new Font("鐎瑰缍�",Font.PLAIN,12));
        addNewMessageButton.setBounds(10, 5, 100, 20);
        addNewMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSaveFrame();
            }
            
        });
    }
    
    private void createSaveFrame() {
        message_frame_title = "娣囨繂鐡ㄩ悾娆掆枅";
        createFrame();
        id_text_field.setText("");
        name_field.setText("");
        message_area.setText("");
        call_area.setText("");
    }

    private DatePicker datePicker = DatePickUtils.getDatePicker();
    private JTextField name_field  = new JTextField();
    private JTextField id_text_field = new JTextField();
    private JTextArea message_area = new JTextArea();
    private JTextArea call_area = new JTextArea();
    
    private String message_frame_title ;
    
    private void createFrame() {
        //閸掓稑缂撴稉锟芥稉鐙獸rame缁愭褰�
        JFrame addNewMessageFrame = new JFrame(message_frame_title);
        addNewMessageFrame.setFont(new Font("鐎瑰缍�",Font.PLAIN,12));
        addNewMessageFrame.setResizable(false);
        addNewMessageFrame.setBounds(400,50,650,700);
        addNewMessageFrame.setVisible(true);
//        addNewMessageFrame.setAlwaysOnTop(true);
//        addNewMessageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //dispose();
        JScrollPane newMessageScrollPanel = new JScrollPane();
        //鐠佸墽鐤嗗鏉戦挬濠婃艾濮╅弶鈥茬瑝閺勫墽銇�
        newMessageScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //鐠佸墽鐤嗛崹鍌滄纯濠婃艾濮╅弶鈥茬閻╁瓨妯夌粈锟�
        newMessageScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        newMessageScrollPanel.getVerticalScrollBar().setUnitIncrement(20);
        addNewMessageFrame.add(newMessageScrollPanel);
        
        JPanel newMessagePanel = new JPanel();
        newMessagePanel.setPreferredSize(new Dimension(1200,800)); 
        newMessagePanel.setLayout(null);
        newMessageScrollPanel.setViewportView(newMessagePanel);
        
        JLabel time_label = new JLabel("閻ｆ瑨鈻堥弮鍫曟？:");
        time_label.setFont(new Font("鐎瑰缍�",Font.PLAIN,12));
        time_label.setBounds(10,10,70,35);
        newMessagePanel.add(time_label);
        
        
        datePicker.setBounds(80, 10, 200, 35);
        newMessagePanel.add(datePicker);
        
        JLabel name_label = new JLabel("婵挸鎮�:",SwingConstants.CENTER);
        name_label.setFont(new Font("鐎瑰缍�",Font.PLAIN,12));
        name_label.setBounds(10, 50, 70, 35);
        newMessagePanel.add(name_label);
        
        name_field.setBounds(80, 50, 200, 35);
        newMessagePanel.add(name_field);
        
        id_text_field.setBounds(285, 50, 200, 35);
        id_text_field.setEnabled(false);
        id_text_field.setVisible(false);
        newMessagePanel.add(id_text_field);
        
        JButton message_button = new JButton(" 閻ｆ瑨鈻堥崘鍛啇:");
        message_button.setFont(new Font("鐎瑰缍�",Font.PLAIN,12));
        message_button.setHorizontalAlignment(SwingConstants.LEFT);
        message_button.setBounds(10, 95, 600, 20);
        message_button.setBorder(null);
        message_button.setBackground(Color.lightGray);
        message_button.setEnabled(false);
        newMessagePanel.add(message_button);
        
        message_area.setBounds(10, 115, 600, 280);
        newMessagePanel.add(message_area);
        
        JLabel call_label = new JLabel("閸ョ偛顦查崘鍛啇:",SwingConstants.LEFT);
        call_label.setFont(new Font("鐎瑰缍�",Font.PLAIN,12));
        call_label.setBounds(10, 405, 600, 20);
        newMessagePanel.add(call_label);
        
        call_area.setBounds(10, 425, 600, 280);
        newMessagePanel.add(call_area);
        
        JButton save_button = new JButton("娣囨繂鐡�");
        save_button.setFont(new Font("鐎瑰缍�",Font.PLAIN,12));
        save_button.setBounds(250, 715, 80, 30);
        newMessagePanel.add(save_button);
        save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = (Date) datePicker.getValue();
                String time = ThreadLocalSimple.df.get().format(date);
                String name = name_field.getText();
                String id  = id_text_field.getText();
                String message = message_area.getText();
                String callMessage = call_area.getText();
                System.out.println(time+","+name+","+message);
                boolean flag = false;
                if(StringUtils.isNullOrEmpty(time.trim())){
                    javax.swing.JOptionPane.showMessageDialog(ManagerFrame.this, "閺冦儲婀¤箛鍛淬�忔繅顐㈠晸");
                    flag = true;
                    return;
                }
                if(flag == false && StringUtils.isNullOrEmpty(name.trim())){
                    javax.swing.JOptionPane.showMessageDialog(ManagerFrame.this, "婵挸鎮曡箛鍛淬�忔繅顐㈠晸");
                    flag = true;
                    return;
                }
                if(flag == false && StringUtils.isNullOrEmpty(message.trim())){
                    javax.swing.JOptionPane.showMessageDialog(ManagerFrame.this, "閻ｆ瑨鈻堣箛鍛淬�忔繅顐㈠晸");
                    flag = true;
                    return;
                }
                addNewMessageFrame.dispose();
                if(!flag){
                    try {
                        if(!StringUtils.isNullOrEmpty(id.trim())){
                            messageDos.writeUTF(Request.UPDATE_MESSAGE);
                            String response = messageDis.readUTF();
                            if(Request.UPDATE_MESSAGE.equals(response)){
                                MessageVO messageVO = new MessageVO();
                                messageVO.setId(Long.parseLong(id));
                                messageVO.setTime(time);
                                messageVO.setName(name);
                                messageVO.setMessage(message);
                                messageVO.setCallMessage(callMessage);
                                String mess = JSON.toJSONString(messageVO);
                                messageDos.writeUTF(mess);
                                response = messageDis.readUTF();
                                if(Response.SUCCESS.equals(response)){
                                    //閺囧瓨鏌婇弫鐗堝祦閹存劕濮涢柌宥呭晸閺屻儴顕楅幍锟介張澶嬫殶閹癸拷
                                    requeryData();
                                }
                            }
                            return;
                        }
                        messageDos.writeUTF(Request.SAVE_MESSAGE);
                        String response = messageDis.readUTF();
                        if(Request.SAVE_MESSAGE.equals(response)){
                            MessageVO messageVO = new MessageVO();
                            messageVO.setTime(time);
                            messageVO.setName(name);
                            messageVO.setMessage(message);
                            messageVO.setCallMessage(callMessage);
                            String mess = JSON.toJSONString(messageVO);
                            messageDos.writeUTF(mess);
                            response = messageDis.readUTF();
                            if(Response.SUCCESS.equals(response)){
                                //娣囨繂鐡ㄩ幋鎰闁插秴鍟撻弻銉嚄閹碉拷閺堝鏆熼幑锟�
                                requeryData();
                            }
                        }
                        
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    };
                }
            }
            private void requeryData() throws IOException {
                rowData.removeAllElements();
                queryDataFromServer(rowData, columnName,0);
                jt.validate();
                jt.updateUI();
                String desc   =  DESC_LABEL_TEXT.replaceAll("currentPage", Integer.toString(currentPage+1));// beging from 0
                desc = desc.replaceAll("pageCount", Integer.toString(pageCount));
                page_des_label.setText(desc);
                changeButtonEnabled(first_page_button, pre_page_button, next_page_button, last_page_button);
            }
        });
        
        JButton cancel_button = new JButton("閸欐牗绉�");
        cancel_button.setFont(new Font("鐎瑰缍�",Font.PLAIN,12));
        cancel_button.setBounds(340, 715, 80, 30);
        newMessagePanel.add(cancel_button);
        cancel_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //閸忔娊妫磋ぐ鎾冲缁愭褰�
                addNewMessageFrame.dispose();
            }
        });
        
    }

    private void createSecondPanel(JPanel secondPanel) {
        secondPanel.setLayout(null);
        secondPanel.setBorder(new TitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        secondPanel.setBounds(0, 30, 1100, 30);
        secondPanel.setVisible(true);
        
        JLabel name_label = new JLabel("婵挸鎮�:");
        name_label.setBounds(10, 5, 50, 20);
        name_label.setFont(new Font("鐎瑰缍�",Font.PLAIN,12));
        secondPanel.add(name_label);
        
        JTextField name_text = new JTextField();
        name_text.setBounds(50, 5, 120, 20);
        name_text.setFont(new Font("鐎瑰缍�",Font.PLAIN,12));
        secondPanel.add(name_text);
        name_text.addKeyListener(new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                String name = name_text.getText();
                currentPage = 0;
                rowData.removeAllElements();
                //query first page data from server
                try {
                    queryDataFromServerByName(rowData,columnName,currentPage,name.trim());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                //refresh data to JTable
                jt.validate();
                jt.updateUI();
                
                String desc = DESC_LABEL_TEXT.replaceAll("currentPage", Integer.toString(currentPage+1));// beging from 0
                desc = desc.replaceAll("pageCount", Integer.toString(pageCount));
                page_des_label.setText(desc);
                
                changeButtonEnabled(first_page_button, pre_page_button, next_page_button, last_page_button);
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
                
            }
        });
        
        JLabel start_label = new JLabel("閺冨爼妫�:");
        start_label.setBounds(190, 5, 50, 20);
        start_label.setFont(new Font("鐎瑰缍�",Font.PLAIN,12));
        secondPanel.add(start_label);
        
        DatePicker start_datePicker = DatePickUtils.getDatePicker();
        start_datePicker.setBounds(230, 5, 200, 20);
        secondPanel.add(start_datePicker);
        
        
        JLabel end_label = new JLabel("閳ワ拷");
        end_label.setBounds(465, 5, 50, 20);
        secondPanel.add(end_label);
        
        DatePicker end_datePicker = DatePickUtils.getDatePicker();
        end_datePicker.setBounds(510, 5, 200, 20);
        secondPanel.add(end_datePicker);
        
        JButton query_button = new JButton("閺屻儴顕�");
        query_button.setFont(new Font("鐎瑰缍�",Font.PLAIN,12));
        query_button.setBounds(730, 5, 100, 20);
        secondPanel.add(query_button);
        query_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //濡紕纭﹂弻銉嚄
                String text = name_text.getText();
                
            }
        });
        
        
    }
    
    private DefaultTableModel defaultTableModel = null;
    private JTable jt = null;
    private JScrollPane jsp = null;
    
    private int currentPage ;//瑜版挸澧犳い鐢电垳 娴狅拷0瀵拷婵拷
    private final int pageSize = 20;//濮ｅ繘銆夐弫鐗堝祦閺夆剝鏆�
    private int rowCount ;//閺佺増宓佹惔鎾茶厬閹槒顔囪ぐ鏇熸殶
    private int pageCount;//鐠侊紕鐣婚惃鍕拷濠氥�夐弫锟�
    
    
    private Vector rowData;
    private Vector columnName;
    
    private String DESC_LABEL_TEXT = "瑜版挸澧犳い鍏歌礋缁楃惂urrentPage妞わ拷/閸忕湑ageCount妞わ拷";
    
    JButton first_page_button = null;
    JButton pre_page_button = null;
    JButton next_page_button = null;
    JButton last_page_button = null;
    JLabel page_des_label = null;
    
    private void createThirdPanel(JPanel thirdPanel) {
        thirdPanel.setLayout(null);
        thirdPanel.setBorder(new TitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        thirdPanel.setBounds(0, 60, 1100, 900);
        thirdPanel.setVisible(true);
        
        //query rowData and columnName from server
        rowData = new Vector();
        columnName = new Vector();
        /*閺嬪嫰锟界姵鏆熼幑锟� test
         * for(int i=0;i<30;i++){
            Vector rows = new Vector();
            for (int j = 0; j < 5; j++) {
                rows.add((currentPage+1)+" page data "+i+" "+j);
            }
            rowData.add(rows);
        }
        for (int i = 0; i < 5; i++) {
            columnName.add("column name "+i);
        }*/
        //TODO query data from server
        //initial table data from page 0
        try {
            queryDataFromServer(rowData, columnName,0);
            System.out.println(pageCount+"閹銆夐弫锟�");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO query total row count from server
//        rowCount = 100;
//        pageCount = (rowCount + pageSize - 1)/pageSize;
        
        jsp = new JScrollPane();
      //鐠佸墽鐤嗗鏉戦挬濠婃艾濮╅弶鈥茬瑝閺勫墽銇�
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //鐠佸墽鐤嗛崹鍌滄纯濠婃艾濮╅弶鈥茬閻╁瓨妯夌粈锟�
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.getVerticalScrollBar().setUnitIncrement(20);
        //鐎规矮绠熸稉锟芥稉鐙玃anel闂堛垺婢� 娴犮儲鏆ｉ崥鍫濆従娴犳牠娼伴弶锟�
        final JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1200,1000)); 
        panel.setLayout(null);
        jsp.setViewportView(panel);
        jsp.setBounds(40, 0, 1060, 900);
        thirdPanel.add(jsp);
        
        createColumnName(panel);
        
        
        defaultTableModel = new DefaultTableModel(rowData,columnName);
        jt = new JTable(defaultTableModel);
        jt.setRowHeight(28);
        jt.setFont(new Font("姒涙垳缍�",Font.BOLD,14));
        jt.validate();
        jt.updateUI();
        createViewTabel(panel);
        
        
        first_page_button = new JButton("妫ｆ牠銆�");
        pre_page_button = new JButton("閸撳秳绔存い锟�");
        next_page_button = new JButton("娑撳绔存い锟�");
        last_page_button = new JButton("閺堫偊銆�");
        page_des_label = new JLabel();
        

        createFirstPageButton(panel, first_page_button, pre_page_button, next_page_button, last_page_button,
                page_des_label);
        
        createPrePageButton(panel, first_page_button, pre_page_button, next_page_button, last_page_button,
                page_des_label);
        
        createNextPageButton(panel, first_page_button, pre_page_button, next_page_button, last_page_button,
                page_des_label);
        
        
        createLastPageButton(panel, first_page_button, pre_page_button, next_page_button, last_page_button,
                page_des_label);
        
        
        createPageDesLabel(panel, page_des_label);
        
        changeButtonEnabled(first_page_button, pre_page_button, next_page_button, last_page_button);
        
    }

    private void createColumnName(final JPanel panel) {
        //column name
        JTextField id_text_field = new JTextField("鎼村繐褰�");
        id_text_field.setBounds(20, 10, 200, 20);
        id_text_field.setEnabled(false);
        id_text_field.setBorder(null);
        id_text_field.setBackground(Color.GRAY);
        id_text_field.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(id_text_field);
        
        JTextField name_text_field = new JTextField("婵挸鎮�");
        name_text_field.setBounds(220, 10, 200, 20);
        name_text_field.setEnabled(false);
        name_text_field.setBorder(null);
        name_text_field.setBackground(Color.GRAY);
        name_text_field.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(name_text_field);
        
        JTextField message_text_field = new JTextField("閻ｆ瑨鈻�");
        message_text_field.setBounds(420, 10, 200, 20);
        message_text_field.setEnabled(false);
        message_text_field.setBorder(null);
        message_text_field.setBackground(Color.GRAY);
        message_text_field.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(message_text_field);
        
        JTextField call_text_field = new JTextField("閸ョ偛顦查悾娆掆枅");
        call_text_field.setBounds(620, 10, 200, 20);
        call_text_field.setEnabled(false);
        call_text_field.setBorder(null);
        call_text_field.setBackground(Color.GRAY);
        call_text_field.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(call_text_field);
        
        JTextField time_text_field = new JTextField("閺冨爼妫�");
        time_text_field.setBounds(820, 10, 200, 20);
        time_text_field.setEnabled(false);
        time_text_field.setBorder(null);
        time_text_field.setBackground(Color.GRAY);
        time_text_field.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(time_text_field);
    }

    private void createViewTabel(final JPanel panel) {
        jt.setBounds(20, 30, 1000, 600);
        jt.setFont(new Font("鐎瑰缍�",Font.PLAIN,12));
        panel.add(jt);
        
        jt.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}
            
            @Override
            public void mousePressed(MouseEvent e) {}
            
            @Override
            public void mouseExited(MouseEvent e) {}
            
            @Override
            public void mouseEntered(MouseEvent e) {}
            
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = jt.getSelectedRow();
                int[] selectedColumnIndexs = {0,1,2,3,4};
                List<String> rows = new ArrayList<String>();
                for (int columnIndex : selectedColumnIndexs) {
                    rows.add(jt.getValueAt(rowIndex == -1?0:rowIndex, columnIndex == -1?0:columnIndex).toString());
                }
                
//                javax.swing.JOptionPane.showMessageDialog(ManagerFrame.this, rows.toString());
                
                //瀵懓鍤紓鏍帆缁愭褰�
                createEditFrame(rows);
            }
        });
    }

    private void createPageDesLabel(final JPanel panel, JLabel page_des_label) {
        page_des_label.setBounds(800, 625, 200, 30);
        page_des_label.setFont(new Font("姒涙垳缍�",Font.BOLD,12));
        String desc =  DESC_LABEL_TEXT.replaceAll("currentPage", Integer.toString(currentPage+1));// beging from 0
        desc = desc.replaceAll("pageCount", Integer.toString(pageCount));
        page_des_label.setText(desc);
        panel.add(page_des_label);
        
    }

    private void createLastPageButton(final JPanel panel, JButton first_page_button, JButton pre_page_button,
            JButton next_page_button, JButton last_page_button, JLabel page_des_label) {
        last_page_button.setBounds(570, 640, 80, 30);
        last_page_button.setFont(new Font("姒涙垳缍�",Font.BOLD,12));
        panel.add(last_page_button);
        last_page_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentPage <0 || currentPage > pageCount-1){
                    javax.swing.JOptionPane.showMessageDialog(ManagerFrame.this, "last page error "+currentPage);
                    return;
                }
                currentPage = pageCount-1;
                rowData.removeAllElements();
                //query first page data from server
                // TODO
                try {
                    queryDataFromServer(rowData,columnName,currentPage);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                /*
                 * 閺嬪嫰锟界姵鏆熼幑锟� test                
                for(int i=0;i<30;i++){
                    Vector rows = new Vector();
                    for (int j = 0; j < 5; j++) {
                        rows.add((currentPage+1)+" page data "+i+" "+j);
                    }
                    rowData.add(rows);
                }*/
                //refresh data to JTable
                jt.validate();
                jt.updateUI();
                
                String desc =  DESC_LABEL_TEXT.replaceAll("currentPage", Integer.toString(currentPage+1));// beging from 0
                desc = desc.replaceAll("pageCount", Integer.toString(pageCount));
                page_des_label.setText(desc);
                
                changeButtonEnabled(first_page_button, pre_page_button, next_page_button, last_page_button);
            }
        });
    }

    private void createNextPageButton(final JPanel panel, JButton first_page_button, JButton pre_page_button,
            JButton next_page_button, JButton last_page_button, JLabel page_des_label) {
        next_page_button.setBounds(480, 640, 80, 30);
        next_page_button.setFont(new Font("姒涙垳缍�",Font.BOLD,12));
        panel.add(next_page_button);
        next_page_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentPage<0 || currentPage >= pageCount-1){
                    javax.swing.JOptionPane.showMessageDialog(ManagerFrame.this, "next page error "+currentPage);
                    return;
                }
                currentPage++;
                rowData.removeAllElements();
                //query first page data from server
                // TODO
                try {
                    queryDataFromServer(rowData,columnName,currentPage);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                /*
                 * 閺嬪嫰锟界姵鏆熼幑锟� test
                for(int i=0;i<30;i++){
                    Vector rows = new Vector();
                    for (int j = 0; j < 5; j++) {
                        rows.add((currentPage+1)+" page data "+i+" "+j);
                    }
                    rowData.add(rows);
                }*/
                //refresh data to JTable
                jt.validate();
                jt.updateUI();
                
                String desc  =  DESC_LABEL_TEXT.replaceAll("currentPage", Integer.toString(currentPage+1));// beging from 0
                desc = desc.replaceAll("pageCount", Integer.toString(pageCount));
                page_des_label.setText(desc);
                
                changeButtonEnabled(first_page_button, pre_page_button, next_page_button, last_page_button);
            }
        });
    }

    private void createPrePageButton(final JPanel panel, JButton first_page_button, JButton pre_page_button,
            JButton next_page_button, JButton last_page_button, JLabel page_des_label) {
        pre_page_button.setBounds(390, 640, 80, 30);
        pre_page_button.setFont(new Font("姒涙垳缍�",Font.BOLD,12));
        panel.add(pre_page_button);
        pre_page_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentPage <= 0){
                    return;
                }
                
                if(currentPage>0){
                    currentPage--;
                }
                rowData.removeAllElements();
                //query first page data from server
                // TODO
                try {
                    queryDataFromServer(rowData,columnName,currentPage);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                /*
                 * 閺嬪嫰锟界姵鏆熼幑锟� test
                for(int i=0;i<30;i++){
                    Vector rows = new Vector();
                    for (int j = 0; j < 5; j++) {
                        rows.add((currentPage+1)+" page data "+i+" "+j);
                    }
                    rowData.add(rows);
                }*/
                //refresh data to JTable
                jt.validate();
                jt.updateUI();
                
                String desc   =  DESC_LABEL_TEXT.replaceAll("currentPage", Integer.toString(currentPage+1));// beging from 0
                desc = desc.replaceAll("pageCount", Integer.toString(pageCount));
                page_des_label.setText(desc);
                
                changeButtonEnabled(first_page_button, pre_page_button, next_page_button, last_page_button);
            }
        });
    }

    private void createFirstPageButton(final JPanel panel, JButton first_page_button, JButton pre_page_button,
            JButton next_page_button, JButton last_page_button, JLabel page_des_label) {
        first_page_button.setBounds(300, 640, 80, 30);
        first_page_button.setFont(new Font("姒涙垳缍�",Font.BOLD,12));
        panel.add(first_page_button);
        first_page_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPage = 0;
                rowData.removeAllElements();
                //query first page data from server
                // TODO
                try {
                    queryDataFromServer(rowData,columnName,currentPage);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                /*閺嬪嫰锟界姵鏆熼幑锟� test
                 * for(int i=0;i<30;i++){
                    Vector rows = new Vector();
                    for (int j = 0; j < 5; j++) {
                        rows.add((currentPage+1)+" page data "+i+" "+j);
                    }
                    rowData.add(rows);
                }*/
                //refresh data to JTable
                jt.validate();
                jt.updateUI();
                
                String desc   =  DESC_LABEL_TEXT.replaceAll("currentPage", Integer.toString(currentPage+1));// beging from 0
                desc = desc.replaceAll("pageCount", Integer.toString(pageCount));
                page_des_label.setText(desc);
                
                changeButtonEnabled(first_page_button, pre_page_button, next_page_button, last_page_button);
            }
        });
    }

    private void changeButtonEnabled(JButton first_page_button, JButton pre_page_button,
            JButton next_page_button, JButton last_page_button) {
        if(currentPage == 0) {//閸掓繂顫愭い鐢告桨 娑撹櫣顑囨稉锟芥い锟� 閺冨墎顩﹂悽锟� 妫ｆ牠銆夐幐澶愭尦 娑撳簼绗傛稉锟芥い鍨瘻闁斤拷
            if(!last_page_button.isEnabled()) {//瑜版挸澧犳い鍨Ц閺堫偊銆夐敍宀�鍋ｉ崙濠氼浕妞ゅ灚瀵滈柦锟�
                last_page_button.setEnabled(true);
            }
            if(!next_page_button.isEnabled()){
                next_page_button.setEnabled(true);
            }
            first_page_button.setEnabled(false);
            pre_page_button.setEnabled(false);
        }
        if(currentPage > 0) {// 婵″倹鐏夐崷銊よ厬闂傛挳銆� 閸掞拷 妫ｆ牠銆夋稉搴濈瑐娑擄拷妞ゅ灚瀵滈柦顔煎讲閻拷
            first_page_button.setEnabled(true);
            pre_page_button.setEnabled(true);
        }
        if(currentPage == pageCount-1) {//婵″倹鐏夎ぐ鎾冲妞ゅ吀璐熼張锟介崥搴濈妞わ拷  閸掓瑧顩﹂悽锟� 閺堫偊銆� 娑撳簼绗呮稉锟芥い鍨瘻闁斤拷
            last_page_button.setEnabled(false);
            next_page_button.setEnabled(false);
        }
        if(currentPage <  pageCount-1 && !last_page_button.isEnabled()) {
            last_page_button.setEnabled(true);
            next_page_button.setEnabled(true);
        }
    }

    protected void createEditFrame(List<String> rows) {
        message_frame_title = "缂傛牞绶悾娆掆枅";
        createFrame();
        id_text_field.setText(rows.get(0));
        name_field.setText(rows.get(1));
        message_area.setText(rows.get(2));
        call_area.setText(rows.get(3));
    }

    private void queryDataFromServerByName(Vector rowData, Vector columnName,int currentPage,String name) throws IOException {
        messageDos.writeUTF(Request.PAGE_QUERY_MESSAGE);
        String response = messageDis.readUTF();
        if(Request.PAGE_QUERY_MESSAGE.equals(response)){
            Message message = new Message();
            MessageVO messageVO = new MessageVO();
            messageVO.setName(name);
            message.setMessageVO(messageVO);
            message.setCurrentPage(currentPage);
            String messStr = JSON.toJSONString(message);
            messageDos.writeUTF(messStr);
            String mess = messageDis.readUTF();
            JSONObject jsonObj = (JSONObject) JSON.parse(mess);
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
    }
    
    private void queryDataFromServer(Vector rowData, Vector columnName,int currentPage) throws IOException {
        messageDos.writeUTF(Request.PAGE_QUERY_MESSAGE);
        String response = messageDis.readUTF();
        if(Request.PAGE_QUERY_MESSAGE.equals(response)){
            Message message = new Message();
            message.setCurrentPage(currentPage);
            String messStr = JSON.toJSONString(message);
            messageDos.writeUTF(messStr);
            String mess = messageDis.readUTF();
            JSONObject jsonObj = (JSONObject) JSON.parse(mess);
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
    }


    private void createMenuBar(JPanel leftPanel,JPanel rightPanel) {
        JLabel manager_label = new JLabel("> 閻ｆ瑨鈻�");
        manager_label.setFont(new Font("鐎瑰缍�",Font.PLAIN,12));
        manager_label.setBounds(10, 20, 180, 30);
        leftPanel.add(manager_label);
        
        JButton manage_leaving_button = new JButton("閻ｆ瑨鈻堢粻锛勬倞");
        manage_leaving_button.setFont(new Font("鐎瑰缍�",Font.PLAIN,12));
        manage_leaving_button.setForeground(Color.WHITE);
        manage_leaving_button.setBounds(10, 50, 180, 30);
        manage_leaving_button.setBorder(null);
        manage_leaving_button.setBackground(Color.GRAY);
        leftPanel.add(manage_leaving_button);
        manage_leaving_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }
}

