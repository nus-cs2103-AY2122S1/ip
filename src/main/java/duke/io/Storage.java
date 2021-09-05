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
public class Storage {
    private final File saveFile;

    public Storage(String filepath) {
        this.saveFile = new File(filepath);
    }

    /**
     * Loads the taskList from the saveFile.
     *
     * @return False if the file does not exist, i.e. the user is a new user. True otherwise.
     */
    public ArrayList<Task> load() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner sc = new Scanner(saveFile);
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
                    break;
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Saves the taskList into save.csv.
     *
     * @param tasks The tasks to be saved.
     * @throws DukeException Any exception caught that has to do with the I/O.
     */
    public void save(ArrayList<Task> tasks) throws DukeException {
        String taskListContent = convertToSaveString(tasks);

        try {
            // create the file if it does not exist
            saveFile.createNewFile();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

        try (FileWriter fw = new FileWriter(saveFile)) {
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
    private String convertToSaveString(ArrayList<Task> tasks) {
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
