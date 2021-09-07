package duke;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private Path filePath;

    protected Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * @return The data that is currently on the hard-drive
     * @throws IOException
     */
    public List<String> read() throws IOException {
        List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        for (String hardCode : lines) {
            System.out.println(hardCode);
        }
        return lines;
    }

    /**
     * Clear the data in the hard-drive
     *
     * @throws IOException
     */
    public void clear() throws IOException {
        String taskList = "";
        Files.write(filePath, taskList.getBytes());
    }

    /**
     * Update the data in the hard-drive
     *
     * @throws IOException
     */
    public void upload() throws IOException {
        String taskList = "";
        for (int i = 0; i < Duke.todoList.size(); i++) {
            taskList += Duke.todoList.get(i).encode() + "\n";
        }
        Files.write(filePath, taskList.getBytes());
    }

    /**
     * Download the data in the hard-drive
     *
     * @throws IOException
     */
    public void downloadDataToTodoList() throws IOException {
        List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        for (String hardCode : lines) {
            Duke.todoList.add(Task.decode(hardCode));
        }
    }

    /**
     * Create a file to store data if it hasn't been created
     *
     * @throws IOException
     */
    public void createFile() throws IOException {
        if (!java.nio.file.Files.exists(filePath)) {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        }
    }
}
