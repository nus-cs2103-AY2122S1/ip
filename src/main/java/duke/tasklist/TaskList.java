package duke.tasklist;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents the list of tasks.
 */
public class TaskList {

    private static ArrayList<Task> tasks;
    private static final DateTimeFormatter FORMAT_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter FORMAT_NO_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructor for TaskList.
     *
     * @param tasks ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Lists out all the tasks.
     */
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ": " + tasks.get(i));
        }
    }

    /**
     * Returns a string of all the tasks.
     *
     * @return A string of all tasks.
     */
    public String tasksAsString() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result += (i + 1 + ": " + tasks.get(i) + System.lineSeparator());
        }
        return result;
    }

    /**
     * Mark a task as done.
     *
     * @param taskId ID of the task we are marking as done.
     */
    public void markTaskAsDone(int taskId) throws DukeException {
        if (taskId < 1 || taskId > tasks.size()) {
            throw new DukeException("☹ OOPS!!! Please provide a task ID that exists.");
        }
        tasks.get(taskId - 1).setIsDone(true);
        System.out.println("Nice! I've marked this task as done.");
        System.out.println("   " + tasks.get(taskId - 1));
    }

    /**
     * Adds a duke.task.Todo task.
     *
     * @param description Description of the duke.task.Todo.
     */
    public void addTodo(String description) {
        tasks.add(new Todo(description));
        printAfterAdding();
    }

    /**
     * Adds a duke.task.Deadline task.
     *
     * @param fullDescription String that contains the description and deadline of the task.
     */
    public void addDeadline(String fullDescription) throws DukeException {
        int sepIndex = fullDescription.indexOf("/by");
        if (fullDescription.charAt(sepIndex + 3) != ' ' || sepIndex == -1 || sepIndex == 0 ||
                fullDescription.charAt(sepIndex - 1) != ' ') {
            throw new DukeException("☹ OOPS!!! Please input with the correct format e.g. deadline return books" +
                    " /by 2023-04-05 18:40 (yyyy-mm-dd hh:mm, where time is optional)");
        }
        String description = fullDescription.substring(0, sepIndex - 1);
        String dateTime = fullDescription.substring(sepIndex + 4);
        String[] datetasksay = dateTime.split(" ");
        if (datetasksay.length == 2) {
            System.out.println(dateTime);
            tasks.add(new Deadline(description, dateTime, FORMAT_TIME, true));
        } else {
            tasks.add(new Deadline(description, dateTime, FORMAT_NO_TIME, false));
        }
        ;
        printAfterAdding();
    }

    /**
     * Adds an duke.task.Event task.
     *
     * @param fullDescription String that contains the description and time of the task.
     */
    public void addEvent(String fullDescription) throws DukeException {
        int sepIndex = fullDescription.indexOf("/at");
        if (fullDescription.charAt(sepIndex + 3) != ' ' || sepIndex == -1 || sepIndex == 0 ||
                fullDescription.charAt(sepIndex - 1) != ' ') {
            throw new DukeException("☹ OOPS!!! Please input with the correct format e.g. event read books" +
                    " /at 2021-09-08 09:30 (yyyy-mm-dd hh:mm, where time is optional)");
        }
        String description = fullDescription.substring(0, sepIndex - 1);
        String dateTime = fullDescription.substring(sepIndex + 4);
        String[] datetasksay = dateTime.split(" ");
        if (datetasksay.length == 2) {
            tasks.add(new Event(description, dateTime, FORMAT_TIME, true));
        } else {
            tasks.add(new Event(description, dateTime, FORMAT_NO_TIME, false));
        }
        printAfterAdding();
    }

    /**
     * Prints information about the latest element that was just added.
     */
    public void printAfterAdding() {
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Deletes a specific task.
     *
     * @param taskId ID of the task to be deleted.
     */
    public void deleteTask(int taskId) throws DukeException {
        if (taskId < 1 || taskId > tasks.size()) {
            throw new DukeException("☹ OOPS!!! Please provide a task ID that exists.");
        }
        System.out.println("Noted. I have removed this task:");
        System.out.println("   " + tasks.get(taskId - 1));
        tasks.remove(taskId - 1);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }
}
