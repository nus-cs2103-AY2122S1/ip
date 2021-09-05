package duke.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeFileSystemException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A class that handles anything related to the storing and loading of information from memory for Duke.
 */
public class Storage {
    private final Path filePath;
    private final DukeParser parser;

    /**
     * Constructs a new instance of Storage that loads and stores data into the file path that is specified.
     *
     * @param filePath a string literal of the file path where the information is accessed from.
     * @throws IOException if the file path is not found and cannot be created automatically by Storage.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = Paths.get(System.getProperty("user.dir"), filePath);
        createDirectoriesAndFiles();
        this.parser = new DukeParser();
    }

    private void createDirectoriesAndFiles() throws IOException {
        // create storage directories and files if it does not exist
        if (Files.notExists(filePath)) {
            File f = new File(filePath.toString());
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
    }

    /**
     * Returns a list of tasks based on the specified data file path.
     *
     * @return a list of tasks from storage.
     * @throws DukeFileSystemException if the file that was read is of an invalid format.
     */
    public List<Task> load() throws DukeFileSystemException {
        File dataFile = new File(this.filePath.toString());
        List<Task> taskList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNext()) {
                taskList.add(parser.parseTaskFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new DukeFileSystemException("Unable to load previous tasks. "
                    + "A new list will be used for this session. Ensure that " + filePath + " exists.");
        }
        return taskList;
    }

    /**
     * Stores the tasks in duke format to specified file path in the constructor to be cached.
     *
     * @param taskList a list of tasks to be converted and stored.
     * @throws IOException if the file path specified is invalid.
     */
    public void writeTasksToFile(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath.toString());
        fileWriter.write(taskList.toDukeStoreFormat());
        fileWriter.close();
    }
}
