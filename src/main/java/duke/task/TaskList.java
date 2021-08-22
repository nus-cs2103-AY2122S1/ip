package duke.task;

import duke.io.Ui;

import java.util.ArrayList;

/**
 * Encapsulates a list of tasks and support manipulation of the tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the task at index i.
     *
     * @param i the index of the task.
     * @return the task at index i.
     */
    public Task get(int i) {
        return taskList.get(i);
    }

    /**
     * Removes the task at index i.
     *
     * @param i the index of the task.
     */
    public void remove(int i) {
        taskList.remove(i);
    }

    /**
     * Adds task to the end of the list.
     *
     * @param task the task to be added into the list.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * String representation of the list of task.
     *
     * @return String that represents the list of task.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            sb.append(task.toDataFormat());
            if (i != taskList.size() - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    /**
     * Prints out the task list formatted and indented.
     *
     * @param ui The Ui object which is used to print to output.
     */
    public void print(Ui ui) {
        StringBuilder tasksString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            String taskAsString = i == taskList.size() - 1
                    ? String.format("%d.%s", i + 1, taskList.get(i))
                    : String.format("%d.%s\n", i + 1, taskList.get(i));
            tasksString.append(taskAsString);
        }
        ui.print(tasksString.toString());
    }
}
