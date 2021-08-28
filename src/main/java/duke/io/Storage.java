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
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Encapsulates the methods used to read and store data onto disk.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task list from file path.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);

        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] taskString = s.nextLine().trim().split(" \\| ");
                Task task;
                switch (taskString[0]) {
                case "T":
                    task = new Todo(taskString[2]);
                    break;
                case "D":
                    task = new Deadline(taskString[2], taskString[3]);
                    break;
                case "E":
                    task = new Event(taskString[2], taskString[3]);
                    break;
                default:
                    throw new DukeException("Invalid task type found!");
                }
                if (taskString[1].equals("1")) {
                    task.setDone();
                }
                tasks.add(task);
            }
            s.close();
        } catch (FileNotFoundException e) {
            createFile();
        }

        return tasks;
    }

    /**
     * Creates file at the specified file path.
     */
    private void createFile() throws DukeException {
        try {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
            f.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Could not create new file!");
        }
    }

    /**
     * Saves task list to file path.
     *
     * @param taskList the path to save task list to.
     */
    public void save(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(taskList.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to save your list of tasks!");
        }
    }
}
