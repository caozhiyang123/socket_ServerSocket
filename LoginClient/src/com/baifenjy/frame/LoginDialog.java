package com.baifenjy.frame;

import java.awt.EventQueue;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

import com.baifenjy.utils.Center;


public class LoginDialog extends JDialog {

    private JPasswordField passwordField_2;
    private JPasswordField passwordField_1;
    private JTextField textField_2;
    private JTextField textField;
    String request = null;
    String response = null;

    Socket socket;
    DataOutputStream dos;
    DataInputStream dis;

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
                    LoginDialog dialog = new LoginDialog();
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

    public LoginDialog() {
        setResizable(false);
        setTitle("管理系统_v1.0_上海力霆教育科技有限公司_[www.baifenjy.com]");
        getContentPane().setLayout(null);
        setBounds(100, 100, 427, 301);// 注册时高度为578，不注册是301

        // 设置窗口居中
        this.setLocation(Center.getPoint(this.getSize()));

        final JTextField textField_1 = new JTextField();
        textField_1.setBounds(148, 90, 192, 42);
        getContentPane().add(textField_1);

        final JLabel label = new JLabel();
        label.setText("帐    号");
        label.setBounds(76, 102, 66, 18);
        getContentPane().add(label);

        final JLabel label_1 = new JLabel();
        label_1.setText("密    码");
        label_1.setBounds(76, 167, 66, 18);
        getContentPane().add(label_1);

        final JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(148, 155, 192, 42);
        getContentPane().add(passwordField);

        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                String phone = new String(textField_1.getText()).trim();
                String password = new String(passwordField.getPassword()).trim();
                if (!phone.equals("") && !password.equals("")) {
                    try {
                        request = "login";
                        dos.writeUTF(request);
                        response = dis.readUTF();
                        if (response.equals("login")) {
                            dos.writeUTF(phone);
                            dos.writeUTF(password);
                            String flag=dis.readUTF();
                            if(flag.equals("success")) {
                                javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "登录成功");
                            }else if(flag.equals("fail")) {
                                javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "登录失败");
                            }
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "用户名密码必须填写...");
                    return;
                }
            }
        });
        button_1.setText("登录");
        button_1.setBounds(230, 222, 106, 36);
        getContentPane().add(button_1);

        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (LoginDialog.this.getHeight() == 301) {
                    LoginDialog.this.setSize(427, 578);
                } else {
                    LoginDialog.this.setSize(427, 301);
                }
                // 设置窗口不断居中
                LoginDialog.this.setLocation(Center.getPoint(LoginDialog.this.getSize()));
            }
        });
        button.setText("注册");
        button.setBounds(76, 222, 106, 36);
        getContentPane().add(button);

        final JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new TitledBorder(null, "注册用户", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        panel.setBounds(10, 278, 401, 226);
        getContentPane().add(panel);

        final JLabel label_2 = new JLabel();
        label_2.setBounds(44, 41, 65, 18);
        label_2.setText("手机号：");
        panel.add(label_2);

        textField = new JTextField();
        textField.setBounds(115, 35, 225, 30);
        panel.add(textField);

        final JButton button_2 = new JButton();
        button_2.setText("发送验证");
        button_2.setBounds(243, 75, 97, 30);
        panel.add(button_2);

        textField_2 = new JTextField();
        textField_2.setBounds(115, 104, 95, 30);
        panel.add(textField_2);

        final JLabel label_3 = new JLabel();
        label_3.setText("验证码：");
        label_3.setBounds(44, 110, 65, 18);
        panel.add(label_3);

        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(115, 143, 231, 30);
        panel.add(passwordField_1);

        passwordField_2 = new JPasswordField();
        passwordField_2.setBounds(115, 175, 231, 30);
        panel.add(passwordField_2);

        final JLabel label_4 = new JLabel();
        label_4.setText("密        码：");
        label_4.setBounds(44, 149, 65, 18);
        panel.add(label_4);

        final JLabel label_5 = new JLabel();
        label_5.setText("验证密码：");
        label_5.setBounds(44, 181, 65, 18);
        panel.add(label_5);

        final JButton button_3 = new JButton();
        button_3.setBounds(47, 510, 97, 30);
        getContentPane().add(button_3);
        button_3.setText("放弃");

        final JButton button_4 = new JButton();
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {

                String phone = textField.getText();
                String password = null;
                String str1 = new String(passwordField_1.getPassword()).trim();
                String str2 = new String(passwordField_2.getPassword()).trim();
                if (!phone.equals("") && !str1.equals("") && !str2.equals("")) {
                    if (str1.equals(str2)) {
                        password = new String(passwordField_2.getPassword()).trim();
                        try {
                            request = "reg";
                            dos.writeUTF(request);
                            String response = dis.readUTF();
                            if (response.equals("reg")) {
                                dos.writeUTF(phone);
                                dos.writeUTF(password);
                            }
                            javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "注册成功...");
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "输入密码不一致...");
                        System.out.println("输入密码不一致...");
                        passwordField_1.setText("");
                        passwordField_2.setText("");
                    }

                } else {
                    javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "用户名密码必须填写...");
                    return;
                }
            }
        });
        
        button_4.setBounds(262, 510, 97, 30);
        button_4.setText("注册用户");
        getContentPane().add(button_4);
        
        connect();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                disconnect();
            }
        });
    }

    private static final String IP = "127.0.0.1";
    private static final int PORT = 8888;
    public void connect() {
        try {
            socket = new Socket(IP, PORT);
            System.out.println("一个客户端登陆中....!");
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());

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
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}