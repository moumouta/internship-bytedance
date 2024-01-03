package P2P_ClientServer;
// 服务器端
import java.io.*;
import java.net.*;

//public class Server {
//}

class Server {

    public static void main(String[] args) throws IOException {

        // 在端口5000上监听连接
        ServerSocket server = new ServerSocket(5000);

        // 接收客户端连接请求
        Socket socket = server.accept();

        // 为每个客户端连接创建一个新的线程进行处理
        new Thread(new ClientHandler(socket)).start();

        // 关闭服务器端口
        server.close();
    }
}

// 客户端处理线程
class ClientHandler implements Runnable {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            // 获取 socket 的输入流用于接收数据
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 获取 socket 的输出流用于发送数据
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {

                // 读取客户端发送来的消息
                String message = in.readLine();

                // 如果客户端发送"exit"则关闭连接
                if (message.equals("exit")) {
                    break;
                }

                // 打印接收到的消息
                System.out.println("Client: " + message);

                // 回复客户端
                out.println("Message received");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
