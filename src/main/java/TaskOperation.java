import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TaskOperation {
    private String description;
    private List<Task> tasks;

    public TaskOperation(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void execute() {
        performOperation();
        saveToDisk();
    }

    abstract void performOperation();

    private void saveToDisk() {
        Path pathToOut = Paths.get("./output/tasklist.txt");
        try {
            Files.createDirectories(pathToOut.getParent());
            if (!Files.exists(pathToOut)) {
                Files.createFile(pathToOut);
            }
            Files.write(pathToOut, tasks.stream().map(Object::toString).collect(Collectors.toList()));
        } catch (IOException e) {
            System.out.printf("Failed to save: %s\n", e.getMessage());
        }
    }

    protected String getTasksOverview() {
        return tasks.isEmpty() ? "You have no tasks.\n"
                : String.format("Now you have %d task%s in the list.\n", tasks.size(), (tasks.size() == 1 ? "" : "s"));
    }

    public String getDescription() {
        return description;
    }

    protected List<Task> getTasks() {
        return tasks;
    }

    protected void setDescription(String description) {
        this.description = description;
    }
}
