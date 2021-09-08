package duke;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidDirectoryException;
import duke.exceptions.InvalidStorageFilePathException;
import duke.tasks.*;

/**
 * Represents the class used to store the tasklist data.
 */
public class Storage {

    /** Contains the filepath for the project's directory */
    private static final String DESKTOP_DIRECTORY = System.getProperty("user.home") + "/Desktop";

    /** User specified filePath destination */
    private final String givenFilePath;

    /** Stores relative path of the file */
    private Path p;

    /**
     * @throws InvalidStorageFilePathException if filepath does not end with .txt, or invalid file path is given.
     */
    Storage(String givenFilePath) throws InvalidStorageFilePathException {
        this.givenFilePath = givenFilePath;
        File currDir = new File(DESKTOP_DIRECTORY);
        File finalPath = new File(currDir, givenFilePath);
        p = Paths.get(finalPath.getPath());
        if (!isValidPath(p)) {
            throw new InvalidStorageFilePathException("Storage filepath should end with '.txt'");
        }
        if (!isValidDirectory(givenFilePath)) {
            throw new InvalidDirectoryException("1 or more directories in the specified file path does not exist");
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
        String currentPath = DESKTOP_DIRECTORY;
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
            return lt;
        } catch (IOException e) {
            String createdPath = DESKTOP_DIRECTORY + "/" + givenFilePath;
            File tempfile = new File(createdPath);
            try {
                tempfile.createNewFile();
                System.out.println("Succesfully created 'tasklist.txt' within " + tempfile.getAbsolutePath());
                return lt;
            } catch (IOException x) {
                throw new DukeException("UNEXPECTED ERROR: Unable to create file...");
            }
        }
    }

    /**
     * Saves the list of tasks into the specified .txt file when function is invoked.
     */
    public void save(TaskList listOfTasks) {
        String outputText = Storage.printListForSave(listOfTasks);
        try {
            // clears the file
            Files.write(p, "".getBytes());
            Files.write(p, outputText.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("An error occurred while trying to save...");
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
                if (isTaskComplete(t.getStatusIcon())) {
                    outputText += "X | " + t.getDescription() + "\n";
                } else {
                    outputText += "0 | " + t.getDescription() + "\n";
                }
            } else if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                outputText += "D | ";
                if (isTaskComplete(d.getStatusIcon())) {
                    outputText += "X | " + d.getDescription() + " | " + d.getBy() + "\n";
                } else {
                    outputText += "0 | " + d.getDescription() + " | " + d.getBy() + "\n";
                }
            } else if (t instanceof Event) {
                Event e = (Event) t;
                outputText += "E | ";
                if (isTaskComplete(e.getStatusIcon())) {
                    outputText += "X | " + e.getDescription() + " | " + e.getAt() + "\n";
                } else {
                    outputText += "0 | " + e.getDescription() + " | " + e.getAt() + "\n";
                }
            } else if (t instanceof DoWithinPeriod) {
                DoWithinPeriod dw = (DoWithinPeriod) t;
                outputText += "DW | ";
                if (isTaskComplete(dw.getStatusIcon())) {
                    outputText += "X | " + dw.getDescription() + " | " + dw.getStartDate()
                            + " | " + dw.getEndDate() + "\n";
                } else {
                    outputText += "0 | " + dw.getDescription() + " | " + dw.getStartDate()
                            + " | " + dw.getEndDate() + "\n";
                }
            }
        }
        return outputText;
    }

    /** Helper method to check if sentence includes a date from the parsed task in the storage file
     *
     * @param words a list in the format [task identifier, 'X'/'0', task description, date]
     * @return true if there are 4 elements, false otherwise
     */
    public static boolean hasDateInSentence(List<String> words) {
        return words.size() == 4;
    }

    /** Helper method to check if sentence includes a start and end date from the parsed doWithinPeriod task
     *  in the storage file
     *
     * @param words a list in the format [task identifier, 'X'/'0', task description, startDate, endDate]
     * @return true if there are 5 elements, false otherwise
     */
    public static boolean hasStartAndEndDate(List<String> words) {
        return words.size() == 5;
    }

    /**
     * Helper method to check if task is completed after parsing the text from the storage file
     *
     * @param firstChar refers to the first character in the sentence (either "X" or "0")
     * @return true if "X", false otherwise
     */
    public static boolean isTaskComplete(String firstChar) {
        return firstChar.equals("X");
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
            if (isTaskComplete(words.get(1))) {
                t = t.markAsDone();
            }
            output = t;
            break;
        case "D":
            Deadline d = new Deadline(words.get(2), words.get(3));
            if (hasDateInSentence(words)) {
                if (isTaskComplete(words.get(1))) {
                    d = d.markAsDone();
                }
                output = d;
            }
            break;
        case "E":
            Event e = new Event(words.get(2), words.get(3));
            if (hasDateInSentence(words)) {
                if (isTaskComplete(words.get(1))) {
                    e = e.markAsDone();
                }
                output = e;
            }
            break;
        case "DW":
            DoWithinPeriod dw = new DoWithinPeriod(words.get(2), words.get(3), words.get(4));
            if (hasStartAndEndDate(words)) {
                if (isTaskComplete(words.get(1))) {
                    dw = dw.markAsDone();
                }
                output = dw;
            }
        default:
            break;
        }
        return output;
    }
}
