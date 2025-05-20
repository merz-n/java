import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

public class FileStorageReader implements ObjectStorageReader{
    private FileStorage fileStorage;

    public FileStorageReader(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    @Override
    public byte[] read(String namespace, String name) throws ObjectNotFoundException {
        try{
            Path path = fileStorage.get(namespace,name);
            byte[] p = Files.readAllBytes(path);
            return p;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<byte[]> read(String namespace, String name, int chuckSize) throws ObjectNotFoundException {
        try {
            Path path = fileStorage.get(namespace,name);
            FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ);
            ByteBuffer byteBuffer = ByteBuffer.allocate(chuckSize);
            int byteRead = fileChannel.read(byteBuffer);
            List<byte[]> chucks = new ArrayList<>();
            while (byteRead != -1){
                byteBuffer.flip();
                byte[] chuck = new byte[byteBuffer.remaining()];
                byteBuffer.get(chuck);
                chucks.add(chuck);
                byteBuffer.clear();
                byteRead = fileChannel.read(byteBuffer);

            }
             return chucks;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
