import java.io.BufferedWriter;
import java.nio.file.Files;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
        save();
    }

    public Task get(int i) {
        return tasks.get(i - 1);
    }

    public Task remove(int i) {
        Task task = tasks.remove(i - 1);
        save();
        return task;
    }

    private void save() {
        try {
            Path directoryPath = Paths.get("data");
            if (!Files.exists(directoryPath)) {
                Files.createDirectory(directoryPath);
            }
            Path filePath = Paths.get("data", "blue.txt");
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            BufferedWriter writer = Files.newBufferedWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int size() {
        return tasks.size();
    }
}
