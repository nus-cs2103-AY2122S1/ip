package duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import duke.logic.tasks.Deadline;
import duke.logic.tasks.Event;
import duke.logic.tasks.Task;
import duke.logic.tasks.ToDo;

// to divide into
// encoder
// decoder

/**
 * Loads data from file upon start and saves data to file upon exit.
 */
public class Storage {
    private static final String FILE_NAME = "./duke.txt";
    private static final Path FILE_PATH = Paths.get(FILE_NAME);

    /**
     * Loads data from file and formats it into a list of tasks.
     *
     * @return The list of tasks read from the file.
     * @throws IOException If an I/O error occurs.
     */
    public static List<Task> load() throws IOException {
        if (!Files.exists(FILE_PATH)) {
            Files.createFile(FILE_PATH);
        }
        List<String> lines = Files.readAllLines(FILE_PATH);
        List<Task> tasks = new ArrayList<>(lines.size());
        for (String s : lines) {
            String[] tokens = s.split(" \\| ");
            Task t;
            boolean isDone = tokens[1].equals("1");
            switch (tokens[0]) {
            case "T":
                t = new ToDo(tokens[2], isDone);
                break;
            case "D":
                LocalDateTime by = LocalDateTime.parse(tokens[3]);
                t = new Deadline(tokens[2], isDone, by);
                break;
            case "E":
                LocalDateTime at = LocalDateTime.parse(tokens[3]);
                LocalDateTime end = tokens[4].equals("null")
                        ? null
                        : LocalDateTime.parse(tokens[4]);
                t = new Event(tokens[2], isDone, at, end);
                break;
            default:
                throw new IOException("Corrupt data found!");
            }
            tasks.add(t);
        }
        return tasks;
    }

    /**
     * Saves data of the current task list to file.
     *
     * @param data The data of the current task list.
     * @throws IOException If an I/O error occurs during writing.
     */
    public static void save(List<String> data) throws IOException {
        Files.write(FILE_PATH, data, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
