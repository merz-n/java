import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileStorage implements ObjectStorage<Path>{
    private Path rootDirectory;

    public FileStorage(Path rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    @Override
    public void put(String namespace, String name, Path sourceFile) {
        try {
            Path targetDir = rootDirectory.resolve(namespace);
            Path targetFile = targetDir.resolve(name);

            if(!Files.exists(targetDir)){
                Files.createDirectories(targetDir);
            }
            Files.copy(sourceFile,targetFile, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Path get(String namespace, String name) throws ObjectNotFoundException {
        Path pathNew = rootDirectory.resolve(namespace).resolve(name);
        if(Files.exists(pathNew) && Files.isRegularFile(pathNew)){
            return pathNew;
        } else {
            throw new ObjectNotFoundException("Not File: " + pathNew);
        }

    }
}
