package duke.util;

import duke.exception.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Encapsulates the storage class.
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath Filepath to/from which date is stored/loaded.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads previously saved data into array list.
     *
     * @return Array list of tasks saved previously.
     * @throws DukeException If there is error loading file.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }

            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            while (line != null) {
                String[] input = line.split("\\|");
                assert input.length >= 3 : "loaded input should have at least 3 details";
                loadTask(input, tasks);
                line = br.readLine();
            }
        } catch (IOException| DateTimeParseException e) {
            throw new DukeException("Error loading tasks...");
        }
        return tasks;
    }

    /**
     * Loads a single task into list based on type of task.
     *
     * @param input Array of task details in one line of file read.
     * @param tasks ArrayList to which task is loaded into.
     */
    private void loadTask(String[] input, ArrayList<Task> tasks) {
        String taskType = input[0];
        String description = input[2];
        boolean isTaskDone = input[1].equals("1");

        switch (taskType) {
        case "T":
            Todo todo = new Todo(description);
            setDoneAndAddToList(tasks, todo, isTaskDone);
            break;
        case "E":
            LocalDateTime at = LocalDateTime.parse(input[3]);
            Event event = new Event(description, at);
            setDoneAndAddToList(tasks, event, isTaskDone);
            break;
        case "D":
            LocalDateTime by = LocalDateTime.parse(input[3]);
            Deadline deadline = new Deadline(description, by);
            setDoneAndAddToList(tasks, deadline, isTaskDone);
            break;
        default:
            assert false : taskType;
        }
    }

    /**
     * Sets task to done if they were saved as done previously.
     *
     * @param tasks Array list into which task is added
     * @param task Task to be set as done or not.
     * @param isTaskDone Boolean of whether task has been done.
     */
    private void setDoneAndAddToList(ArrayList<Task> tasks, Task task, boolean isTaskDone) {
        if (isTaskDone) {
            task.setDone();
        }
        tasks.add(task);
    }

    /**
     * Saves tasks into a file.
     *
     * @param taskList TaskList which contains tasks to be saved.
     * @param commandType Command that called the save method.
     * @throws DukeException If there is error saving tasks to file.
     */
    public void save(TaskList taskList, String commandType) throws DukeException {
        File file = new File(filePath);
        try {
            FileWriter writer;

            if (commandType.equals("archive")) {
                writer = new FileWriter(file, true);
            } else {
                writer = new FileWriter(file);
            }

            taskList.saveTasksInStorage(writer);
            writer.close();
        } catch (IOException e) {
           throw new DukeException("Error writing file");
        }
    }

}
