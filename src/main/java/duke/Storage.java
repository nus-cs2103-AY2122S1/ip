package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents a Storage object that handles file IO for tasks.
 */
public class Storage {
    private final Path path;

    public Storage() {
        this.path = Paths.get(".", "db.txt");
    }

    public Storage(Path path) {
        this.path = path;
    }

    /**
     * Saves given list of tasks to file specified at instantiation.
     * If file does not exist, it will be created.
     * The list of tasks will be formatted in easily parsable format delimited
     * by |.
     *
     * @param taskList The list of tasks to be saved.
     * @throws IOException If something goes wrong with File operations.
     */
    public void save(List<Task> taskList) throws IOException {
        boolean fileExists = Files.exists(path);

        if (!fileExists) {
            Files.createFile(path);
        }
        BufferedWriter file = Files.newBufferedWriter(path);

        for (Task t : taskList) {
            file.write(t.toDatabaseString() + "\n");
        }

        file.close();
    }

    /**
     * Returns a List of Task by parsing file specified at instantiation.
     * If file does not exist, empty List will be returned.
     *
     * @return List of Task saved in file.
     * @throws IOException       If something goes wrong with File operations.
     * @throws DateTimeException If file's date data is corrupted.
     */
    public List<Task> load() throws IOException, DateTimeException {
        boolean fileExists = Files.exists(path);
        List<Task> taskList = new ArrayList<>();

        if (!fileExists) {
            return new ArrayList<>();
        }

        BufferedReader file = Files.newBufferedReader(path);
        file.lines().forEachOrdered(line -> {
            String[] parts = line.split("[|]");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            switch (type) {
            case "T":
                taskList.add(ToDo.of(isDone, parts[2]));
                break;
            case "D":
                taskList.add(Deadline.of(isDone, parts[2], parts[3]));
                break;
            case "E":
                taskList.add(Event.of(isDone, parts[2], parts[3]));
                break;
            default:
                break;
            }
        });

        return taskList;
    }
}
