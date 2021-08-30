package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.io.*;
import java.util.ArrayList;

/**
 * Handles the reading and writing to the save file.
 */
public class Storage {
    private String filePath;

    /**
     * Initialises a new instance of Storage.
     *
     * @param filePath The filepath to save the save file to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads from the save file if it exists, otherwise a new save file is created.
     *
     * @return The previously saved task list, if it exists.
     * @throws DukeException If there was an error reading or writing to the save file.
     */
    public ArrayList<Task> load() throws DukeException {
        File save = new File(this.filePath);
        try {
            ArrayList<Task> savedList = new ArrayList<>();

            if (save.isFile()) {
                // If the save file exists, return the previously saved list.
                BufferedReader br = new BufferedReader(new FileReader(this.filePath));
                String nextLine = br.readLine();
                while (nextLine != null) {
                    savedList.add(Parser.processTaskString(nextLine));
                    nextLine = br.readLine();
                }                br.close();

            } else {
                // If the save file does not exist, create a new save file.
                BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath));
                bw.close();
            }
            return savedList;
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! There was an error starting Duke.\n"
            + "Your changes may not be saved.");
        }
    }

    /**
     * Saves the current task list to the save file.
     *
     * @param  taskList The given task list.
     * @throws DukeException If there was an error writing to the save file.
     */
    public void save(TaskList taskList) throws DukeException {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath));
            for (Task task : taskList.get()) {
                bw.write(task.parseToString() + "\n");
            }
            bw.close();
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! There was an error updating your task list.");
        }
    }
}
