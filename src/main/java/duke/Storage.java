package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * This class represents a Storage that is used to save and load Tasks from a file.
 */
public class Storage {
    private static final String FILEPATH = "./data/duke.txt";

    /**
     * Constructs a Storage that saves and loads tasks from the file specified by FILEPATH/
     */
    public Storage() {
    }

    private static File getStoreFile() throws IOException {
        File store = new File(FILEPATH);
        String dirPath = store.getParent();
        File directory = new File(dirPath);
        // creates parent directories if they do not exist
        directory.mkdirs();
        // creates file if it does not exist
        store.createNewFile();
        assert store.isFile() : "Storage file could not be found nor created.";
        return store;
    }

    /**
     * Loads tasks from file specified by FILEPATH into an ArrayList of Tasks.
     *
     * @return ArrayList of Tasks loaded from the FILEPATH.
     * @throws DukeException If tasks cannot be loaded from storage file or file is not found.
     */
    public static ArrayList<Task> load() throws DukeException {
        try {
            File store = getStoreFile();
            return getTasksFromStore(store);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Saves tasks in the file specified by FILEPATH.
     *
     * @param tasks TaskList containing Tasks that should be saved.
     */
    public static void saveTasks(TaskList tasks) {
        try {
            File store = getStoreFile();
            writeTasksToStore(store, tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static ArrayList<Task> getTasksFromStore(File store) throws DukeException, IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(store));
        String fileLine = reader.readLine();
        int lineNo = 1;
        while (fileLine != null) {
            Task task = parseTask(fileLine, lineNo);
            tasks.add(task);
            lineNo++;
            fileLine = reader.readLine();
        }
        reader.close();
        return tasks;
    }

    private static void writeTasksToStore(File store, TaskList tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(store));
        ArrayList<String> taskStrings = tasks.getTaskStrings();
        for (String taskString : taskStrings) {
            writer.write(taskString);
            writer.newLine();
        }
        writer.close();
    }

    private static Task parseTask(String fileLine, int lineNo) throws DukeException {
        assert fileLine != null : "Line " + lineNo + " of storage file is null";
        assert !fileLine.isBlank() : "Line " + lineNo + " of storage file is blank";

        try {
            String[] parts = fileLine.split(" \\| ");
            String taskType = parts[0];
            int isDoneInt = Integer.parseInt(parts[1]);
            boolean isDone = (isDoneInt == 1);
            String description = parts[2];
            switch(taskType) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                String date = parts[3];
                return new Deadline(description, date, isDone);
            case "E":
                date = parts[3];
                return new Event(description, date, isDone);
            default:
                throw new DukeException("Could not parse task type on file line " + lineNo);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Task details are missing on file line " + lineNo);
        }
    }
}
