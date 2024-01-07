package P2P_OneSlide;

import java.io.*;
import java.net.*;

class P2 {

    public static void main(String[] args) throws IOException {

        // Listen for connections on port 5000
        ServerSocket server = new ServerSocket(5000);

        // Accept a connection request from a client
        Socket socket = server.accept();

        // Create a new thread to handle each client connection
        new Thread(new ClientHandler(socket)).start();

        // Close the server socket
        server.close();
    }
}

// Client handling thread
class ClientHandler implements Runnable {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            // Get the InputStream of the socket for receiving data
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Get the OutputStream of the socket for sending data
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {

                // Read the message sent by the client
                String message = in.readLine();

                // If the client sends "exit," close the connection
                if (message.equals("exit")) {
                    break;
                }

                // Print the received message
                System.out.println(message);

                // Reply to the client
                out.println("P2: Message received");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
