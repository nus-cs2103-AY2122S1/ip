package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Handles the interactions with the data file.
 */
public class Storage {

    /** The relative file path of the data file. */
    private String filepath;

    /** Constructs a Storage object to handle the interactions with the data file. */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Saves the user's TaskList to the data file when the user exits the program.
     * If the data file does not exist the the file path, a new data file is created
     * for the user.
     *
     * @param tasks The user's TaskList to be saved.
     */
    public void save(TaskList tasks) {
        try {
            File file = new File(filepath);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(filepath);

            String toSave = "";

            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.getTask(i);
                toSave = toSave.concat(currentTask.getTaskType()
                        + " | "
                        + currentTask.isDoneToInt()
                        + " | "
                        + currentTask.getTaskDescription() + "\n");
            }
            writer.write(toSave);

            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads the data from the data file and returns the TaskList from when the user last ran Duke.
     * If the data file does not exist in the file path, an empty TaskList will be returned.
     *
     * @return If the data files exists in the file path, returns the TaskList that is loaded.
     * If not, returns an empty TaskList.
     */
    public TaskList load() {
        ArrayList<Task> loadedTaskList = new ArrayList<>();

        try {
            File file = new File(filepath);

            if (file.createNewFile()) {
                return new TaskList();
            }

            Scanner s = new Scanner(file);

            while (s.hasNext()) {
                String[] lineSplit = s.nextLine().split(" \\| ", 5);

                switch (lineSplit[0]) {
                case "T":
                    assert lineSplit.length == 3;
                    loadedTaskList.add(new ToDo(lineSplit[2]));
                    break;
                case "D":
                    assert lineSplit.length == 5;
                    loadedTaskList.add(new Deadline(lineSplit[2], lineSplit[3], lineSplit[4]));
                    break;
                case "E":
                    assert lineSplit.length == 4;
                    loadedTaskList.add(new Event(lineSplit[2], lineSplit[3], lineSplit[4]));
                    break;
                default:
                    break;
                }

                if (lineSplit[1].equals("0")) {
                    loadedTaskList.get(loadedTaskList.size() - 1).markDone();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new TaskList(loadedTaskList);
    }
}
