package P2P_BothClientServer_OneSlide;

import java.io.*;
import java.net.*;

public class P1 {

    public static void main(String[] args) throws IOException {

        // Connect to the P2 (Server) at the specified IP address and port
        Socket socket = new Socket("127.0.0.1", 5000);

        // Create a new thread to handle incoming messages from P2
        new Thread(new IncomingReader(socket)).start();

        // Get user input from the console
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        // Create a PrintWriter to send messages to P2
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        while (true) {

            // Read user input
            String input = userInput.readLine();

            // If the user input is "exit," break out of the loop
            if (input.equals("exit")) {
                break;
            }

            // Send the message to P2
            out.println("P1: " + input);
        }
    }
}

// This thread runs in the background and reads messages from the server and prints them to the console
class IncomingReader implements Runnable {

    private Socket socket;

    public IncomingReader(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            // Get the InputStream of the socket
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;
            // Continuously read messages from the server
            while ((message = reader.readLine()) != null) {
                System.out.println(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
