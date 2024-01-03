package JJTalk.ClientUtil;

import JJTalk.pojo.ClientInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread{
    //连接服务器的套接字
    Socket socket;
    ClientAbleUtil ableUtil;
    //连接客户端的套接字
    Socket clientSocket;
    ClientAbleUtil ableUtil1;
    //是否客户端标志
    String clientFlat;
    String text;
    // 创建JTextArea对象
    public JTextArea outInfo = new JTextArea();

    public ClientThread(Socket socket,ClientAbleUtil ableUtil,String clientFlat,String text) throws IOException {
        this.socket = socket;
        this.ableUtil=ableUtil;
        this.clientFlat=clientFlat;
        this.text=text;
    }

    public void run() {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //运行图像化界面
                    Frame frame=new Frame(text);
                    frame.setVisible(true);
                }
            }).start();
            //监听发送方发送的消息
            if(clientFlat.equals("Y")){
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        while (true){
                            String line=ableUtil.getInfo(socket);
                            if(line!=null){
                                outInfo.append("\n");
                                outInfo.append(socket.getInetAddress().getHostName()+"发送消息:"+line);
                            }
                        }
                    }
                }).start();
            }

        } catch (Exception e) {
            System.out.println(socket + "已经退出。\n");
        }
    }

    class Frame extends JFrame{
        //连接客户端文本框
        private JTextField connectJText;
        //发送信息文本狂
        private JTextField sendInfoJText;

        public Frame(String text) {
            super();
            setTitle("客户端程序");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(500, 100, 800, 700);
            final JScrollPane scrollPane = new JScrollPane();
            getContentPane().setLayout(new GridLayout(1,2));
            getContentPane().add(scrollPane);
            scrollPane.setViewportView(outInfo);
            getContentPane().add(getMainPanel());
            outInfo.append(text);
        }

        /**得到按钮对象
         * @return 按钮对象
         */
        protected JButton getButton(String text) {
            JButton jButton=new JButton();
            jButton = new JButton();
            jButton.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    if(text.equals("连接")){
                        ClientInfo clientInfo=new ClientInfo();
                        clientInfo.setClientId(connectJText.getText());
                        clientInfo.setQuery("Y");
                        System.out.println("开始向服务器发送查询信息");
                        ableUtil.sendObject(clientInfo,socket);
                        //等待接收服务器发送来的客户信息
                        try{
                            clientInfo=ableUtil.getClientInfoFromServer(socket);
                        }catch (Exception e3){
                            e3.printStackTrace();
                        }
                        if(clientInfo.isActive().equals("Y")){
                            //连接通信对方
                            try {
                                outInfo.append("开始连接客户："+clientInfo.getClientId());
                                // 实例化Socket对象
                                clientSocket = new Socket(clientInfo.getIp(),Integer.parseInt(clientInfo.getPort()));
                                ableUtil1=new ClientAbleUtil();
                                outInfo.append("\n");
                                outInfo.append("连接成功！！可以开始通信了");
                                //监听请求方发送的消息
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        while (true){
                                            String line=ableUtil1.getInfo(clientSocket);
                                            if(line!=null){
                                                outInfo.append("\n");
                                                outInfo.append(clientSocket.getInetAddress().getHostName()+"发送消息："+line);
                                            }
                                        }
                                    }
                                }).start();
                            } catch (Exception ex) {
                                ex.printStackTrace(); // 输出异常信息
                            }
                        }else if(clientInfo.isActive().equals("N")){
                            //对方不在线，输出提示信息
                            outInfo.append("对方不在线，请稍后在试！！");
                        }else{
                            outInfo.append("不存在此用户！！！请检查");
                        }
                    }
                    //发送消息给对方
                    if(text.equals("发送")){
                        if(clientFlat.equals("N")){
                            ableUtil1.sendInfo(sendInfoJText.getText(),clientSocket);
                        }else{
                            ableUtil.sendInfo(sendInfoJText.getText(),socket);
                        }
                    }

                    if(text.equals("选择文件")){

                    }

                    if(text.equals("发送文件")){

                    }

                }
            });
            jButton.setText(text);

            return jButton;
        }
        /**
         * 得到画板对象
         * @return 返回画板
         */
        protected JPanel getMainPanel() {
            JPanel panel;
            panel = new JPanel();
            panel.setLayout(new GridLayout(4,1));
            panel.add(getConnectPanel());
            panel.add(getSendDataPanel());
            panel.add(getSendDataPathPanel());
            panel.add(getSendInfoPanel());
            return panel;
        }

        /**
         * 得到连接客户端画板对象
         * @return 返回画板
         */
        protected JPanel getConnectPanel() {
            JPanel panel;
            connectJText=getJTextFiled(100,25);
            panel = new JPanel();
            panel.add(getLabel("对方客户端名称："));
            panel.add(connectJText);
            panel.add(getButton("连接"));
            return panel;
        }

        /**
         * 得到发送信息的画板
         * @return
         */
        protected JPanel getSendInfoPanel() {
            JPanel panel;
            sendInfoJText=getJTextFiled(200,100);
            panel = new JPanel();
            panel.add(getLabel("请输入要发送的信息:"));
            panel.add(sendInfoJText);
            panel.add(getButton("发送"));
            return panel;
        }
        /**
         * 得到发送数据文件的画板
         * @return 发送数据的画板
         */
        protected JPanel getSendDataPanel() {
            JPanel panel;
            panel = new JPanel();
            panel.add(getLabel("选择要发送的文件:"));
            panel.add(getButton("选择文件"));
            return panel;
        }
        /**
         * 得到发送数据文件的画板
         * @return 发送数据的画板
         */
        protected JPanel getSendDataPathPanel() {
            JPanel panel;
            panel = new JPanel();
            panel.add(getLabel("文件路径："));
            panel.add(getButton("发送文件"));
            return panel;
        }

        /**文本标签
         * @return
         */
        protected JLabel getLabel(String text) {
            JLabel jLabel=new JLabel();
            jLabel.setText(text);
            return jLabel;
        }

        /**获得输入框
         * @param width
         * @param height
         * @return 输入框对象
         */
        protected JTextField getJTextFiled(int width,int height) {
            JTextField jTextField;
            jTextField = new JTextField();
            jTextField.setPreferredSize(new Dimension(width, height));
            return jTextField;
        }

    }

}

