package JJTalk.ServerUtil;

import JJTalk.pojo.ClientInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class ServerThread extends Thread{
    // 创建流对象
    private ObjectOutputStream out = null;
    // 创建流对象
    private ObjectInputStream in = null;
    Socket socket;
    //客户端注册表
    Map<String,ClientInfo> registerMaps;

    public ServerThread(Socket socket, Map<String,ClientInfo>registerMaps) throws IOException {
        this.socket = socket;
        this.registerMaps=registerMaps;
        in=new ObjectInputStream(socket.getInputStream());
        out=new ObjectOutputStream(socket.getOutputStream());
    }

    public void run() {
        ClientInfo clientInfo = null;
        try {
            while (true){
                clientInfo=(ClientInfo) in.readObject();
                if(clientInfo!=null){
                    if(clientInfo.isQuery().equals("N")){
                        registerMaps.put(clientInfo.getClientId(),clientInfo);
                        System.out.println("注册成功：");
                        System.out.println(clientInfo.toString());
                    }else{
                        ClientInfo clientInfo1=registerMaps.get(clientInfo.getClientId());
                        if(clientInfo1!=null){
                            out.writeObject(clientInfo1);
                            System.out.println("查询完成：");
                            System.out.println(registerMaps.get(clientInfo.getClientId()).toString());
                        }else{
                            clientInfo1=new ClientInfo();
                            clientInfo1.setActive("F");
                            out.writeObject(clientInfo1);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(socket + "已经退出。\n");
        }
    }
}
