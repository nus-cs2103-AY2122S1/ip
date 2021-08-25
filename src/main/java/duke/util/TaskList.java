package duke.util;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** A class that contains the task list and deals with add/delete operation on the list. */
public class TaskList {

    private enum TaskType {
        TODO, EVENT, DEADLINE
    }

    private final List<Task> tasks;
    private int taskNum;

    /**
     * A constructor for class TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * A constructor for class TaskList.
     *
     * @param storageLoad The list containing all the user's tasks.
     * @throws DukeException The exception that handles the possible exceptions thrown when the program runs.
     */
    public TaskList(List<String> storageLoad) throws DukeException {

        if (storageLoad == null) {
            throw new DukeException("Loading failed!");
        }

        this.tasks = new ArrayList<>();

        for (String taskString : storageLoad) {
            // Extract task details into three parts
            String[] taskDetails = taskString.split(" \\| ", 4);
            TaskType type = TaskType.valueOf(taskDetails[0]);
            boolean isDone = taskDetails[1].equals("1");
            String description = taskDetails[2];

            // Create task based on the extracted details
            Task task = null;
            switch (type) {
            case TODO:
                task = new Todo(description);
                break;
            case EVENT:
                LocalDate at = LocalDate.parse(taskDetails[3]);
                task = new Event(description, at);
                break;
            case DEADLINE:
                LocalDate by = LocalDate.parse(taskDetails[3]);
                task = new Deadline(description, by);
                break;
            }

            // Add to the task list if and only if it is valid in data file
            if (task != null) {
                if (isDone) {
                    task.markAsDone();
                }
                this.tasks.add(task);
                this.taskNum++;
            }
        }
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public int getTaskNum() {
        return this.taskNum;
    }

    /**
     * Add task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
        this.taskNum++;
    }

    /**
     * Delete task from the task list.
     *
     * @param taskNum The task to be deleted.
     */
    public void delete(int taskNum) {
        this.tasks.remove(taskNum);
        this.taskNum--;
    }
}
