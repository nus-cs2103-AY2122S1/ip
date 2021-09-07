package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * A class that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private final Path filePath;
    private final Path directoryPath;

    /**
     * Constructs a Storage instance that deals with loading tasks from the file and saving tasks in the file.
     *
     * @param filePath The relative path of the file that stores all the tasks.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        this.directoryPath = Paths.get(new File(filePath).getParent());
    }

    /**
     * Returns a list of string containing the tasks.
     *
     * @return The list of string containing the tasks.
     * @throws DukeException The exception that handles the possible exceptions thrown when the program runs.
     */
    public List<String> load() throws DukeException {
        List<String> taskList = new ArrayList<>();

        try {
            if (!Files.exists(directoryPath)) {
                Files.createDirectory(directoryPath);
            }
            assert Files.exists(directoryPath) : "Directory should be created";

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            assert Files.exists(filePath) : "File should be created";

            taskList = Files.readAllLines(filePath);
        } catch (IOException | UnsupportedOperationException | SecurityException e) {
            new Ui().showError(e.getMessage());
        }

        if (taskList.isEmpty()) {
            throw new DukeException("Empty list");
        }

        return taskList;
    }

    /**
     * Writes tasks list to the file.
     *
     * @param taskLists The list that stores all the tasks to be added/deleted.
     */
    public void save(TaskList taskLists) {
        try {
            FileWriter dataFileWriter = new FileWriter(filePath.toString());
            for (Task task : taskLists.getTasks()) {
                dataFileWriter.write(task.toDataString() + System.lineSeparator());
            }
            dataFileWriter.close();
        } catch (IOException e) {
            new Ui().showError(e.getMessage());
        }
    }
}
