package utils.storage;
import utils.exceptions.DukeException;
import utils.task.Deadline;
import utils.task.Event;
import utils.task.Task;
import utils.task.Todo;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class Storage {
    private static final String delimiter = "|";
    private static final int delimiterLimit = 4;

    private static final String IDENTIFIER_TODO = "T";
    private static final String IDENTIFIER_DEADLINE = "D";
    private static final String IDENTIFIER_EVENT = "E";

    private static final String DONE = "1";
    private static final String NOT_DONE = "0";

    private static final String noPermissionsException = "User does not have permissions to create directories/file.";
    private static final String failedSaveException = "Error: Failed to write tasks to file.";

    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads all the task in the indicated filepath with the correct format.
     * @return the ArrayList of Task objects.
     * @throws DukeException throws exceptions for when file and folders cannot be created or filepath is corrupted.
     */
    public ArrayList<Task> load() throws DukeException {
        // Creates the file and folders for filepath, else throw DukeException
        createsFileAndFoldersForPath();

        // Parse the file and get the tasks list
        File file = new File(filepath);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                Task task = createTaskFromString(line);
                tasks.add(task);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        }

        return tasks;
    }

    /**
     * Writes the ArrayList of tasks into the file.
     * @param tasks ArrayList of Task objects.
     * @throws DukeException throws exceptions for when file and folders cannot be created or filepath is corrupted.
     */
    public void writeTasksToFile(ArrayList<Task> tasks) throws DukeException {
        // Creates the file and folders for filepath, else throw DukeException
        createsFileAndFoldersForPath();
        try {
            FileWriter writer = new FileWriter(filepath);
            StringBuilder fileString = new StringBuilder();
            for (Task task : tasks) {
                String appendedString = task.getTaskFileString(delimiter, DONE, NOT_DONE) + "\n";
                fileString.append(appendedString);
            }
            writer.write(fileString.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new DukeException(failedSaveException);
        }
    }

    /**
     * Creates the files and folders required.
     * @throws DukeException throws exceptions for when file and folders cannot be created or filepath is corrupted.
     */
    private void createsFileAndFoldersForPath() throws DukeException {
        File file = new File(filepath);

        // handle the folder does not exist
        File parent = file.getParentFile();
        if (!parent.exists()) {
            boolean result = parent.mkdirs();
            if (!result) {
                throw new DukeException(noPermissionsException);
            }
        }

        // handle the file does not exist
        if (!file.exists()) {
            try {
                boolean result = file.createNewFile();
                if (!result) {
                    throw new DukeException(noPermissionsException);
                }
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        }
    }

    /**
     * Returns a task from a line of String.
     * @param line the String for a task in the correct format
     * @return Task object
     */
    private Task createTaskFromString(String line) {
        String[] split = line.split("\\" + delimiter, delimiterLimit);
        String taskType = split[0];
        switch (taskType) {
            case (IDENTIFIER_TODO):
                Task todo = new Todo(split[2]);
                if (split[1].equals(DONE)) {
                    todo.markAsDone();
                }
                return todo;
            case (IDENTIFIER_DEADLINE):
                Deadline deadline = new Deadline(split[2], split[3]);
                if (split[1].equals(DONE)) {
                    deadline.markAsDone();
                }
                return deadline;
            case (IDENTIFIER_EVENT):
                Event event = new Event(split[2], split[3]);
                if (split[1].equals(DONE)) {
                    event.markAsDone();
                }
                return event;
        }
        return null;
    }

}
