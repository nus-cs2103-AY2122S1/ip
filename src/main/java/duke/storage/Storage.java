package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.exception.DukeException;
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
     * @throws DukeException which may be thrown.
     */
    public TaskList load(TaskList taskList) throws DukeException {
        try {
            String line = this.reader.readLine();
            if (isNullOrEmpty(line)) {
                printPreviousRecordLine();
                return taskList;
            }
            while (line != null) {
                taskList.addTask(this.formatter.formatStringToTask(line));
                line = this.reader.readLine();
            }
        } catch (IOException e) {
            printLoadFileError(e);
        }
        return taskList;
    }

    /**
     * Saves a taskList into the stored .txt file.
     * @param taskList A TaskList to be filled with the tasks from the .txt file.
     * @throws DukeException which may be thrown.
     */
    public void save(TaskList taskList) throws DukeException {
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

    private void saveTasksInTaskList(TaskList taskList) throws DukeException {
        for (int i = 1; i <= taskList.size(); i++) {
            try {
                writeTaskToWriterDestination(taskList.get(i));
            } catch (IOException e) {
                printExceptionError(e, "Exception occurred while writing");
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
            try {
                Files.createDirectory(Paths.get(".", DEFAULT_FILE_DIRECTORY).toAbsolutePath().normalize());
            } catch (java.nio.file.FileAlreadyExistsException e) {
                printDirectoryExistsMessage();
            }
            Files.createFile(targetDirectory);
        }
        return targetDirectory;
    }

    private Path checkIfDirectoryExistsAndCreate(String[] args) throws IOException {
        Path targetDirectory = Paths.get(".", args).toAbsolutePath().normalize();
        if (!java.nio.file.Files.exists(targetDirectory)) {
            traverseDownDirectoryAndCreateSubDirectory(args);
            Files.createFile(targetDirectory);
        }
        return targetDirectory;
    }

    private void traverseDownDirectoryAndCreateSubDirectory(String[] args) throws IOException {
        int i = 0;
        StringBuilder directory = new StringBuilder("./");
        while (i < args.length - 1) {
            try {
                addSubdirectoryToPath(directory, args[i]);
            } catch (java.nio.file.FileAlreadyExistsException e) {
                printDirectoryExistsMessage();
            }
            i++;
        }
    }

    private void addSubdirectoryToPath(StringBuilder directory, String arg) throws IOException {
        directory.append(arg);
        Files.createDirectory(Paths.get(directory.toString()).toAbsolutePath().normalize());
        directory.append("/");
    }

    private void printDirectoryExistsMessage() {
        printMessage("Directory exists but file does not. Creating file...");
    }

    private void setBufferedReader(Path path) throws IOException {
        this.reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
    }

    private void setBufferedWriter() throws IOException {
        this.writer = Files.newBufferedWriter(this.targetDirectory, StandardCharsets.UTF_8);
    }

    private void printLoadFileError(IOException e) {
        printExceptionError(e, "Unable to load file.");
    }

    private void printPreviousRecordLine() {
        printMessage("No previous record found.");
    }

    private boolean isNullOrEmpty(String line) {
        return line == null || line.isEmpty();
    }

    private void printExceptionError(IOException e, String s) {
        printMessage(s);
        e.printStackTrace();
    }

    private void printMessage(String s) {
        System.out.println(s);
    }
}
