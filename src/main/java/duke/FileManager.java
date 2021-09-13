package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The FileManager class that deals with loading tasks from a file and saving tasks to a file.
 */
public class FileManager {
    private static final String FILENAME = "tasks.txt";
    private static final File DATA_FILE = new File(FILENAME);

    /**
     * Constructor for a FileManager object.
     */
    public FileManager() {
        try {
            DATA_FILE.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * A method that returns a task given the task details.
     *
     * @param taskArgs Array of task details.
     * @return The task corresponding to the task details.
     * @throws DukeException If unable to create a todo, deadline or event.
     */
    public Task getTask(String[] taskArgs) throws DukeException {
        Task newTask = null;
        String taskIcon = taskArgs[0];
        boolean isDone = taskArgs[1].equals("1");
        switch (taskIcon) {
        case "T":
            newTask = new Todo(taskArgs[2]);
            break;
        case "D":
            newTask = new Deadline(taskArgs[2], taskArgs[3]);
            break;
        case "E":
            newTask = new Event(taskArgs[2], taskArgs[3]);
            break;
        default:
            assert false;
        }
        if (isDone) {
            newTask.markAsDone();
        }
        return newTask;
    }

    /**
     * A method that reads a save file and returns the list of tasks.
     *
     * @return The list of tasks.
     * @throws DukeException If a save file is not found.
     */
    public TaskList getListFromFile() throws DukeException {
        ArrayList<Task> newList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(DATA_FILE);
            while (sc.hasNextLine()) {
                String[] curr = sc.nextLine().split("~S~");
                Task newTask = getTask(curr);
                newList.add(newTask);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Save file is not found");
        }
        return new TaskList(newList);
    }

    /**
     * A method that saves the list of tasks to a file.
     *
     * @param list The list of tasks to be saved.
     */
    public void writeToFile(TaskList list) throws DukeException {
        try {
            FileWriter writer = new FileWriter(FILENAME);
            writer.write(list.convertToData());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred while writing to the save file :(");
        }
    }
}
