import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileHandler {
    private static final Path DIRECTORY = Paths.get("data");
    private static final Path FILE_PATH = Paths.get("data", "duke.txt");

    private static void directoryCreator() {
        try {
            Files.createDirectories(DIRECTORY);
        } catch (FileAlreadyExistsException e) {

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    private static void fileCreator() {
        try {
            Files.createFile(FILE_PATH);
        } catch (FileAlreadyExistsException e) {

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<String> open() {
        directoryCreator();
        fileCreator();
        List<String> saveFile = null;
        try {
            saveFile = Files.readAllLines(FILE_PATH);
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
        
        return saveFile;

    }
    
    public static void save(String text) {
        try {
            Files.write(FILE_PATH, text.getBytes());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
