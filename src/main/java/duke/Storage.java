package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

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
        createIfNotExist(this.filePath.getParent(), this.filePath);
        assert(hasSave());
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
        assert(hasSave());
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
     * Create text file and directories to that text file if any of them do not exist.
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
}
