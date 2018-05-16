package home.uploader.storage;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.stream.Stream;
import static home.constant.Constants.IMAGE_DOMAIN;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

//    @Autowired
//    public FileSystemStorageService(StorageProperties properties) {
//        Path path  = Paths.get(properties.getLocation());
//        this.rootLocation = path;
//    }

    public FileSystemStorageService() {
//        Path.get(String)
//        convert given string to Path object
        Path path  = Paths.get("upload-dir");
        this.rootLocation = path;
    }

//    MultipartFile is uploaded file that is received through multipart request
    @Override
    public String store(MultipartFile file) {
        try {
            boolean b = file.isEmpty();
            if (b) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
//            Files.copy(InputStream, Path)
//            copies all bytes from input stream to a file

            String filename = new Date().getTime() + file.getOriginalFilename();
            Path path = this.rootLocation.resolve(filename);
            Files.copy(file.getInputStream(), path);
            return filename;
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
//            (Files.walk) walks through file tree starting from the given path
//            returns stream of all path
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
//                    (Path.relativize)
//                    root (this) = a/b
//                    given path = a/b/somefile
//                    returns somefile
                    .map(givenPath -> this.rootLocation.relativize(givenPath));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String givenPath) {
//        Path.resolve
//        rootLocation (this path) = e.g. root/
//        given path = filename
//        returns root/filename
        return rootLocation.resolve(givenPath);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        }catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public void delete() {
        try {
            Files.deleteIfExists(rootLocation);
        }catch (IOException e) {
            throw new StorageException("Could not delete storage", e);
        }
    }
}
