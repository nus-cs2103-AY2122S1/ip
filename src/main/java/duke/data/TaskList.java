package duke.data;

import duke.commands.Deadline;
import duke.commands.Event;
import duke.commands.Task;
import duke.commands.ToDo;

import java.time.LocalDateTime;

import java.util.ArrayList;


/**
 * Encapsulates the operations related to managing task.
 *
 * @author Zhi Bin
 * @version Duke Level 8
 */
public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    /**
     * Returns a task created from the description mentioned in the string.
     * Used to convert stored data from local directory into task.
     *
     * @param str The string with a task description.
     * @return The created task.
     */
    public static Task stringToTask(String str) {
        String[] taskData = str.split("\\|");
        boolean isDone = (taskData[1].equals("1")) ? true : false;

        switch (taskData[0]) {
        case "D":
            LocalDateTime by = LocalDateTime.parse(taskData[3]);
            Deadline deadline = new Deadline(taskData[2], isDone, by);
            return deadline;

        case "E":
            LocalDateTime at = LocalDateTime.parse(taskData[3]);
            Event event = new Event(taskData[2], isDone, at);
            return event;

        default:
            ToDo toDo = new ToDo(taskData[2], isDone);
            return toDo;
        }
    }

    /**
     * Adds the task to current list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Deletes a specific task corresponding to the number in the list.
     *
     * @param taskNum The task number.
     * @return The task deleted.
     */
    public Task delete(int taskNum) {
        return list.remove(taskNum - 1);
    }

    /**
     * Marks a specific task corresponding to the number in the list as done.
     *
     * @param taskNum The task number.
     * @return The task that was marked as done.
     */
    public Task mark(int taskNum) {
        Task task = list.get(taskNum - 1);
        task.markDone();
        return task;
    }

    /**
     * Returns the number of task in the current list.
     *
     * @return The number of task in the list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns the current list of task.
     *
     * @return Returns an ArrayList consisting of all the Task stored.
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Stores the task from the given task list into the current task list.
     *
     * @param list The task list to be stored to the current list.
     */
    public void loadFromList(ArrayList<Task> list) {
        this.list = list;
    }
}
