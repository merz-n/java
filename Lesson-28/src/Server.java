import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class Server {
        public static void main(String[] args) {
            try (ServerSocket serverSocket = new ServerSocket(7)) {
                System.out.println("server started");
                int clientId = 1;
                Semaphore semaphore = new Semaphore(3);

                while (true){
                    Socket socket = serverSocket.accept();
                    if(semaphore.tryAcquire()){
                        int currentId = clientId++;
                        System.out.println("Client #" + currentId + " connected");
                        new Thread(new ClientHandlerServer(socket,currentId,semaphore)).start();
                    }else{
                        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                        dos.writeUTF("Server is full. Try later.");
                        System.out.println("Client #" + clientId + " connected");
                        socket.close();
                    }

                }
                /*
                while(clientId < 4){
                    Socket socket = serverSocket.accept();
                    System.out.println("Client #" + clientId + " connected");
                    ClientHandlerServer clientHandlerServer = new ClientHandlerServer(socket, clientId);
                    new Thread(clientHandlerServer).start();
                    clientId ++;
                }*/


                //System.out.println("Server reached max client limit (3).");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

