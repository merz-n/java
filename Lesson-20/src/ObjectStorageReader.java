import java.util.List;

public interface ObjectStorageReader {
    byte[] read (String namespace, String name) throws ObjectNotFoundException;
    List<byte[]> read(String namespace, String name, int chuckSize) throws ObjectNotFoundException;
}
