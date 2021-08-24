import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
//    protected static final String HOME = System.getProperty("user.home");
//    protected static final Path PATH = java.nio.file.Paths.get(HOME, "Duke", "data", "duke.txt");

    private Path filePath;

    protected Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public void read() throws IOException {
        String fileContent = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
        System.out.println(fileContent);
    }

    public void upload() throws IOException {
        String taskList = "";
        for (int i = 0; i < Duke.todoList.size(); i++) {
            taskList += Duke.todoList.get(i).encode() + "\n";
        }
        Files.write(filePath, taskList.getBytes());
    }

    public void download() throws IOException {
        List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        for (String hardCode: lines) {
            Duke.todoList.add(Task.decode(hardCode));
        }
    }

    public void createFile() throws IOException {
        if (!java.nio.file.Files.exists(filePath)) {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        };
    }
}
