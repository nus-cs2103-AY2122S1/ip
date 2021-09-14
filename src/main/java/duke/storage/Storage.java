package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.exception.IncorrectIndexException;
import duke.exception.TimedTaskDateInputException;
import duke.formatter.Formatter;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * A class to initialise storage of taskLists and loading of taskLists from stored
 * .txt files
 */
public class Storage {

    /** The default file name **/
    public static final String DEFAULT_FILE_NAME = "duke.txt";

    /** The default file directory **/
    public static final String DEFAULT_FILE_DIRECTORY = "data";

    /** The default file path **/
    public static final String DEFAULT_FILE_PATH = "data/duke.txt";

    /** Constant representing a load issue message. */
    public static final String LOAD_ISSUE_MESSAGE = "DB file is corrupted.\n";

    private BufferedReader reader;

    private BufferedWriter writer;

    private Formatter formatter;

    private Path targetDirectory;

    private Storage() {
        try {
            this.targetDirectory = checkIfDirectoryExistsAndCreate();
            setBufferedReader(this.targetDirectory);
            this.formatter = new Formatter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Storage(String[] args) {
        try {
            this.targetDirectory = checkIfDirectoryExistsAndCreate(args);
            setBufferedReader(this.targetDirectory);
            this.formatter = new Formatter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A factory method to return an instance of a Storage object.
     * @return Storage which created the .txt file.
     */
    public static Storage createStorage() {
        return new Storage();
    }

    /**
     * A factory method to return an instance of a Storage object with an input filePath.
     * @return Storage which created the .txt file.
     */
    public static Storage createStorage(String filePath) {
        return new Storage(filePath.split("/"));
    }

    /**
     * Loads a taskList with the tasks found in the stored .txt file.
     * @param taskList A TaskList to be filled with the tasks from the .txt file.
     * @return A complete TaskList with the tasks extracted from the .txt file.
     * @throws TimedTaskDateInputException which may be thrown.
     */
    public TaskList load(TaskList taskList) throws TimedTaskDateInputException {
        try {
            String line = this.reader.readLine();
            if (isNullOrEmpty(line)) {
                printMessage("No previous record found!");
                return taskList;
            }
            while (line != null) {
                taskList.addTask(this.formatter.formatStorageStringToTask(line));
                line = this.reader.readLine();
            }
        } catch (IOException e) {
            printExceptionError(e, "The Tasks were loaded with errors.");
        }
        return taskList;
    }

    /**
     * Saves a taskList into the stored .txt file.
     * @param taskList A TaskList to be filled with the tasks from the .txt file.
     */
    public void save(TaskList taskList) {
        try {
            setBufferedWriter();
        } catch (IOException e) {
            printExceptionError(e, "Exception occurred while initialising writer.");
        }
        printMessage("Saving your list now!");
        saveTasksInTaskList(taskList);
        try {
            this.writer.close();
        } catch (IOException e) {
            printExceptionError(e, "Exception occurred while closing writer.");
        }
    }

    private void saveTasksInTaskList(TaskList taskList) {
        for (int i = 1; i <= taskList.size(); i++) {
            try {
                writeTaskToWriterDestination(taskList.get(i));
            } catch (IOException e) {
                printExceptionError(e, "Exception occurred while writing");
            } catch (IncorrectIndexException e) {
                printExceptionError(e, "Exception occurred while saving task due to incorrect index");
            }
        }
    }

    private void writeTaskToWriterDestination(Task task) throws IOException {
        this.writer.write(this.formatter.formatTaskToString(task));
        this.writer.newLine();
    }

    private Path checkIfDirectoryExistsAndCreate() throws IOException {
        Path targetDirectory =
                Paths.get(".", DEFAULT_FILE_DIRECTORY, DEFAULT_FILE_NAME)
                        .toAbsolutePath()
                        .normalize();
        if (!Files.exists(targetDirectory)) {
            Files.createDirectory(Paths.get(".", DEFAULT_FILE_DIRECTORY).toAbsolutePath().normalize());
            Files.createFile(targetDirectory);
        }
        return targetDirectory;
    }

    private Path checkIfDirectoryExistsAndCreate(String[] directoryPath) throws IOException {
        Path targetDirectory = Paths.get(".", directoryPath).toAbsolutePath().normalize();
        if (!java.nio.file.Files.exists(targetDirectory)) {
            traverseDirectoryAndCreateSubDirectory(directoryPath);
            Files.createFile(targetDirectory);
        }
        return targetDirectory;
    }

    private void traverseDirectoryAndCreateSubDirectory(String[] args) throws IOException {
        int i = 0;
        StringBuilder directory = new StringBuilder("./");
        while (i < args.length - 1) {
            addSubdirectoryToPath(directory, args[i]);
            i++;
        }
    }

    private void addSubdirectoryToPath(StringBuilder directory, String arg) throws IOException {
        directory.append(arg);
        Files.createDirectory(Paths.get(directory.toString()).toAbsolutePath().normalize());
        directory.append("/");
    }

    private void setBufferedReader(Path path) throws IOException {
        this.reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
    }

    private void setBufferedWriter() throws IOException {
        this.writer = Files.newBufferedWriter(this.targetDirectory, StandardCharsets.UTF_8);
    }

    private boolean isNullOrEmpty(String line) {
        return line == null || line.isEmpty();
    }

    private void printExceptionError(Exception e, String message) {
        System.out.println(message + "\n" + e.getMessage());
    }

    private void printMessage(String s) {
        System.out.println(s);
    }
}
