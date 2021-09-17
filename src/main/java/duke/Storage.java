package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

import duke.error.DukeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TodoTask;

/**
 * Deals with loading tasks from the file and saving tasks in the files.
 */
public class Storage {
    private String directoryPath;
    private String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param directoryPath Path to data file directory.
     * @param filePath Path to data file.
     */
    public Storage(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = directoryPath.concat(filePath);
    }

    /**
     * Loads the tasks from the data file.
     *
     * @return List of task loaded from the data file.
     * @throws DukeException If something wrong happens.
     */
    public ArrayList<Task> load() throws DukeException {
        File directory = new File(directoryPath);
        File dataFile = new File(filePath);

        ArrayList<Task> tasks = new ArrayList<>();

        try {
            if (!directory.exists()) {
                Files.createDirectories(Paths.get("data/"));
            }
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("OOPS!! Something went wrong");
        }

        try {
            Scanner f = new Scanner(dataFile);

            while (f.hasNext()) {
                String[] task = f.nextLine().split("/");

                String taskType = task[0];
                boolean isDone = (Integer.parseInt(task[1]) == 1);
                String description = task[2];
                String time = task.length == 4 ? task[3] : null;

                assert taskType.equals("T") || taskType.equals("D") || taskType.equals("E"): "invalid task type";

                if (taskType.equals("T")) {
                    tasks.add(new TodoTask(description, isDone));
                } else if (taskType.equals("D")) {
                    tasks.add(new DeadlineTask(description, isDone, time));
                } else {
                    tasks.add(new EventTask(description, isDone, time));
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("OOPS!! Something went wrong");
        }
        return tasks;
    }

    /**
     * Saves the tasks in the list to the data file.
     *
     * @param tasks Current tasks.
     * @throws IOException
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        ArrayList<Task> listOfTasks = tasks.getListOfTasks();

        for (int i = 0; i < listOfTasks.size(); i++) {
            Task t = listOfTasks.get(i);

            String type = t.getType();
            String description = t.getDescription();
            String isDone = t.getStatusIcon().equals("X") ? "1" : "0";

            assert type.equals("T") || type.equals("D") || type.equals("E"): "invalid task type";

            String line = type + "/" + isDone + "/" + description;
            if (type.equals("D")) {
                DeadlineTask dt = (DeadlineTask) t;
                line += "/" + dt.getTime();
            } else if (type.equals("E")) {
                EventTask et = (EventTask) t;
                line += "/" + et.getTime();
            }
            line += "\n";
            fw.write(line);
        }

        fw.close();
    }
}
