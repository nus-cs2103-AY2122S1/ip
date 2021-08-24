import java.io.BufferedWriter;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final String DIVIDER = "\n\n";
    private final List<Task> tasks;
    private final Path directoryPath = Paths.get("data");
    private final Path filePath = Paths.get("data", "blue.txt");


    public TaskList() {
        tasks = new ArrayList<>();
        boolean success = loadData();
        if (!success) {
            createFile();
        }
    }

    private boolean loadData() {
        if (!Files.exists(directoryPath) || !Files.exists(filePath)) {
            return false;
        }
        try {
            String allContent = Files.readString(filePath);
            String[] taskRepresentations = allContent.split(DIVIDER);
            for (String taskRepresentation : taskRepresentations) {
                Task task = TaskMapper.makeTask(taskRepresentation);
                tasks.add(task);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void createFile() {
        try {
            if (!Files.exists(directoryPath)) {
                Files.createDirectory(directoryPath);
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            BufferedWriter writer = Files.newBufferedWriter(filePath);
            for (Task task : tasks) {
                writer.write(TaskMapper.makeString(task));
                writer.write(DIVIDER);
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
