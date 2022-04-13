package duke.task;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.DukeException;

/**
 * Represents the storage of tasks to the hard disk.
 */
public class Storage {
    /**
     * The file path where the data file is.
     */
    private final java.nio.file.Path filePath;

    /**
     * Constructs a storage at the given path.
     *
     * @param path The file path where the data file is.
     * @throws DukeException
     */
    public Storage(String path) throws DukeException {
        this.filePath = java.nio.file.Paths.get(path);
        initStorage();
    }

    /**
     * Initialises storage by creating a data file if it does not exist.
     *
     * @throws DukeException
     */
    private void initStorage() throws DukeException {
        try {
            Files.createDirectories(filePath.getParent());
            if (!java.nio.file.Files.exists(filePath)) {
                java.nio.file.Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new DukeException("Could not initialise database. " + e.getMessage());
        }
    }

    /**
     * Writes over data file to save todo list contents to the hard disk.
     *
     * @param dataStrings
     * @throws DukeException
     */
    public void save(List<String> dataStrings) throws DukeException {
        try {
            // task saved as e.g. E|0|meeting|2pm
            Files.write(filePath, dataStrings);
        } catch (IOException e) {
            throw new DukeException("Could not save list to database. " + e.getMessage());
        }
    }

    /**
     * Loads data file contents into a list of tasks.
     *
     * @return A list of Tasks stored in data.
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            ArrayList<Task> todoList = new ArrayList<>();
            List<String> dataList = Files.lines(filePath).collect(Collectors.toList());
            for (String line : dataList) {
                String[] details = line.split("\\|", 4);
                assert details.length > 2;
                String tag = details[0];
                Boolean done = details[1].equals("1");
                Task task;
                if (tag.equals("T")) {
                    task = new ToDo(details[2]);
                } else if (tag.equals("D")) {
                    assert details.length == 4;
                    task = new Deadline(details[2], details[3]);
                } else if (tag.equals("E")) {
                    assert details.length == 4;
                    task = new Event(details[2], details[3]);
                } else {
                    throw new DukeException("Unknown tag '" + tag + "'.");
                }
                if (done) {
                    task.markAsDone();
                }
                todoList.add(task);
            }
            return todoList;
        } catch (IOException | DukeException e) {
            throw new DukeException("Could not load database into todo list.").concat(e);
        }
    }

}
