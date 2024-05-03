import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String host = "localhost";
        int port = 8100;
        while (true) {
            try (Socket clientSocket = new Socket(host, port);
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
            ) {
                System.out.println("Enter your message(Input 'quit' to exit):");
                String message = sc.nextLine();
                out.println(message + "\n" + "Host: localhost\n\n\n");
                String resp = in.readLine();
                if (resp == null) {
                    return;
                }
                System.out.println(resp);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}