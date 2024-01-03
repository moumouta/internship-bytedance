package P2P_ClientServer_OneSlide;
import java.io.*;
import java.net.*;

//public class Client {
//}

public class Client {

    public static void main(String[] args) throws IOException {

        // 连接到服务器端指定的IP地址和端口
        // Socket socket = new Socket("192.168.43.186", 5000);
        Socket socket = new Socket("127.0.0.1", 5000);

        // 创建一个新的线程来处理从服务器接收的消息
        new Thread(new IncomingReader(socket)).start();

        // 获取用户在控制台的输入
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        // 用于向服务器发送消息
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        while (true) {

            // 读取用户输入
            String input = userInput.readLine();

            // 如果用户输入"exit"则退出循环
            if (input.equals("exit")) {
                break;
            }

            // 向服务器发送消息
            out.println(input);
        }
    }
}

// 该线程在后台运行,负责读取服务器的消息并打印输出
class IncomingReader implements Runnable {

    private Socket socket;

    public IncomingReader(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            // 获取socket的InputStream
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;
            // 持续读取服务器发送的消息
            while ((message = reader.readLine()) != null) {
                System.out.println("Server: " + message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
