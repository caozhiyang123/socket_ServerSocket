package com.baifenjy.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baifenjy.io.Request;
import com.baifenjy.io.Response;
import com.baifenjy.utils.DatePickUtils;
import com.baifenjy.utils.ThreadLocalSimple;
import com.baifenjy.vo.MessageVO;
import com.eltima.components.ui.DatePicker;
import com.mysql.jdbc.StringUtils;

public class ManagerFrame extends JFrame{
    public static void main(String[] args) {
        new ManagerFrame();
  }

  private  ManagerFrame() {
      this.setTitle("管理系统_v2.0_上海力霆教育科技有限公司_[www.baifenjy.com]");
      this.setFont(new Font("宋体",Font.PLAIN,12));
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
      //设置水平滚动条不显示
      jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      //设置垂直滚动条一直显示
      jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      jScrollPane.getVerticalScrollBar().setUnitIncrement(20);
      //定义一个JPanel面板 以整合其他面板
      final JPanel panel = new JPanel();
      panel.setPreferredSize(new Dimension(1200,1000)); 
      panel.setLayout(null);
      jScrollPane.setViewportView(panel);
      this.add(jScrollPane);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      final JPanel rightPanel = new JPanel();
      panel.add(rightPanel);
      createRightPanel(rightPanel);

      final JPanel leftPanel = new JPanel();
      panel.add(leftPanel);
      createLeftPanel(leftPanel,rightPanel);
      
      connect();
      
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

    private void createLeftPanel(JPanel leftPanel,JPanel rightPanel) {
        leftPanel.setLayout(null);
        leftPanel.setBorder(new TitledBorder(null, "网站管理", TitledBorder.DEFAULT_JUSTIFICATION,
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
        
        JButton addNewMessageButton = new JButton("添加新留言");
        firstPanel.add(addNewMessageButton);
        createAddNewMessageButton(addNewMessageButton);
        
        JButton deleteMessageButton = new JButton("删除留言");
        firstPanel.add(deleteMessageButton);
        createDeleteMessageButton(deleteMessageButton);
    }
    

    private void createDeleteMessageButton(JButton deleteMessageButton) {
        deleteMessageButton.setFont(new Font("宋体",Font.PLAIN,12));
        deleteMessageButton.setBounds(140, 5, 100, 20);
        deleteMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 选择要删除的留言
                
            }
        });
    }

    private void createAddNewMessageButton(JButton addNewMessageButton) {
        addNewMessageButton.setFont(new Font("宋体",Font.PLAIN,12));
        addNewMessageButton.setBounds(10, 5, 100, 20);
        addNewMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSaveFrame();
            }

            
        });
    }
    
    private void createSaveFrame() {
        createFrame();
    }

    private DatePicker datePicker;
    private JTextField name_field;
    private JTextField id_text_field;
    private JTextArea message_area;
    private JTextArea call_area;
    
    private void createFrame() {
        //创建一个JFrame窗口
        JFrame addNewMessageFrame = new JFrame("添加新留言");
        addNewMessageFrame.setFont(new Font("宋体",Font.PLAIN,12));
        addNewMessageFrame.setResizable(false);
        addNewMessageFrame.setBounds(400,50,650,700);
        addNewMessageFrame.setVisible(true);
        addNewMessageFrame.setAlwaysOnTop(true);
        addNewMessageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //dispose();
        JScrollPane newMessageScrollPanel = new JScrollPane();
        //设置水平滚动条不显示
        newMessageScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //设置垂直滚动条一直显示
        newMessageScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        newMessageScrollPanel.getVerticalScrollBar().setUnitIncrement(20);
        addNewMessageFrame.add(newMessageScrollPanel);
        
        JPanel newMessagePanel = new JPanel();
        newMessagePanel.setPreferredSize(new Dimension(1200,800)); 
        newMessagePanel.setLayout(null);
        newMessageScrollPanel.setViewportView(newMessagePanel);
        
        JLabel time_label = new JLabel("留言时间:");
        time_label.setFont(new Font("宋体",Font.PLAIN,12));
        time_label.setBounds(10,10,70,35);
        newMessagePanel.add(time_label);
        
        datePicker = DatePickUtils.getDatePicker();
        datePicker.setBounds(80, 10, 200, 35);
        newMessagePanel.add(datePicker);
        
        JLabel name_label = new JLabel("姓名:",SwingConstants.CENTER);
        name_label.setFont(new Font("宋体",Font.PLAIN,12));
        name_label.setBounds(10, 50, 70, 35);
        newMessagePanel.add(name_label);
        
        name_field = new JTextField();
        name_field.setBounds(80, 50, 200, 35);
        newMessagePanel.add(name_field);
        
        id_text_field = new JTextField();
        id_text_field.setBounds(285, 50, 200, 35);
        id_text_field.setEnabled(false);
        id_text_field.setVisible(false);
        newMessagePanel.add(id_text_field);
        
        JButton message_button = new JButton(" 留言内容:");
        message_button.setFont(new Font("宋体",Font.PLAIN,12));
        message_button.setHorizontalAlignment(SwingConstants.LEFT);
        message_button.setBounds(10, 95, 600, 20);
        message_button.setBorder(null);
        message_button.setBackground(Color.lightGray);
        message_button.setEnabled(false);
        newMessagePanel.add(message_button);
        
        message_area = new JTextArea();
        message_area.setBounds(10, 115, 600, 280);
        newMessagePanel.add(message_area);
        
        JLabel call_label = new JLabel("回复内容:",SwingConstants.LEFT);
        call_label.setFont(new Font("宋体",Font.PLAIN,12));
        call_label.setBounds(10, 405, 600, 20);
        newMessagePanel.add(call_label);
        
        call_area = new JTextArea();
        call_area.setBounds(10, 425, 600, 280);
        newMessagePanel.add(call_area);
        
        JButton save_button = new JButton("保存");
        save_button.setFont(new Font("宋体",Font.PLAIN,12));
        save_button.setBounds(250, 715, 80, 30);
        newMessagePanel.add(save_button);
        save_button.addActionListener(new ActionListener() {
            Date date = (Date) datePicker.getValue();
            String time = ThreadLocalSimple.df.get().format(date);
            String name = name_field.getText();
            String id  = id_text_field.getText();
            String message = message_area.getText();
            String callMessage = call_area.getText();
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = false;
                if(StringUtils.isNullOrEmpty(time.trim())){
                    javax.swing.JOptionPane.showMessageDialog(ManagerFrame.this, "日期必须填写");
                    flag = true;
                    return;
                }
                if(flag == false && StringUtils.isNullOrEmpty(name.trim())){
                    javax.swing.JOptionPane.showMessageDialog(ManagerFrame.this, "姓名必须填写");
                    flag = true;
                    return;
                }
                if(flag == false && StringUtils.isNullOrEmpty(message.trim())){
                    javax.swing.JOptionPane.showMessageDialog(ManagerFrame.this, "留言必须填写");
                    flag = true;
                    return;
                }
                
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
                                    //更新数据成功重写查询所有数据
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
                                //保存成功重写查询所有数据
                            }
                        }
                        
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    };
                }
            }
        });
        
        JButton cancel_button = new JButton("取消");
        cancel_button.setFont(new Font("宋体",Font.PLAIN,12));
        cancel_button.setBounds(340, 715, 80, 30);
        newMessagePanel.add(cancel_button);
        cancel_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //关闭当前窗口
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
        
        JLabel name_label = new JLabel("姓名:");
        name_label.setBounds(10, 5, 50, 20);
        name_label.setFont(new Font("宋体",Font.PLAIN,12));
        secondPanel.add(name_label);
        
        JTextField name_text = new JTextField();
        name_text.setBounds(50, 5, 80, 20);
        name_text.setFont(new Font("宋体",Font.PLAIN,12));
        secondPanel.add(name_text);
        
        JLabel start_label = new JLabel("时间:");
        start_label.setBounds(150, 5, 50, 20);
        start_label.setFont(new Font("宋体",Font.PLAIN,12));
        secondPanel.add(start_label);
        
        DatePicker start_datePicker = DatePickUtils.getDatePicker();
        start_datePicker.setBounds(190, 5, 200, 20);
        secondPanel.add(start_datePicker);
        
        
        JLabel end_label = new JLabel("—");
        end_label.setBounds(410, 5, 50, 20);
        secondPanel.add(end_label);
        
        DatePicker end_datePicker = DatePickUtils.getDatePicker();
        end_datePicker.setBounds(440, 5, 200, 20);
        secondPanel.add(end_datePicker);
        
        JButton query_button = new JButton("查询");
        query_button.setBounds(660, 5, 100, 20);
        secondPanel.add(query_button);
        query_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //分页查询
                
            }
        });
        
        
    }
    
    private JTable jt = null;
    private JScrollPane jsp = null;
    
    private void createThirdPanel(JPanel thirdPanel) {
        thirdPanel.setLayout(null);
        thirdPanel.setBorder(new TitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        thirdPanel.setBounds(0, 60, 1100, 900);
        thirdPanel.setVisible(true);
        
        //query rowData and columnName from server
        Vector rowData = new Vector();
        Vector columnName = new Vector();
        for(int i=0;i<5;i++){
            Vector rows = new Vector();
            for (int j = 0; j < 5; j++) {
                rows.add("row value "+i+" "+j);
            }
            rowData.add(rows);
        }
        for (int i = 0; i < 5; i++) {
            columnName.add("column name "+i);
        }
        /*try {
            queryDataFromServer(rowData, columnName);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        
        jsp = new JScrollPane();
      //设置水平滚动条不显示
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //设置垂直滚动条一直显示
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.getVerticalScrollBar().setUnitIncrement(20);
        //定义一个JPanel面板 以整合其他面板
        final JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1200,1000)); 
        panel.setLayout(null);
        jsp.setViewportView(panel);
        jsp.setBounds(40, 0, 1060, 900);
        thirdPanel.add(jsp);
        
        //column name
        JTextField id_text_field = new JTextField("序号");
        id_text_field.setBounds(20, 10, 200, 20);
        id_text_field.setEnabled(false);
        id_text_field.setBorder(null);
        id_text_field.setBackground(Color.GRAY);
        id_text_field.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(id_text_field);
        
        JTextField name_text_field = new JTextField("姓名");
        name_text_field.setBounds(220, 10, 200, 20);
        name_text_field.setEnabled(false);
        name_text_field.setBorder(null);
        name_text_field.setBackground(Color.GRAY);
        name_text_field.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(name_text_field);
        
        JTextField message_text_field = new JTextField("留言");
        message_text_field.setBounds(420, 10, 200, 20);
        message_text_field.setEnabled(false);
        message_text_field.setBorder(null);
        message_text_field.setBackground(Color.GRAY);
        message_text_field.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(message_text_field);
        
        JTextField call_text_field = new JTextField("回复留言");
        call_text_field.setBounds(620, 10, 200, 20);
        call_text_field.setEnabled(false);
        call_text_field.setBorder(null);
        call_text_field.setBackground(Color.GRAY);
        call_text_field.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(call_text_field);
        
        JTextField time_text_field = new JTextField("时间");
        time_text_field.setBounds(820, 10, 200, 20);
        time_text_field.setEnabled(false);
        time_text_field.setBorder(null);
        time_text_field.setBackground(Color.GRAY);
        time_text_field.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(time_text_field);
        
        jt = new JTable(rowData,columnName);
        jt.setBounds(20, 30, 1000, 900);
        panel.add(jt);
        
        
        jt.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = jt.getSelectedRow();
                int[] selectedColumnIndexs = {0,1,2,3,4};
                List<String> rows = new ArrayList<String>();
                for (int columnIndex : selectedColumnIndexs) {
                    rows.add(jt.getValueAt(rowIndex == -1?0:rowIndex, columnIndex == -1?0:columnIndex).toString());
                }
                
//                javax.swing.JOptionPane.showMessageDialog(ManagerFrame.this, rows.toString());
                
                //弹出编辑窗口
                createEditFrame(rows);
            }
        });
        
    }

    protected void createEditFrame(List<String> rows) {
        createFrame();
        id_text_field.setText(rows.get(0));
        name_field.setText(rows.get(1));
        message_area.setText(rows.get(2));
        call_area.setText(rows.get(3));
    }

    private void queryDataFromServer(Vector rowData, Vector columnName) throws IOException {
        messageDos.writeUTF(Request.PAGE_QUERY_MESSAGE);
        String response = messageDis.readUTF();
        if(Request.PAGE_QUERY_MESSAGE.equals(response)){
            String mess = messageDis.readUTF();
            JSONObject jsonObj = (JSONObject) JSON.parse(mess);
            JSONArray rowDatas = (JSONArray) jsonObj.get("rowData");
            for (Object obj : rowDatas) {
                Vector rowLineData = new Vector();
                JSONArray rowDataArr = (JSONArray)obj;
                for (Object objLine : rowDataArr) {
                    JSONObject jsonLine = (JSONObject)objLine;
                    rowLineData.add(jsonLine.get("time").toString());
                    rowLineData.add(jsonLine.get("name").toString());
                    rowLineData.add(jsonLine.get("message").toString());
                    rowLineData.add(jsonLine.get("callMessage").toString());
                }
                rowData.add(rowLineData);
            }
            JSONArray columnNameArr = (JSONArray) jsonObj.get("columnName");
            for (Object obj : columnNameArr) {
                columnName.add(obj.toString());
            }
            
        }
    }


    private void createMenuBar(JPanel leftPanel,JPanel rightPanel) {
        JLabel manager_label = new JLabel("> 留言");
        manager_label.setFont(new Font("宋体",Font.PLAIN,12));
        manager_label.setBounds(10, 20, 180, 30);
        leftPanel.add(manager_label);
        
        JButton manage_leaving_button = new JButton("留言管理");
        manage_leaving_button.setFont(new Font("宋体",Font.PLAIN,12));
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

