package duke;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidDirectoryException;
import duke.exceptions.InvalidStorageFilePathException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents the class used to store the tasklist data.
 */
public class Storage {

    /** User specified filePath destination */
    private final String givenFilePath;

    /** Contains the filepath for the project's directory */
    private static final String PROJECT_DIRECTORY = System.getProperty("user.dir") + "/src/main/java/duke";
    private Path p;

    /**
     * @throws InvalidStorageFilePathException if filepath does not end with .txt, or invalid file path is given.
     */
    Storage(String givenFilePath) throws InvalidStorageFilePathException {
        this.givenFilePath = givenFilePath;
        File currDir = new File(PROJECT_DIRECTORY);
        File finalPath = new File(currDir, givenFilePath);
        p = Paths.get(finalPath.getPath());
        if (!isValidPath(p)) {
            throw new InvalidStorageFilePathException("   Storage filepath should end with '.txt'");
        }
        if (!isValidDirectory(givenFilePath)) {
            throw new InvalidDirectoryException("   1 or more directories in the specified file path does not exist");
        }
    }

    /** Helper function to determine if the specified path ends with .txt */
    public static boolean isValidPath(Path filepath) {
        return filepath.toString().endsWith(".txt");
    }

    /**
     * Helper function to determine if the specified directory exists.
     *
     * @return true if directory exists. false if any parent/child directory is missing in the file path.
     */
    public static boolean isValidDirectory(String filePath) {
        String[] folderNames = filePath.split("/");
        String currentPath = PROJECT_DIRECTORY;
        for (String folder: folderNames) {
            File file = new File(currentPath);
            if (!file.exists()) {
                return false;
            } else {
                currentPath += "/" + folder;
            }
        }
        return true;
    }

    /**
     * Reads the file upon app startup and attempts to load the data into a list for TaskList.java to parse.
     * Creates a new .txt file if specified .txt file is not found within the directory.
     *
     * @return list of all tasks saved from the previous session.
     * @throws DukeException if error occurs while attempting to create a new file.
     */
    public List<Task> load() throws DukeException {
        List<Task> lt = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(p);
            for (String s: lines) {
                lt.add(convertToTask(s));
            }
            System.out.println("   Autosave feature detected. Please type 'list' to view previously saved tasks...");
            return lt;
        } catch (IOException e) {
            System.out.println("   FILE NOT FOUND ERROR: Creating empty tasklist...");
            String createdPath = PROJECT_DIRECTORY + "/" + givenFilePath;
            File tempfile = new File(createdPath);
            System.out.println(tempfile.getAbsolutePath());
            try {
                tempfile.createNewFile();
                System.out.println("   Succesfully created 'tasklist.txt' within ./src/main/java/" + givenFilePath);
                return lt;
            } catch (IOException x) {
                System.out.println(x.getMessage());
                throw new DukeException("   UNEXPECTED ERROR: Unable to create file...");
            }
        }
    }

    /**
     * Saves the list of tasks into the specified .txt file when function is invoked.
     */
    public void save(TaskList listOfTasks) {
        String outputText = Storage.printListForSave(listOfTasks);
        try {
            Files.write(p, "".getBytes()); // clears the file
            Files.write(p, outputText.getBytes(), StandardOpenOption.APPEND);
            System.out.println("   Saved tasks to file...");
        } catch (IOException e) {
            System.out.println("   An error occurred while trying to save...");
        }
    }

    /**
     * Converts the saved list of tasks into a format convenient for readability.
     */
    public static String printListForSave(TaskList lst) {
        String outputText = "";

        for (Task t: lst.getListOfTasks()) {
            if (t instanceof Todo) {
                outputText += "T | ";
                if (t.getStatusIcon().equals("X")) {
                    outputText += "X | " + t.getDescription() + "\n";
                } else {
                    outputText += "0 | " + t.getDescription() + "\n";
                }
            } else if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                outputText += "D | ";
                if (d.getStatusIcon().equals("X")) {
                    outputText += "X | " + d.getDescription() + " | " + d.getBy() + "\n";
                } else {
                    outputText += "0 | " + d.getDescription() + " | " + d.getBy() + "\n";
                }
            } else if (t instanceof Event) {
                Event e = (Event) t;
                outputText += "E | ";
                if (e.getStatusIcon().equals("X")) {
                    outputText += "X | " + e.getDescription() + " | " + e.getAt() + "\n";
                } else {
                    outputText += "0 | " + e.getDescription() + " | " + e.getAt() + "\n";
                }
            }
        }
        return outputText;
    }

    /**
     * Upon loading of the storage file, functions parses the inputs found in the file into their respective tasks.
     * Invalid inputs for deadline/events will be filtered and converted accordingly.
     */
    public Task convertToTask(String input) {
        List<String> words = List.of(input.split(" \\| "));
        Task output = null;

        switch (words.get(0)) {
        case "T":
            Todo t = new Todo(words.get(2));
            if (words.get(1).equals("X")) {
                t = t.markAsDone();
            }
            output = t;
            break;
        case "D":
            Deadline d = new Deadline(words.get(2), words.get(3));

            /** add only if date is specified   */
            if (words.size() == 4) {
                if (words.get(1).equals("X")) {
                    d = d.markAsDone();
                }
                output = d;
            }
            break;
        case "E":
            Event e = new Event(words.get(2), words.get(3));

            /** add only if date is specified   */
            if (words.size() == 4) {
                if (words.get(1).equals("X")) {
                    e = e.markAsDone();
                }
                output = e;
            }
            break;
        default:
            break;
        }
        return output;
    }
}
