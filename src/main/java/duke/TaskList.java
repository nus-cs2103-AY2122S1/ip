package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;
    private int taskCount;

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
        this.taskCount = tasks.size();
    }

    public ArrayList<Task> get() {
        return this.taskList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the task list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        this.taskCount++;
        String taskCount = (this.taskCount == 1) ? "1 task" : this.taskCount + " tasks";
        System.out.println("Got it. I've added this task:\n" + "  " + task.toString()
                + "\n" + "Now you have " + taskCount + " in the list.");
    }

    /**
     * Lists the current tasks in the task list.
     */
    public void listTasks() {
        int i = 0;
        for (Task task : this.taskList) {
            if (task != null) {
                System.out.println(++i + "." + task.toString());
            } else {
                break;
            }
        }
        if (i == 0) {
            System.out.println("Your list is currently empty.");
        }
    }

    /**
     * Marks a certain task in the task list as done, using its task number.
     *
     * @param taskNumber The number of the task in the task list.
     * @throws DukeException If the task number does not exist.
     */
    public void markTaskAsDone(int taskNumber) throws DukeException {
        try {
            Task doneTask = this.taskList.get(taskNumber - 1);
            doneTask.markDone();
            System.out.println("Nice! I've marked this task as done:\n" + "  "
                    + doneTask.toString());
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! That task does not exist.");
        }
    }

    /**
     * Deletes a certain task from the task list, using its task number.
     *
     * @param taskNumber The number of the task in the task list.
     * @throws DukeException If the task number does not exist.
     */
    public void deleteTask(int taskNumber) throws DukeException {
        try {
            Task deletedTask = this.taskList.get(taskNumber - 1);
            this.taskList.remove(taskNumber - 1);
            this.taskCount--;
            String taskCount = (this.taskCount == 1) ? "1 task" : this.taskCount + " tasks";
            System.out.println("Noted. I've removed this task:\n" + "  "
                    + deletedTask.toString() + "\n" + "Now you have " + taskCount
                    + " in the list.");

        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! That task does not exist.");
        }
    }
}
