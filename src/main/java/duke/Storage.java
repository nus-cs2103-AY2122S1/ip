package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import duke.exception.DukeException;
import duke.exception.IllegalTaskTypeException;
import duke.exception.LoadFileCorrupted;
import duke.exception.LoadFileMissing;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Storage handles saving and loading task lists.
 *
 * @author Gabriel Goh
 */
public class Storage {

    private final Path filePath;

    /**
     * Constructor to create a storage.
     *
     * @param filePath Path to storage file
     */
    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    /**
     * Saves list of tasks into storage.
     *
     * @param taskList List of tasks
     */
    public void save(TaskList taskList) throws DukeException {
        createIfNotExist(filePath.getParent(), filePath);
        assert(hasSave());
        try {
            Files.writeString(filePath, taskList.saveString());
        } catch (IOException e) {
            throw new DukeException(e.toString());
        }
    }

    /**
     * Loads list of tasks into storage.
     *
     * @return Task list
     */
    public TaskList load() throws DukeException {
        if (!hasSave()) {
            throw new LoadFileMissing();
        }

        String s;
        try {
            s = Files.readString(filePath);
        } catch (IOException e) {
            throw new DukeException(e.toString());
        }
        TaskList taskList = new TaskList();
        for (String line : s.split("\n")) {
            String[] params = line.split(" \\| ");
            if (params.length < 2) {
                throw new LoadFileCorrupted();
            }

            Task task;
            switch (params[0]) {
            case "T":
                task = new Todo(params[2]);
                break;

            case "D":
                if (params.length < 3) {
                    throw new LoadFileCorrupted();
                }

                task = new Deadline(params[2], Parser.parseLoadTemporal(params[3]));
                break;

            case "E":
                if (params.length < 3) {
                    throw new LoadFileCorrupted();
                }

                task = new Event(params[2], Parser.parseLoadTemporal(params[3]));
                break;

            default:
                throw new IllegalTaskTypeException(params[0]);
            }

            if (Objects.equals(params[1], "1")) {
                task.markAsDone();
            }

            taskList.add(task);
        }
        return taskList;
    }

    /**
     * Creates text file and directories to that text file if any of them do not exist.
     *
     * @param dir  Directory that file is contained in
     * @param file Name of text file
     */
    public static void createIfNotExist(Path dir, Path file) {
        if (Files.notExists(dir)) {
            try {
                Files.createDirectories(dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Files.notExists(file)) {
            try {
                Files.createFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns if storage save file exists.
     *
     * @return True if save file exists
     */
    public boolean hasSave() {
        return !(Files.notExists(filePath.getParent()) || Files.notExists(filePath));
    }

    /**
     * String representation of file path where save files are stored.
     *
     * @return String
     */
    @Override
    public String toString() {
        return filePath.toString();
    }
}
