import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ObjectNotFoundException {
        //Path file = Paths.get("exampl.txt");
        Path file1 = Paths.get("test.txt");
        Files.writeString(file1,"This task demonstrates a practical application of Java NIO APIs including Path, Files, ByteBuffer, and FileChannel.");

        Path storageReader = Paths.get("storage");
        FileStorage fileStorage = new FileStorage(storageReader);
        FileStorageReader fileStorageReader = new FileStorageReader(fileStorage);

        String namespace = "test";
        String name = "exampl.txt";
        fileStorage.put(namespace,name,file1);

        byte[] fullContent = fileStorageReader.read(namespace,name);
        System.out.println("All text: " + new String(fullContent));

        List<byte[]> chucks = fileStorageReader.read(namespace,name,20);
        System.out.println("Content on counted parts: ");

        for(int i = 0; i < chucks.size(); i++){
            System.out.println("Part: " + (i+1) + ":" + new String(chucks.get(i)));
        }

    }
}