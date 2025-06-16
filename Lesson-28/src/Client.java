import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    public static void main(String[] args)  {
        try {
            Socket socket = new Socket("localhost", 7);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            new Thread(() ->{
                try {
                while (true){
                String serverMes = dataInputStream.readUTF();
                System.out.println("Server: " + serverMes);
                    if (serverMes.equals("/end")) break;
                }
            }catch (IOException e){
                System.out.println("Ошибка приёма от сервера: " + e.getMessage());
            }
            }).start();
            while(true) {
                String userInput = scanner.nextLine();
                System.out.println("received: " + userInput);
                dataOutputStream.writeUTF(userInput);
                if(userInput.equals("/end"))
                    break;
            }
            socket.close();
            System.out.println("Client ended");
        } catch (IOException e) {
            System.out.println("Ошибка подключения к серверу: " + e.getMessage());
        }

    }
}
