package com.baifenjy.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.baifenjy.io.Request;
import com.baifenjy.io.Response;
import com.baifenjy.utils.Center;

public class OrderFrame extends JFrame {

    Socket s;
    DataOutputStream dos;
    DataInputStream dis;
    
    private static String res;
    
    public static void main(String args[]) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    OrderFrame dialog = new OrderFrame();
                    dialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
    public OrderFrame(){
        setTitle("管理系统_v1.0_上海力霆教育科技有限公司_[www.baifenjy.com]");
        getContentPane().setLayout(null);
        setBounds(100, 100, 1350, 750);
        
     // 设置窗口居中
        this.setLocation(Center.getPoint(this.getSize()));
        final JPanel commit_panel = new JPanel();
        commit_panel.setLayout(null);
        commit_panel.setBorder(new TitledBorder(null, "新增订单", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        commit_panel.setBounds(0, 0, 700, 500);
        commit_panel.setVisible(true);
        getContentPane().add(commit_panel);

      // --left up
        final JLabel label_create = new JLabel();
        label_create.setText("创建时间");
        label_create.setBounds(76, 50, 66, 18);
        commit_panel.add(label_create);
        
        final JTextField create_Field = new JTextField();
        create_Field.setBounds(148, 40, 192, 42);
        create_Field.setEnabled(false);
        commit_panel.add(create_Field);
        
        final JLabel label_order = new JLabel();
        label_order.setText("订单号");
        label_order.setBounds(76, 100, 66, 18);
        commit_panel.add(label_order);
        
        final JTextField order_Field = new JTextField();
        order_Field.setBounds(148, 90, 192, 42);
        commit_panel.add(order_Field);
        
        final JLabel label_name = new JLabel();
        label_name.setText("学生姓名");
        label_name.setBounds(76, 150, 66, 18);
        commit_panel.add(label_name);
        
        final JTextField name_Field = new JTextField();
        name_Field.setBounds(148, 140, 192, 42);
        commit_panel.add(name_Field);
        
        final JLabel label_age = new JLabel();
        label_age.setText("学生年龄");
        label_age.setBounds(76, 200, 66, 18);
        commit_panel.add(label_age);
        
        final JTextField ageField = new JTextField();
        ageField.setBounds(148, 190, 192, 42);
        commit_panel.add(ageField);
        
        final JLabel label_sex = new JLabel();
        label_sex.setText("学生性别");
        label_sex.setBounds(76, 250, 66, 18);
        commit_panel.add(label_sex);
        
        final JTextField sexField = new JTextField();
        sexField.setBounds(148, 240, 192, 42);
        commit_panel.add(sexField);
        
        final JLabel label_grade = new JLabel();
        label_grade.setText("学生年级");
        label_grade.setBounds(76, 300, 66, 18);
        commit_panel.add(label_grade);
        
        final JTextField gradeField = new JTextField();
        gradeField.setBounds(148, 290, 192, 42);
        commit_panel.add(gradeField);
        
        final JLabel phoneNum_label = new JLabel();
        phoneNum_label.setText("电话");
        phoneNum_label.setBounds(76, 350, 66, 18);
        commit_panel.add(phoneNum_label);
        
        final JTextField phone_field = new JTextField();
        phone_field.setBounds(148, 340, 192, 42);
        commit_panel.add(phone_field);
        
        final JLabel label_resource = new JLabel();
        label_resource.setText("渠道");
        label_resource.setBounds(76, 400, 66, 18);
        commit_panel.add(label_resource);
        
        final JTextField resource_Field = new JTextField();
        resource_Field.setBounds(148, 390, 192, 42);
        commit_panel.add(resource_Field);
        
        // -- right up  ~ 72
        final JLabel label_update = new JLabel();
        label_update.setText("更新时间");
        label_update.setBounds(352, 50, 66, 18);
        commit_panel.add(label_update);
        
        final JTextField update_Field = new JTextField();
        update_Field.setBounds(432, 40, 192, 42);
        update_Field.setEnabled(false);
        commit_panel.add(update_Field);
        
        final JLabel label_subject = new JLabel();
        label_subject.setText("科目");
        label_subject.setBounds(352, 100, 66, 18);
        commit_panel.add(label_subject);
        
        final JTextField subject_Field = new JTextField();
        subject_Field.setBounds(432, 90, 192, 42);
        commit_panel.add(subject_Field);
        
        final JLabel label_address = new JLabel();
        label_address.setText("家庭地址");
        label_address.setBounds(352, 150, 66, 18);
        commit_panel.add(label_address);
        
        final JTextField address_Field = new JTextField();
        address_Field.setBounds(432, 140, 192, 42);
        commit_panel.add(address_Field);
        
        final JLabel label_time = new JLabel();
        label_time.setText("时间要求");
        label_time.setBounds(352, 200, 66, 18);
        commit_panel.add(label_time);
        
        final JTextField time_Field = new JTextField();
        time_Field.setBounds(432, 190, 192, 42);
        commit_panel.add(time_Field);
        
        final JLabel label_cost = new JLabel();
        label_cost.setText("价格要求");
        label_cost.setBounds(352, 250, 66, 18);
        commit_panel.add(label_cost);
        
        final JTextField costField = new JTextField();
        costField.setBounds(432, 240, 192, 42);
        commit_panel.add(costField);
        
        final JLabel label_parentsName = new JLabel();
        label_parentsName.setText("家长称呼");
        label_parentsName.setBounds(352, 300, 66, 18);
        commit_panel.add(label_parentsName);
        
        final JTextField parentsName_field = new JTextField();
        parentsName_field.setBounds(432, 290, 192, 42);
        commit_panel.add(parentsName_field);
        
        final JLabel label_qqNum = new JLabel();
        label_qqNum.setText("QQ");
        label_qqNum.setBounds(352, 350, 66, 18);
        commit_panel.add(label_qqNum);
        
        final JTextField qq_Field = new JTextField();
        qq_Field.setBounds(432, 340, 192, 42);
        commit_panel.add(qq_Field);
        

        final JLabel label_weChatNum = new JLabel();
        label_weChatNum.setText("微信");
        label_weChatNum.setBounds(352, 400, 66, 18);
        commit_panel.add(label_weChatNum);
        
        final JTextField weChatNum_Field = new JTextField();
        weChatNum_Field.setBounds(432, 390, 192, 42);
        commit_panel.add(weChatNum_Field);
        
       
        
        addSaveOrUpdateButton(commit_panel, order_Field, name_Field, ageField, sexField, gradeField, phone_field,
                resource_Field, subject_Field, address_Field, time_Field, costField, parentsName_field, qq_Field,
                weChatNum_Field);
        
        addQueryButton(commit_panel, create_Field, order_Field, name_Field, ageField, sexField, gradeField, phone_field,
                resource_Field, update_Field, subject_Field, address_Field, time_Field, costField, parentsName_field,
                qq_Field, weChatNum_Field);
        
        addEmptyButton(commit_panel, create_Field, order_Field, name_Field, ageField, sexField, gradeField, phone_field,
                resource_Field, update_Field, subject_Field, address_Field, time_Field, costField, parentsName_field,
                qq_Field, weChatNum_Field);
        
        
        final JButton teacher_button = new JButton();
        teacher_button.setText("上门老师");
        teacher_button.setBounds(248, 440, 91, 40);
        commit_panel.add(teacher_button);
        
        resetWindoHightEvent(teacher_button);
        
        createTecPanel();
        
        
        connect();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                disconnect();
            }
        });
    }


    private void addEmptyButton(JPanel commit_panel, JTextField create_Field, JTextField order_Field,
            JTextField name_Field, JTextField ageField, JTextField sexField, JTextField gradeField,
            JTextField phone_field, JTextField resource_Field, JTextField update_Field, JTextField subject_Field,
            JTextField address_Field, JTextField time_Field, JTextField costField, JTextField parentsName_field,
            JTextField qq_Field, JTextField weChatNum_Field)
    {
        final JButton empty_button = new JButton();
        empty_button.setText("清空");
        empty_button.setBounds(532, 440, 91, 40);
        commit_panel.add(empty_button);
        
        empty_button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                order_Field.setText("");
                name_Field.setText("");
                ageField.setText("");
                sexField.setText("");
                gradeField.setText("");
                subject_Field.setText("");
                address_Field.setText("");
                time_Field.setText("");
                costField.setText("");
                parentsName_field.setText("");
                qq_Field.setText("");
                weChatNum_Field.setText("");
                phone_field.setText("");
                resource_Field.setText("");
            }
        });
    }


    public void addQueryButton(final JPanel commit_panel, final JTextField create_Field, final JTextField order_Field,
            final JTextField name_Field, final JTextField ageField, final JTextField sexField,
            final JTextField gradeField, final JTextField phone_field, final JTextField resource_Field,
            final JTextField update_Field, final JTextField subject_Field, final JTextField address_Field,
            final JTextField time_Field, final JTextField costField, final JTextField parentsName_field,
            final JTextField qq_Field, final JTextField weChatNum_Field)
    {
        final JButton query_button = new JButton();
        query_button.setText("订单查询");
        query_button.setBounds(432, 440, 91, 40);
        commit_panel.add(query_button);
        
        query_button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    dos.writeUTF(Request.QUERY_ORDER);
                    String response = dis.readUTF();
                    if (response.equals(Request.QUERY_ORDER)) {
                        String orderId = order_Field.getText();
                        dos.writeUTF(orderId);
                        orderId = dis.readUTF();
                        String studentName = dis.readUTF();
                        String studentAge = dis.readUTF();
                        String studentSex = dis.readUTF();
                        String studentGrade = dis.readUTF();
                        String studentSubject = dis.readUTF();
                        String address = dis.readUTF();
                        String time = dis.readUTF();
                        String cost = dis.readUTF();
                        String parentsName = dis.readUTF();
                        String qqNum = dis.readUTF();
                        String weChatNum = dis.readUTF();
                        String phoneNum = dis.readUTF();
                        String messageResource = dis.readUTF();
                        String createAt = dis.readUTF();
                        String updateAt = dis.readUTF();
                        
                        order_Field.setText(orderId);
                        name_Field.setText(studentName);
                        ageField.setText(studentAge);
                        sexField.setText(studentSex);
                        gradeField.setText(studentGrade);
                        subject_Field.setText(studentSubject);
                        address_Field.setText(address);
                        time_Field.setText(time);
                        costField.setText(cost);
                        parentsName_field.setText(parentsName);
                        qq_Field.setText(qqNum);
                        weChatNum_Field.setText(weChatNum);
                        phone_field.setText(phoneNum);
                        resource_Field.setText(messageResource);
                        create_Field.setText(createAt);
                        update_Field.setText(updateAt);
                        
                    }
                } catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        });
    }


    public void addSaveOrUpdateButton(final JPanel commit_panel, final JTextField order_Field,
            final JTextField name_Field, final JTextField ageField, final JTextField sexField,
            final JTextField gradeField, final JTextField phone_field, final JTextField resource_Field,
            final JTextField subject_Field, final JTextField address_Field, final JTextField time_Field,
            final JTextField costField, final JTextField parentsName_field, final JTextField qq_Field,
            final JTextField weChatNum_Field)
    {
        // --
        final JButton commit_button = new JButton();
        commit_button.setText("保存/修改");
        commit_button.setBounds(148, 440, 91, 40);
        commit_panel.add(commit_button);
        
        commit_button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String orderId = new String(order_Field.getText()).trim();
                String studentName = new String(name_Field.getText()).trim();
                String studentAge = new String(ageField.getText()).trim();
                String studentSex = new String(sexField.getText()).trim();
                String studentGrade = new String(gradeField.getText()).trim();
                String studentSubject = new String(subject_Field.getText()).trim();
                String address = new String(address_Field.getText()).trim();
                String time = new String(time_Field.getText()).trim();
                String cost = new String(costField.getText()).trim();
                String parentsName = new String(parentsName_field.getText()).trim();
                String qqNum = new String(qq_Field.getText()).trim();
                String weChatNum = new String(weChatNum_Field.getText()).trim();
                String phoneNum = new String(phone_field.getText()).trim();
                String messageResource = new String(resource_Field.getText()).trim();
                
                boolean flag = false;
                if(orderId.trim().equals("")){
                    javax.swing.JOptionPane.showMessageDialog(OrderFrame.this, "订单号必须填写...");
                    flag = true;
                    return;
                }

                if(flag == false && phoneNum.trim().equals("")){
                    javax.swing.JOptionPane.showMessageDialog(OrderFrame.this, "手机号必须填写...");
                    flag = true;
                    return;
                }

                if( flag == false && studentGrade.trim().equals("")){
                    javax.swing.JOptionPane.showMessageDialog(OrderFrame.this, "学生年级必须填写...");
                    flag = true;
                    return;
                }

                if(flag == false && studentSubject.trim().equals("")){
                    javax.swing.JOptionPane.showMessageDialog(OrderFrame.this, "学生辅导科目必须填写...");
                    flag = true;
                    return;
                }
                

                if(flag == false && !time.trim().equals("")){
                    try
                    {
                        dos.writeUTF(Request.COMMIT_ORDER);
                        res = dis.readUTF();
                        if (Request.COMMIT_ORDER.equals(res)) {
                            dos.writeUTF(orderId);
                            dos.writeUTF(studentName);
                            dos.writeUTF(studentAge);
                            dos.writeUTF(studentSex);
                            dos.writeUTF(studentGrade);
                            dos.writeUTF(studentSubject);
                            dos.writeUTF(address);
                            dos.writeUTF(time);
                            dos.writeUTF(cost);
                            dos.writeUTF(parentsName);
                            dos.writeUTF(qqNum);
                            dos.writeUTF(weChatNum);
                            dos.writeUTF(phoneNum);
                            dos.writeUTF(messageResource);
                            res = dis.readUTF();
                            if(Response.SUCCESS.equals(res)){
                                javax.swing.JOptionPane.showMessageDialog(OrderFrame.this, "提交成功");
                                
                                order_Field.setText("");
                                name_Field.setText("");
                                ageField.setText("");
                                sexField.setText("");
                                gradeField.setText("");
                                subject_Field.setText("");
                                address_Field.setText("");
                                time_Field.setText("");
                                costField.setText("");
                                parentsName_field.setText("");
                                qq_Field.setText("");
                                weChatNum_Field.setText("");
                                phone_field.setText("");
                                resource_Field.setText("");
                            }else{
                                javax.swing.JOptionPane.showMessageDialog(OrderFrame.this, "提交失败");
                            }
                        }
                    } catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }
                    
                }else if(flag == false && time.trim().equals("")){
                    javax.swing.JOptionPane.showMessageDialog(OrderFrame.this, "时间要求必须填写...");
                    return;
                }
            
            }
        });
    }

    private static final String IP = "127.0.0.1";
    private static final int PORT = 8888;
    public void connect() {
        try {
            s = new Socket(IP, PORT);
            System.out.println("一个客户端登陆中....!");
            dos = new DataOutputStream(s.getOutputStream());
            dis = new DataInputStream(s.getInputStream());

        } catch (ConnectException e) {
            System.out.println("服务端异常.........");
            System.out.println("请确认服务端是否开启.........");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (dos != null)
                dos.close();
            if (s != null)
                s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetWindoHightEvent(final JButton query_button)
    {
        query_button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (OrderFrame.this.getHeight() == 515) {
                    OrderFrame.this.setSize(1300, 1000);
                }else{
                    OrderFrame.this.setSize(800, 515);
                }
                // 设置窗口不断居中
                OrderFrame.this.setLocation(Center.getPoint(OrderFrame.this.getSize()));
            }
        });
    }


    public void createTecPanel()
    {
        final JPanel query_panel = new JPanel();
        query_panel.setLayout(null);
        query_panel.setBorder(new TitledBorder(null, "上门老师", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        query_panel.setBounds(710, 0, 700, 500);
        getContentPane().add(query_panel);
        
        // -- left
        final JLabel label_orderId = new JLabel();
        label_orderId.setText("订单号：");
        label_orderId.setBounds(10, 40, 66, 18);
        query_panel.add(label_orderId);
        
        final JTextArea field_orderId = new JTextArea();
        field_orderId.setBounds(66, 30, 150, 40);
        query_panel.add(field_orderId);
        
        final JLabel label_tec_name = new JLabel();
        label_tec_name.setText("老师姓名：");
        label_tec_name.setBounds(10, 90, 66, 18);
        query_panel.add(label_tec_name);
        
        final JTextArea field_tec_name = new JTextArea();
        field_tec_name.setBounds(66, 80, 150, 40);
        query_panel.add(field_tec_name);
        
        final JLabel label_tec_phone = new JLabel();
        label_tec_phone.setText("老师电话：");
        label_tec_phone.setBounds(10, 140, 66, 18);
        query_panel.add(label_tec_phone);
        
        final JTextArea field_tec_phone = new JTextArea();
        field_tec_phone.setBounds(66, 130, 150, 40);
        query_panel.add(field_tec_phone);
        
        final JLabel label_tec_qq = new JLabel();
        label_tec_qq.setText("老师 QQ：");
        label_tec_qq.setBounds(10, 190, 66, 18);
        query_panel.add(label_tec_qq);
        
        final JTextArea field_tec_qq = new JTextArea();
        field_tec_qq.setBounds(66, 180, 150, 40);
        query_panel.add(field_tec_qq);
        
        final JLabel label_tec_weChat = new JLabel();
        label_tec_weChat.setText("老师微信：");
        label_tec_weChat.setBounds(10, 240, 66, 18);
        query_panel.add(label_tec_weChat);
        
        final JTextArea field_tec_weChat = new JTextArea();
        field_tec_weChat.setBounds(66, 230, 150, 40);
        query_panel.add(field_tec_weChat);

        final JLabel label_tec_age = new JLabel();
        label_tec_age.setText("老师年龄：");
        label_tec_age.setBounds(10, 290, 66, 18);
        query_panel.add(label_tec_age);
        
        final JTextArea field_tec_age = new JTextArea();
        field_tec_age.setBounds(66, 280, 150, 40);
        query_panel.add(field_tec_age);
        
        final JLabel label_tec_sex = new JLabel();
        label_tec_sex.setText("老师性别：");
        label_tec_sex.setBounds(10, 340, 66, 18);
        query_panel.add(label_tec_sex);
        
        final JTextArea field_tec_sex = new JTextArea();
        field_tec_sex.setBounds(66, 330, 150, 40);
        query_panel.add(field_tec_sex);

        final JLabel label_tec_email = new JLabel();
        label_tec_email.setText("老师邮箱：");
        label_tec_email.setBounds(10, 390, 66, 18);
        query_panel.add(label_tec_email);
        
        final JTextArea field_tec_email = new JTextArea();
        field_tec_email.setBounds(66, 380, 150, 40);
        query_panel.add(field_tec_email);
        
        //--right

        final JLabel label_tec_address = new JLabel();
        label_tec_address.setText("老师地址：");
        label_tec_address.setBounds(226, 40, 66, 18);
        query_panel.add(label_tec_address);
        
        final JTextArea field_tec_address = new JTextArea();
        field_tec_address.setBounds(292, 30, 150, 40);
        query_panel.add(field_tec_address);

        final JLabel label_tec_idCard = new JLabel();
        label_tec_idCard.setText("身份证：");
        label_tec_idCard.setBounds(226, 90, 66, 18);
        query_panel.add(label_tec_idCard);
        
        final JTextArea field_tec_idCard = new JTextArea();
        field_tec_idCard.setBounds(292, 80, 150, 40);
        query_panel.add(field_tec_idCard);

        final JLabel label_tec_college = new JLabel();
        label_tec_college.setText("在读大学：");
        label_tec_college.setBounds(226, 140, 66, 18);
        query_panel.add(label_tec_college);
        
        final JTextArea field_tec_college = new JTextArea();
        field_tec_college.setBounds(292, 130, 150, 40);
        query_panel.add(field_tec_college);
        
        final JLabel label_tec_profession = new JLabel();
        label_tec_profession.setText("在读专业：");
        label_tec_profession.setBounds(226, 190, 66, 18);
        query_panel.add(label_tec_profession);
        
        final JTextArea field_tec_profession = new JTextArea();
        field_tec_profession.setBounds(292, 180, 150, 40);
        query_panel.add(field_tec_profession);
        
        final JLabel label_tec_otherImports = new JLabel();
        label_tec_otherImports.setText("其他信息：");
        label_tec_otherImports.setBounds(226, 240, 66, 18);
        query_panel.add(label_tec_otherImports);
        
        final JTextArea field_tec_otherImports = new JTextArea();
        field_tec_otherImports.setBounds(292, 230, 150, 90);
        query_panel.add(field_tec_otherImports);
        
        final JLabel label_tec_item = new JLabel();
        label_tec_item.setText("复制详情：");
        label_tec_item.setBounds(226, 330, 66, 18);
        query_panel.add(label_tec_item);
        
        final JTextArea field_tec_item = new JTextArea();
        field_tec_item.setBounds(292, 330, 150, 90);
        query_panel.add(field_tec_item);
        
        //-- right right
        final JLabel label_tec_certification = new JLabel();
        label_tec_certification.setText("获奖证书：");
        label_tec_certification.setBounds(442, 40, 66, 18);
        query_panel.add(label_tec_certification);
        
        final JTextArea field_tec_certification = new JTextArea();
        field_tec_certification.setBounds(508, 30, 150, 40);
        query_panel.add(field_tec_certification);

        final JLabel label_tec_canTeacherGrade = new JLabel();
        label_tec_canTeacherGrade.setText("授课年级：");
        label_tec_canTeacherGrade.setBounds(442, 90, 66, 18);
        query_panel.add(label_tec_canTeacherGrade);
        
        final JTextArea field_tec_canTeacherGrade = new JTextArea();
        field_tec_canTeacherGrade.setBounds(508, 80, 150, 40);
        query_panel.add(field_tec_canTeacherGrade);

        final JLabel label_tec_canTeacherSubject = new JLabel();
        label_tec_canTeacherSubject.setText("教授科目：");
        label_tec_canTeacherSubject.setBounds(442, 140, 66, 18);
        query_panel.add(label_tec_canTeacherSubject);
        
        final JTextArea field_tec_canTeacherSubject = new JTextArea();
        field_tec_canTeacherSubject.setBounds(508, 130, 150, 40);
        query_panel.add(field_tec_canTeacherSubject);
        
        final JLabel label_tec_canTeacherArea = new JLabel();
        label_tec_canTeacherArea.setText("教授区域：");
        label_tec_canTeacherArea.setBounds(442, 190, 66, 18);
        query_panel.add(label_tec_canTeacherArea);
        
        final JTextArea field_tec_canTeacherArea = new JTextArea();
        field_tec_canTeacherArea.setBounds(508, 180, 150, 40);
        query_panel.add(field_tec_canTeacherArea);
        
        final JLabel label_tec_teachExperience = new JLabel();
        label_tec_teachExperience.setText("教学经验：");
        label_tec_teachExperience.setBounds(442, 240, 66, 18);
        query_panel.add(label_tec_teachExperience);
        
        final JTextArea field_tec_teachExperience = new JTextArea();
        field_tec_teachExperience.setBounds(508, 230, 150, 40);
        query_panel.add(field_tec_teachExperience);
        
        final JLabel label_tec_createAt = new JLabel();
        label_tec_createAt.setText("创建时间：");
        label_tec_createAt.setBounds(442, 290, 66, 18);
        query_panel.add(label_tec_createAt);
        
        final JTextArea field_tec_createAt  = new JTextArea();
        field_tec_createAt.setBounds(508, 280, 150, 40);
        field_tec_createAt.setEnabled(false);
        query_panel.add(field_tec_createAt);
        
        
        final JLabel label_tec_updateAt = new JLabel();
        label_tec_updateAt.setText("更新时间：");
        label_tec_updateAt.setBounds(442, 340, 66, 18);
        query_panel.add(label_tec_updateAt);
        
        final JTextArea field_tec_updateAt  = new JTextArea();
        field_tec_updateAt.setBounds(508, 330, 150, 40);
        field_tec_updateAt.setEnabled(false);
        query_panel.add(field_tec_updateAt);
        
        final JLabel label_tec_orderIds = new JLabel();
        label_tec_orderIds.setText("历史订单：");
        label_tec_orderIds.setBounds(442, 390, 66, 18);
        query_panel.add(label_tec_orderIds);
        
        final JTextArea field_tec_orderIds  = new JTextArea();
        field_tec_orderIds.setBounds(508, 380, 150, 40);
        query_panel.add(field_tec_orderIds);
        
        final JButton query_button = new JButton();
        query_button.setText("保存/修改");
        query_button.setBounds(148, 440, 91, 40);
        query_panel.add(query_button);
        query_button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String tec_name = field_tec_name.getText();
                String tec_age = field_tec_age.getText();
                String tec_sex = field_tec_sex.getText();
                String tec_email = field_tec_email.getText();
                String tec_phone = label_tec_phone.getText();
                String tec_qq = field_tec_qq.getText();
                String tec_wechat = field_tec_weChat.getText();
                String tec_address = field_tec_address.getText();
                String tec_idCard = field_tec_idCard.getText();
                String tec_college = field_tec_college.getText();
                String tec_profession = field_tec_profession.getText();
                
                String json = "{"+"name:"+tec_name+",age:"+tec_age+",sex:"+tec_sex+",email:"+tec_email
                        +",phoneNum:"+tec_phone+",qqNum:"+tec_qq+",weChatNum:"+tec_wechat+",address:"
                        +field_tec_address+"}";
                try
                {
                    dos.writeUTF(Request.COMMIT_TEC);
                    res = dis.readUTF();
                    if(Request.COMMIT_TEC.equals(res)){
                        dos.writeUTF(tec_name);
                        dos.writeUTF(tec_phone);
                        dos.writeUTF(tec_qq);
                        dos.writeUTF(tec_wechat);
                        res = dis.readUTF();
                        if(Response.SUCCESS.equals(res)){
                            javax.swing.JOptionPane.showMessageDialog(OrderFrame.this, "提交成功");
                            field_orderId.setText("");
                            field_tec_name.setText("");
                            label_tec_phone.setText("");
                            field_tec_qq.setText("");
                            field_tec_weChat.setText("");
                            field_tec_item.setText("");
                        }else{
                            javax.swing.JOptionPane.showMessageDialog(OrderFrame.this, "提交失败");
                        }
                    }
                    
                } catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        });
        
        //TODO
    }
    

}
