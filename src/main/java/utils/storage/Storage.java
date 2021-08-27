package utils.storage;
import utils.exceptions.DukeException;
import utils.task.Deadline;
import utils.task.Event;
import utils.task.Task;
import utils.task.Todo;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files

public class Storage {
    private static final String delimiter = "\\|";
    private static final int delimiterLimit = 4;

    private static final String IDENTIFIER_TODO = "T";
    private static final String IDENTIFIER_DEADLINE = "D";
    private static final String IDENTIFIER_EVENT = "E";

    private static final String DONE = "1";
    private static final String NOT_DONE = "0";

    private static final String noPermissionsException = "User does not have permissions to create directories/file.";

    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }


    public ArrayList<Task> load() throws DukeException {
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

        // Parse the file and get the tasks list
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
     * Returns a task from a line of String.
     * @param line
     * @return Task object
     */
    private Task createTaskFromString(String line) {
        String[] split = line.split(delimiter, delimiterLimit);
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
