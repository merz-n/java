import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class ClientHandlerServer implements Runnable{
    private Socket clientSocket;
    private int clientId;
    private Semaphore semaphore;

    public ClientHandlerServer(Socket clientSocket, int clientId,Semaphore semaphore) {
        this.clientSocket = clientSocket;
        this.clientId = clientId;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try (DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        ){
            while(true) {

                String data = dataInputStream.readUTF();
                System.out.println("Client #" + clientId + " sent: " + data);
                dataOutputStream.writeUTF("Echo: " + data);
                if(data.equals("/end"))
                    break;
            }
        }catch (IOException e){
            System.out.println("Error client #" + clientId + ": " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                semaphore.release();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Client #" + clientId + " off");
        }
    }
}
