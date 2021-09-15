package duke.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Storage class that manages the saving and loading of the task list.
 */
public class TaskStorage {
    public static final String TASK_DONE = "o";
    public static final String TASK_NOT_DONE = "x";
    private static final File SAVE_FILE = new File("save.csv");

    /**
     * Loads the taskList from the saveFile.
     *
     * @return The TaskList generated from loading a task from each line in the file.
     */
    public static ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc;

        try {
            sc = new Scanner(SAVE_FILE);
        } catch (FileNotFoundException e) {
            // no save data, use a fresh list
            return new ArrayList<>();
        }

        while (sc.hasNextLine()) {
            String[] datas = sc.nextLine().split(",");
            switch (datas[0]) {
            case "t":
                tasks.add(ToDo.load(datas));
                break;
            case "d":
                tasks.add(Deadline.load(datas));
                break;
            case "e":
                tasks.add(Event.load(datas));
                break;
            default:
                // unknown task type
                break;
            }
        }

        return tasks;
    }

    /**
     * Saves the taskList into the specified file.
     *
     * @param tasks The tasks to be saved.
     * @throws DukeException Any exception caught that has to do with the I/O.
     */
    public static void save(ArrayList<Task> tasks) throws DukeException {
        String taskListContent = convertToSaveString(tasks);

        try {
            // create the file if it does not exist
            SAVE_FILE.createNewFile();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

        try (FileWriter fw = new FileWriter(SAVE_FILE)) {
            fw.write(taskListContent);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Returns a string representing the tasks compliant to the save format.
     *
     * @param tasks The tasks to be saved.
     * @return A string representing the tasks compliant to the save format.
     */
    private static String convertToSaveString(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();

        if (tasks.size() == 0) {
            return "";
        }

        for (Task task : tasks) {
            sb.append(task.getSaveString());
            sb.append('\n');
        }

        // remove the last newline character
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}
