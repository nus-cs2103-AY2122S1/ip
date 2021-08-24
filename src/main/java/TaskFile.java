import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskFile {
    private static final Path DEFAULT_DIR = Paths.get("src", "main", "data");

    private File tasks;

    public TaskFile(String fileName) {
        try {
            if (!Files.exists(DEFAULT_DIR)) {
                Files.createDirectories(DEFAULT_DIR);
            }
            Path taskPath = Paths.get(DEFAULT_DIR.toString(), fileName);
            if (Files.notExists(taskPath)) {
                this.tasks = Files.createFile(taskPath).toFile();
            } else {
                this.tasks = taskPath.toFile();
            }
        } catch (IOException e) {
            Message errorMessage = new Message("☹ OOPS!!! Unable to find or create a file!");
            errorMessage.printMessage();
        }
    }

    public static Task lineToTask(String line) {
        String[] data = line.split(" \\| ");
        return Task.of(data);
    }

    public ArrayList<Task> parseToTasks() throws DukeException {
        ArrayList<Task> result = new ArrayList<>();
        try {
            List<String> data = Files.readAllLines(tasks.toPath());
            data.forEach(x -> result.add(lineToTask(x)));
        } catch (IOException e) {
            Message errorMessage = new Message("☹ OOPS!!! Something wrong happened when reading the file.");
            errorMessage.printMessage();
        } catch (DukeException e) {
            Message errorMessage = new Message(e.getMessage());
            errorMessage.printMessage();
        }
        return result;
    }
}
