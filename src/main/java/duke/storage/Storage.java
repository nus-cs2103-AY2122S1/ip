package duke.storage;

import duke.exceptions.DukeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TodoTask;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class Storage {
    private static final String DELIMITER = " | ";
    private static final String DELIMITER_SPLIT = " \\| ";

    private static final String IDENTIFIER_TODO = "T";
    private static final String IDENTIFIER_DEADLINE = "D";
    private static final String IDENTIFIER_EVENT = "E";

    private static final String IDENTIFIER_DONE = "1";
    private static final String IDENTIFIER_NOT_DONE = "0";

    private static final String ERROR_FAILED_PATH_CREATION = "Error: Failed to find/create necessary directories and file. Check your filepath and permissions.";
    private static final String ERROR_FAILED_LOAD_CORRUPTED_FILE = "Error: Failed to load tasks from file. Corrupted file or filepath.\nWarning: File will be rewritten on next duke.command.";
    private static final String ERROR_FAILED_WRITE_CORRUPTED_FILE = "Error: Failed to write tasks to file. Corrupted file or filepath.";

    private final String FILEPATH;

    public Storage(String filepath) {
        this.FILEPATH = filepath;
    }

    /**
     * Loads all the duke.task in the indicated filepath with the correct format.
     *
     * @return the ArrayList of Task objects.
     * @throws DukeException throws duke.exceptions for when file and folders cannot be created or filepath is corrupted.
     */
    public ArrayList<Task> load() throws DukeException {

        // Parse the file and get the tasks list
        File file = new File(FILEPATH);
        ArrayList<Task> tasks = new ArrayList<>();
        try {

            // Start scanning the file, throws FileNotFoundException if file not found.
            Scanner myReader = new Scanner(file);

            // File exists, scan the text file and create tasks.
            while (myReader.hasNextLine()) {
                // Grab the next line of String in the text file.
                String line = myReader.nextLine();

                // While creating duke.task from String, possibly corrupted file.
                Task task = null;
                try {
                    task = createTaskFromString(line);
                } catch (DukeException e) {
                    throw new DukeException(ERROR_FAILED_LOAD_CORRUPTED_FILE);
                }

                // asserts task to be added is not null
                assert(task != null);

                // Add successfully created duke.task to the list.
                tasks.add(task);
            }

            // Close the reader.
            myReader.close();

        } catch (FileNotFoundException e) {

            // Failed to load file
            // Check if path exists, else try to create the path.
            // If failed to create path, return false.
            boolean result = checkPathExistsElseCreate();

            // If failed to create the necessary directories and file, throw DukeException.
            if (!result) {
                throw new DukeException(ERROR_FAILED_PATH_CREATION);
            }
        }

        return tasks;
    }

    /**
     * Writes the ArrayList of tasks into the file.
     *
     * @param tasks ArrayList of Task objects.
     * @throws DukeException throws duke.exceptions for when file and folders cannot be created or filepath is corrupted.
     */
    public void writeTasksToFile(TaskList tasks) throws DukeException {

        // Check if path exists, else try to create the path.
        // If failed to create path, return false.
        boolean result = checkPathExistsElseCreate();

        // If failed to create the necessary directories and file, throw DukeException.
        if (!result) {
            throw new DukeException(ERROR_FAILED_PATH_CREATION);
        }

        try {
            FileWriter writer = new FileWriter(FILEPATH);
            String fileString = tasks.getFileString(DELIMITER, IDENTIFIER_DONE, IDENTIFIER_NOT_DONE);

            // asserts fileString is not empty or null
            assert(fileString != null);
            assert(!fileString.equals(""));

            writer.write(fileString);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new DukeException(ERROR_FAILED_WRITE_CORRUPTED_FILE);
        }

    }

    /**
     * Checks if path exists, else try to create the path.
     * If failed to create path, return false.
     * Returns true if successful, else false.
     *
     * @return true if all files and folders required for filepath are created, else false.
     */
    private boolean checkPathExistsElseCreate() {
        File file = new File(FILEPATH);

        // handle the folder does not exist
        File parent = file.getParentFile();
        if (parent == null) {
            return false;
        }

        if (!parent.exists()) {
            try {
                boolean result = parent.mkdirs();
                if (!result) {
                    return false;
                }
            } catch (SecurityException e) {
                return false;
            }
        }

        // handle the file does not exist
        if (!file.exists()) {
            try {
                boolean result = file.createNewFile();
                if (!result) {
                    return false;
                }
            } catch (IOException | SecurityException e) {
                return false;
            }
        }

        // Reached the end of function.
        // Successfully created the directories and file required or
        // directory and file exists from the beginning.
        return true;
    }

    /**
     * Returns a duke.task from a line of String.
     *
     * @param taskLine the String for a duke.task in the correct format.
     * @return Task object.
     */
    private Task createTaskFromString(String taskLine) throws DukeException {
        // Split the line String to get the first duke.task type in the String.
        String[] taskSplit = taskLine.split(DELIMITER_SPLIT, 2);

        // Throws DukeException if duke.task String is corrupted.
        if (taskSplit.length < 2) {
            throw new DukeException();
        }

        String taskType = taskSplit[0];
        Task task = null;

        switch (taskType) {
        case IDENTIFIER_TODO:
            task = TodoTask.getTaskFromStorageString(taskSplit[1], DELIMITER_SPLIT, IDENTIFIER_DONE);
            break;
        case IDENTIFIER_DEADLINE:
            task = DeadlineTask.getTaskFromStorageString(taskSplit[1], DELIMITER_SPLIT, IDENTIFIER_DONE);
            break;
        case IDENTIFIER_EVENT:
            task = EventTask.getTaskFromStorageString(taskSplit[1], DELIMITER_SPLIT, IDENTIFIER_DONE);
            break;
        }

        if (task == null) {
            throw new DukeException();
        }

        return task;
    }

}
