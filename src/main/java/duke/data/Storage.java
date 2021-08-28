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

import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Event;

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
    public TaskList loadTaskData() throws IOException {
        this.taskFile.getParentFile().mkdirs();
        if (!this.taskFile.createNewFile()) {
            // Save file exists, so load it
            Scanner sc = new Scanner(this.taskFile);
            List<Task> taskData = new ArrayList<>();
            while (sc.hasNextLine()) {
                String task = sc.nextLine();

                String[] taskDetails = task.split("\\|");
                boolean taskDone = Objects.equals(taskDetails[1], "1");

                switch (taskDetails[0]) {
                    case "T":
                        taskData.add(new Todo(taskDetails[2], taskDone));
                        break;
                    case "D":
                        taskData.add(new Deadline(taskDetails[2], LocalDate.parse(taskDetails[3]), taskDone));
                        break;
                    case "E":
                        taskData.add(new Event(taskDetails[2], LocalDate.parse(taskDetails[3]), taskDone));
                        break;
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
