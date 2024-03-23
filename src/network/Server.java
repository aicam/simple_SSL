package network;

import java.net.ServerSocket;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Server {
    public static void run() throws IOException {
        // Specify the port number
        int portNumber = 8080;

        try (
                // Create a server socket on the specified port
                ServerSocket serverSocket = new ServerSocket(portNumber)
        ) {
            System.out.println("Server is listening on port " + portNumber);

            // Wait for a client connection
            Socket clientSocket = serverSocket.accept();

            System.out.println("Client connected!");

            // Get the input stream from the client socket
            InputStream inputStream = clientSocket.getInputStream();

            // Buffer for reading data
            // Buffer for reading data
            byte[] buffer = new byte[1024];

            int bytesRead;
            // Read data from the client until the stream is closed
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                // Print the received bytes
                System.out.println("Received " + bytesRead + " bytes:");
                for (int i = 0; i < bytesRead; i++) {
                    System.out.print(buffer[i] + " ");
                }
                System.out.println();
            }

            System.out.println("Client disconnected.");
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            e.printStackTrace();
        }
    }
}
