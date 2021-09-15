package duke.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * A class that contains the task list and deals with add/delete operation on the list.
 */
public class TaskList {

    private enum TaskType {
        TODO, EVENT, DEADLINE
    }

    private final List<Task> tasks;
    private int taskNum;

    /**
     * Constructs a TaskList instance that contains the task list and deals with add/delete operation on the list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList instance that contains the task list and deals with add/delete operation on the list.
     *
     * @param storageLoad The list containing all the user's tasks.
     * @throws DukeException The exception that handles the possible exceptions thrown when the program runs.
     */
    public TaskList(List<String> storageLoad) throws DukeException {
        if (storageLoad == null) {
            throw new DukeException("Loading failed!");
        }

        tasks = new ArrayList<>();

        addLoad(storageLoad);
    }

    private Task createTask(TaskType type, String[] tags, String description, String date) {
        switch (type) {
        case TODO:
            return new Todo(tags, description);
        case EVENT:
            LocalDate at = LocalDate.parse(date);
            return new Event(tags, description, at);
        case DEADLINE:
            LocalDate by = LocalDate.parse(date);
            return new Deadline(tags, description, by);
        default:
            return null;
        }
    }

    private void addLoad(List<String> storageLoad) {
        for (String taskString : storageLoad) {
            // Extract task details into three parts
            String[] taskDetails = taskString.split(" \\| ", 5);

            TaskType type = TaskType.valueOf(taskDetails[0]);
            String[] tags = taskDetails[2].split(" ");
            String description = taskDetails[3];
            String date = type.equals(TaskType.TODO) ? null : taskDetails[4];
            Task task = createTask(type, tags, description, date);

            boolean isTask = task != null;
            boolean isDone = taskDetails[1].equals("1");
            if (isTask && isDone) {
                task.markAsDone();
            }
            if (isTask) {
                add(task);
            }
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getTaskNum() {
        return taskNum;
    }

    /**
     * Adds task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
        taskNum++;
    }

    /**
     * Deletes task from the task list.
     *
     * @param taskNum The task to be deleted.
     */
    public void delete(int taskNum) {
        tasks.remove(taskNum);
        this.taskNum--;
    }
}
