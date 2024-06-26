import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        System.out.println("Server started on port 8100");
        while(true) {
            try (ServerSocket serverSocket = new ServerSocket(8100);
                 Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {
                final String name = in.readLine();
                System.out.println("New connection accepted from " + clientSocket.getInetAddress() + "\n" + "Receive message: " + name);
                System.out.println("==========================================");
                if(name.equals("quit")){
                    serverSocket.close();
                    clientSocket.close();
                    out.close();
                    in.close();
                    return;
                }
                out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
