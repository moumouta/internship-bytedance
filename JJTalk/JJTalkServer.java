package JJTalk;

import JJTalk.ServerUtil.ServerThread;
import JJTalk.pojo.ClientInfo;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class JJTalkServer {
    // 声明ServerSocket对象
    private ServerSocket server;
    // 声明Socket对象socket
    private Socket socket;
    //客户端注册表
    private Map<String, ClientInfo> registerMaps;

    /**
     * 新建服务器对象并等待连接
     */
    public void getServer() {
        try {
            server = new ServerSocket(2006);
            registerMaps=new HashMap<>();
            while (true) {
                // 监听是否有客户端连接
                System.out.println("等待连接！！");
                socket = server.accept();
                System.out.println("连接成功！");
                // 创建并启动连接线程对象
                new ServerThread(socket,registerMaps).start();
            }
        } catch (Exception e) {
            e.printStackTrace(); // 输出异常信息
        }
    }

    public static void main(String[] args) {
        JJTalkServer jjTalkServer=new JJTalkServer();
        jjTalkServer.getServer();
    }
}
