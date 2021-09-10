package duke.data;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import duke.DukeException;
import duke.tasks.*;

/**
 * Encapsulates the saving and loading of task data from a save file.
 */
public class Storage {
    protected String pathName;
    protected File taskFile;

    /**
     * Initialises the object with a path to the save file.
     *
     * @param pathName A String containing a relative path to the save file
     */
    public Storage(String pathName) {
        this.pathName = pathName;
        this.taskFile = new File(pathName);
    }

    /**
     * Saves a given TaskList to the file at the path given at initialisation.
     *
     * @param taskList The list of tasks to be saved to file
     * @throws IOException Thrown in the case of an error while initialising the file writer
     */
    public void saveTaskData(TaskList taskList) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathName));
        if (!taskList.isEmpty()) {
            writer.write(taskList.get(0).getFormattedData() + "\n");
        }

        for (int i = 1; i < taskList.size(); ++i) {
            writer.append(taskList.get(i).getFormattedData()).append("\n");
        }

        writer.close();
    }

    /**
     * Loads a TaskList from a save file at the path given at initialisation.
     *
     * @return The TaskList representing the tasks retrieved from the save file
     * @throws IOException Thrown when there is an exception while creating the save file (if it doesn't already exist)
     */
    public TaskList loadTaskData() throws IOException, DukeException {
        this.taskFile.getParentFile().mkdirs();
        if (!this.taskFile.createNewFile()) {
            // Save file exists, so load it
            Scanner sc = new Scanner(this.taskFile);
            List<Task> taskData = new ArrayList<>();
            while (sc.hasNextLine()) {
                String task = sc.nextLine();

                // Index 0 should contain the identifier for the type of task being loaded
                // The rest of the indices are arguments that provide more information (E.g. description, deadline, etc)
                String[] taskDetails = task.split("\\|");
                assert(taskDetails.length >= 3);
                assert(Objects.equals(taskDetails[1], "0") || Objects.equals(taskDetails[1], "1"));
                boolean taskDone = Objects.equals(taskDetails[1], "1");

                switch (taskDetails[0]) {
                case "T":
                    taskData.add(new Todo(taskDetails[2], taskDone));
                    break;
                case "D":
                    assert(taskDetails.length == 4);
                    taskData.add(new Deadline(taskDetails[2], LocalDate.parse(taskDetails[3]), taskDone));
                    break;
                case "E":
                    assert(taskDetails.length == 4);
                    taskData.add(new Event(taskDetails[2], LocalDate.parse(taskDetails[3]), taskDone));
                    break;
                case "P":
                    assert(taskDetails.length == 5);
                    taskData.add(new DoWithinPeriodTask(taskDetails[2], LocalDate.parse(taskDetails[3]), LocalDate.parse(taskDetails[4]), taskDone));
                    break;
                default:
                    throw new DukeException("Error loading Task. Unrecognised Task identifier");
                }
            }

            sc.close();
            return new TaskList(taskData);
        } else {
            // No save file exists, so a new one is created. Return empty list of tasks
            return new TaskList(new ArrayList<>());
        }
    }
}
