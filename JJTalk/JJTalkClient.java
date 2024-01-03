package JJTalk;

import JJTalk.ClientUtil.ClientAbleUtil;
import JJTalk.ClientUtil.ClientThread;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static JJTalk.ClientUtil.ClientAbleUtil.makeActiveClientInfo;

public class JJTalkClient {
    // 创建流对象
    private ObjectOutputStream out = null;
    // 创建流对象
    private ObjectInputStream in = null;
    //声明服务器Socket
    private ServerSocket serverSocket;

    /**
     * 新建服务器对象并等待连接
     */
    public void getConnect(){
        try { // 捕捉异常
            Socket socket;
            System.out.println("开始连接服务器");
            socket = new Socket("127.0.0.1", 2006); // 实例化Socket对象
            //客户端信息注册
            ClientAbleUtil ableUtil=new ClientAbleUtil();
            ableUtil.sendObject(makeActiveClientInfo(),socket);
            new ClientThread(socket,ableUtil,"N","成功连接到服务器").start();
        } catch (Exception e) {
            e.printStackTrace(); // 输出异常信息
        }
    }

    /**
     * 新建服务器对象并等待连接
     */
    public void getServer() {
        try {
            serverSocket = new ServerSocket(1998);
            while (true) {
                Socket socket;
                // 监听是否有客户端连接
                System.out.println("等待连接！！");
                socket = serverSocket.accept();
                System.out.println("连接成功！");
                ClientAbleUtil ableUtil=new ClientAbleUtil();
                // 创建并启动连接线程对象
                ClientThread clientThread=new ClientThread(socket,ableUtil,"Y",socket.getInetAddress().getHostName()+"向您发起会话");
                clientThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace(); // 输出异常信息
        }
    }

    public static void main(String[] args) {
       JJTalkClient client=new JJTalkClient();
       //连接服务器线程
       new Thread(new Runnable() {
           @Override
           public void run() {
               client.getConnect();
           }
       }).start();
       //监听连接线程
       new Thread(new Runnable() {
           @Override
           public void run() {
               client.getServer();
           }
       }).start();
    }

}
