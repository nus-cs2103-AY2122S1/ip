package duke.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.place.Place;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Period;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Encapsulates the methods used to read and store data onto disk.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor of the storage class.
     *
     * @param filePath the path to store the tasks list to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task list from file path.
     *
     * @return the array list loaded from file path.
     * @throws DukeException exception arising from reading file path.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);

        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] taskString = s.nextLine().split(" \\| ");
                Task task;
                switch (taskString[0]) {
                case "T":
                    task = new Todo(taskString[2]);
                    break;
                case "D":
                    task = new Deadline(taskString[2], taskString[4]);
                    break;
                case "E":
                    task = new Event(taskString[2], taskString[4]);
                    break;
                case "P":
                    task = new Period(taskString[2], taskString[4], taskString[5]);
                    break;
                default:
                    throw new DukeException("Invalid task type found!");
                }
                if (taskString[1].equals("1")) {
                    task.setDone();
                }

                if (taskString.length > 3) {
                    Arrays.stream(taskString[3].split(", "))
                            .forEach(placeString -> task.addPlace(new Place(placeString)));
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
     *
     * @throws DukeException exception creating file.
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
     * @throws DukeException exception when saving tasks.
     */
    public void save(TaskList taskList) throws DukeException {
        assert taskList != null : "Task list cannot be null";
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(taskList.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to save your list of tasks!");
        }
    }
}
