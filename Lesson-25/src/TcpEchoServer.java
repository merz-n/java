import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpEchoServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(7)) {
            System.out.println("server started");
            Socket accept = serverSocket.accept();
            System.out.println("Client connected");
            DataInputStream dataInputStream = new DataInputStream(accept.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(accept.getOutputStream());

            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                try {
                    while (true) {
                        String msg = scanner.nextLine();
                        dataOutputStream.writeUTF(msg);
                        if (msg.equalsIgnoreCase("/end")) {
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            while(true) {
                String data = dataInputStream.readUTF();
                System.out.println("received: " + data);
                dataOutputStream.writeUTF("Echo: " + data);
                if(data.equals("/end"))
                    break;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
