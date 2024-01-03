package JJTalk.ClientUtil;

import JJTalk.pojo.ClientInfo;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientAbleUtil {

    private ObjectOutputStream objectOut;
    private ObjectInputStream objectIn;
    private PrintWriter infoOut;
    private BufferedReader infoIn;
    private DataOutputStream DataOut;
    private DataInputStream DataIn;


    /**
     * 生成活跃信息
     * @return
     * @throws UnknownHostException
     */
    public static ClientInfo makeActiveClientInfo() throws UnknownHostException {
        InetAddress inetAdder = InetAddress.getLocalHost();
        ClientInfo clientInfo=new ClientInfo();
        clientInfo.setClientId(String.valueOf(System.currentTimeMillis()));
        clientInfo.setActive("Y");
        clientInfo.setIp(inetAdder.getHostAddress());
        clientInfo.setPort("1998");
        clientInfo.setQuery("N");
        return clientInfo;
    }

    /**
     * 发送信息
     * @param info
     * @param socket
     */
    public void sendInfo(String info, Socket socket){
        try{
            if(infoOut==null){
                infoOut=new PrintWriter(socket.getOutputStream(),true);
            }
            infoOut.println(info);
            System.out.println("发送消息完成: "+info+"发送到："+socket.getInetAddress().getHostAddress()+":"+socket.getPort());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 发送对象
     * @param obj 要发送的对象
     * @param socket 连接套接字
     */
   public void sendObject(Object obj, Socket socket){
        try {
            if(objectOut==null){
                objectOut=new ObjectOutputStream(socket.getOutputStream());
            }
            objectOut.writeObject(obj);
            System.out.println("发送对象完成");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 发送数据文件
     * @param file 要发送的文件对象
     * @param socket 连接套接字
     */
    public void sendData(File file, Socket socket){
        long lengths;
        try {
            if(socket!=null){
                if(DataOut==null){
                    DataOut=new DataOutputStream(socket.getOutputStream());
                }
                DataInputStream inStream = null;// 定义数据输入流对象
                FileInputStream inFile;
                if (file != null) {
                    lengths = file.length();
                    inFile=new FileInputStream(file);
                    inStream = new DataInputStream(inFile);
                } else {
                    System.out.println("没有选择文件。");
                    return;
                }
                DataOut.writeLong(lengths);
                DataOut.writeUTF(file.getName());
                byte[] bt = new byte[(int) lengths];
                int len = -1;
                while ((len = inStream.read(bt)) != -1) {
                    DataOut.write(bt);// 将字节数组写入输出流
                }
                inStream.close();
                inFile.close();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 监听接收服务端返回的对象信息
     * @param socket
     * @return
     */
    public ClientInfo getClientInfoFromServer(Socket socket) throws IOException {
        try {
            if(socket!=null){
                if(objectIn==null){
                    objectIn=new ObjectInputStream(socket.getInputStream());
                }
                while (true){
                    ClientInfo clientInfo=(ClientInfo) objectIn.readObject();
                    if(clientInfo!=null){
                        System.out.println("接收对象完成");
                        return clientInfo;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            objectIn.close();
        }
        return null;
    }

    /**
     * 监听其他节点传过来的信息
     * @param socket
     * @return
     */
    public  String getInfo(Socket socket){
        String info=null;
        try {
            if(socket!=null){
                if(infoIn==null){
                    infoIn=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                }
                while (true){
                    info=infoIn.readLine();
                    if(info!=null){
                        System.out.println("接收消息完成:"+info);
                        return info;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 等待客户端发送数据并接收
     */
    public boolean getData(Socket socket) {
        try {
            if(socket!=null){
                if(DataIn==null){
                    DataIn=new DataInputStream(socket.getInputStream());
                }
                while (true){
                    // 读取数据文件大小
                    long lengths = DataIn.readLong();
                    if(lengths>0){
                        String fileName=DataIn.readUTF();
                        // 创建字节数组
                        byte[] bt = new byte[(int) lengths];
                        for (int i = 0; i < bt.length; i++) {
                            bt[i] = DataIn.readByte();// 读取字节信息并存储到字节数组
                        }
                        File img=new File(String.valueOf(Math.random())+fileName);
                        OutputStream out=new DataOutputStream(new FileOutputStream(img));
                        out.write(bt);
                        System.out.println("文件接收成功！！");
                        return true;
                    }
                }
            }

        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (DataIn != null) {
                    DataIn.close();// 关闭流
                }
                if (socket != null) {
                    socket.close(); // 关闭套接字
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
