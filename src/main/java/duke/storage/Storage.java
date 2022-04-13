package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents the location used to store and retrieve the list of tasks.
 */
public class Storage {

    /**
     * The filepath of where the list of task is stored
     **/
    private String filePath;

    /**
     * Constructs the storage
     *
     * @param filePath The location of the list of tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Fetches the list of tasks.
     *
     * @return The arraylist of tasks.
     * @throws DukeException if file cannot be found.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File data = new File(filePath);
            Scanner sc = new Scanner(data);
            
            while (sc.hasNextLine()) {
                String[] argArr = sc.nextLine().split("\\|");
                Task taskToBeAdded;
                switch (argArr[0]) {
                case "E":
                    taskToBeAdded = new Event(argArr[2], LocalDate.parse(argArr[3]));
                    break;
                case "D":
                    taskToBeAdded = new Deadline(argArr[2], LocalDate.parse(argArr[3]));
                    break;
                default:
                    taskToBeAdded = new Todo(argArr[2]);
                    break;
                }
                if (argArr[1].equals("1")) {
                    taskToBeAdded.markTaskAsDone();
                }
                taskList.add(taskToBeAdded);
            }
            
            sc.close();
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("Sorry, I can't find your list of tasks. I'll create a new one for you.");
        }
    }

    /**
     * Saves the list of tasks. If location doesn't exist, creates it.
     *
     * @param tasks The list of tasks to be saved.
     * @throws DukeException If storage cannot save list at specified path.
     */
    public void write(TaskList tasks) throws DukeException {
        try {
            Path filePath = Paths.get(this.filePath);
            Path directoryPath = filePath.getParent();
            File directoryName = new File(directoryPath.toString());
            
            if (!directoryName.exists()) {
                directoryName.mkdirs();
            }
            
            FileWriter fw = new FileWriter(this.filePath);
            for (Task currTask : tasks.getTasks()) {
                fw.write(currTask.getTaskType() + "|" + (currTask.getIsDone() ? "1" : "0") + "|"
                        + currTask.getDescription()
                        + (currTask.getTiming() == null ? "\n" : "|" + currTask.getTiming().toString() + "\n"));
            }
            
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Sorry, I was unable to store your list of tasks");
        }
    }

}
