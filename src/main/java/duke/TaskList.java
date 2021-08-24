package duke;

import duke.task.*;
import java.util.ArrayList;

/**
 * TaskList contains the list of Tasks.
 * It handles display, add, markAsDone and delete operations.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Initialises an empty tasks arraylist.
     */
    public TaskList() {this.tasks = new ArrayList<>();}

    /**
     * Initialises the tasks with the tasks from storage.
     *
     * @param tasks the tasks obtained from storage.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks= tasks;
    }

    /**
     * Adds a newTask into the taskList.
     *
     * @param newTask the task to be added.
     */
    public void add(Task newTask) {
        String addedMessage = "Got it. I've added this task:\n";
        tasks.add(newTask);
        System.out.println(addedMessage + newTask.toString() +
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Marks a current task as done.
     *
     * @param taskNumber the index of the task to be marked.
     */
    public void markAsDone(int taskNumber) throws DukeException {
        if (taskNumber > tasks.size()) {
            throw new DukeException("☹ OOPS!!! " +
                    "There isn't a task number " + taskNumber + ".");
        }
        Task t = tasks.get(taskNumber - 1);
        t.setDone();
        System.out.println("Nice! I've marked this task as done:\n"
                + "  " + t.toString());
    }

    /**
     * Deletes a task from the taskList.
     *
     * @param taskNumber the index of the task to be deleted.
     */
    public void delete(int taskNumber) throws DukeException {
        if (taskNumber > tasks.size()) {
            throw new DukeException("☹ OOPS!!! " +
                    "There isn't a task number " + taskNumber + ".");
        }
        Task t = tasks.remove(taskNumber - 1);
        System.out.println("Noted! I've removed this task:\n" + "  "
                + t.toString() + "\nNow you have " + tasks.size()  + " tasks in the list.");
    }

    /**
     * Prints the current taskList.
     */
    public void display() {
        String listString = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            listString += "\n" + (i + 1) + "." + tasks.get(i).toString();
        }
        System.out.println(listString);
    }

    /**
     * Returns the tasks arraylist.
     *
     * @return the tasks arraylist.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
