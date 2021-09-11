package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;

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
        File saveFile = new File(this.filePath);
        try {
            ArrayList<Task> savedList = new ArrayList<>();

            if (saveFile.isFile()) {
                // If the save file exists, simply load the data from the save file.
                BufferedReader br = new BufferedReader(new FileReader(this.filePath));
                String nextTask = br.readLine();
                while (nextTask != null) {
                    savedList.add(Parser.convertStringToTask(nextTask));
                    nextTask = br.readLine();
                }
                br.close();

            } else {
                // If the save file does not exist, create a new save file.
                BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath));
                bw.close();
            }
            return savedList;
        } catch (Exception e) {
            throw new DukeException("Damn!!! The save file was too intimidated to form.\n"
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
        assert taskList != null : "Task list is not initialised.";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath));
            for (Task task : taskList.get()) {
                bw.write(task.parseToString() + "\n");
            }
            bw.close();
        } catch (Exception e) {
            throw new DukeException("What the!!! The save file is too terrified to "
                + "let me save your task list!");
        }
    }
}
