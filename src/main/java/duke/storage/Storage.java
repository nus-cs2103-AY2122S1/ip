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

    private BufferedReader reader;

    private BufferedWriter writer;

    private Formatter formatter;

    private Path targetDirectory;

    private Storage() {
        try {
            this.targetDirectory =
                    Paths.get(".", DEFAULT_FILE_DIRECTORY, DEFAULT_FILE_NAME)
                            .toAbsolutePath()
                            .normalize();
            if (!java.nio.file.Files.exists(this.targetDirectory)) {
                try {
                    Files.createDirectory(Paths.get(".", DEFAULT_FILE_DIRECTORY).toAbsolutePath().normalize());
                } catch (java.nio.file.FileAlreadyExistsException e) {
                    System.out.println("Directory exists but file does not. Creating file...");
                }
                Files.createFile(this.targetDirectory);
            }
            this.reader = Files.newBufferedReader(this.targetDirectory, StandardCharsets.UTF_8);
            this.formatter = new Formatter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Storage(String[] args) {
        try {
            this.targetDirectory =
                    Paths.get(".", args).toAbsolutePath().normalize();
            if (!java.nio.file.Files.exists(this.targetDirectory)) {
                int i = 0;
                StringBuilder directory = new StringBuilder("./");
                while (i < args.length - 1) {
                    try {
                        directory.append(args[i]);
                        Files.createDirectory(Paths.get(directory.toString()).toAbsolutePath().normalize());
                        directory.append("/");
                    } catch (java.nio.file.FileAlreadyExistsException e) {
                        System.out.println("Directory exists but file does not. Creating file...");
                    }
                    i++;
                }
                Files.createFile(this.targetDirectory);
            }
            this.reader = Files.newBufferedReader(this.targetDirectory, StandardCharsets.UTF_8);
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
            if (line == null || line.isEmpty()) {
                System.out.println("No previous record found.");
                return taskList;
            }
            while (line != null) {
                taskList.addTask(this.formatter.formatStringToTask(line));
                line = this.reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Unable to load file.");
            e.printStackTrace();
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
            this.writer = Files.newBufferedWriter(this.targetDirectory, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Exception occurred while initialising writer.");
            e.printStackTrace();
        }
        System.out.println("Saving your list now!");
        for (int i = 1; i <= taskList.size(); i++) {
            try {
                this.writer.write(this.formatter.formatTaskToString(taskList.get(i)));
                this.writer.newLine();
            } catch (IOException e) {
                System.out.println("Exception occurred while writing");
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Exception occurred while closing writer.");
            e.printStackTrace();
        }
    }
}
