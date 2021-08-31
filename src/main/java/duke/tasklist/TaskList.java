package duke.tasklist;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

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
     * String of all the tasks.
     *
     * @return Returns a list of all tasks.
     */
    public String getAllTasksString() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output += i + 1 + ": " + tasks.get(i) + "\n";
            System.out.println(i + 1 + ": " + tasks.get(i));
        }
        return output;
    }

    /**
     * String of a task with the index specified.
     *
     * @param index Index of the task to print.
     * @return Task with the specified index.
     */
    public String getTaskString(int index) { // RENAME
        System.out.println(index + 1 + ": " + tasks.get(index));
        return index + 1 + ": " + tasks.get(index);
    }

    /**
     * Mark a task as done.
     *
     * @param taskId ID of the task we are marking as done.
     * @return Response to the task being marked as done.
     */
    public String markTaskAsDone(int taskId) throws DukeException {
        if (taskId < 1 || taskId > tasks.size()) {
            throw new DukeException("☹ OOPS!!! Please provide a task ID that exists.");
        }
        tasks.get(taskId - 1).setIsDone(true);
        System.out.println("Nice! I've marked this task as done.");
        System.out.println("   " + tasks.get(taskId - 1));
        return "Nice! I've marked this task as done.\n" + "    " + tasks.get(taskId - 1);
    }

    /**
     * Adds a duke.task.Todo task.
     *
     * @param description Description of the duke.task.Todo.
     * @return Response to a Todo being added.
     */
    public String addTodo(String description) {
        tasks.add(new Todo(description, false));
        return getLatestTaskString();
    }

    /**
     * Adds a duke.task.Deadline task.
     *
     * @param fullDescription String that contains the description and deadline of the task.
     * @return Response to a deadline being added.
     */
    public String addDeadline(String fullDescription) throws DukeException {
        int sepIndex = fullDescription.indexOf("/by");
        if (fullDescription.charAt(sepIndex + 3) != ' ' || sepIndex == -1 || sepIndex == 0 ||
                fullDescription.charAt(sepIndex - 1) != ' ') {
            throw new DukeException("☹ OOPS!!! Please input with the correct format e.g. deadline return books" +
                    " /by 2023-04-05 18:40 (yyyy-mm-dd hh:mm, where time is optional)");
        }
        String description = fullDescription.substring(0, sepIndex - 1);
        String dateTime = fullDescription.substring(sepIndex + 4);
        String[] dateTaskArray = dateTime.split(" ");
        if (dateTaskArray.length == 2) {
            System.out.println(dateTime);
            tasks.add(new Deadline(description, dateTime, FORMAT_TIME, true, false));
        } else {
            tasks.add(new Deadline(description, dateTime, FORMAT_NO_TIME, false, false));
        }
        return getLatestTaskString();
    }

    /**
     * Adds an duke.task.Event task.
     *
     * @param fullDescription String that contains the description and time of the task.
     * @return Response to an event being added.
     */
    public String addEvent(String fullDescription) throws DukeException {
        int sepIndex = fullDescription.indexOf("/at");
        if (fullDescription.charAt(sepIndex + 3) != ' ' || sepIndex == -1 || sepIndex == 0 ||
                fullDescription.charAt(sepIndex - 1) != ' ') {
            throw new DukeException("☹ OOPS!!! Please input with the correct format e.g. event read books" +
                    " /at 2021-09-08 09:30 (yyyy-mm-dd hh:mm, where time is optional)");
        }
        String description = fullDescription.substring(0, sepIndex - 1);
        String dateTime = fullDescription.substring(sepIndex + 4);
        String[] dateTaskArray = dateTime.split(" ");
        if (dateTaskArray.length == 2) {
            tasks.add(new Event(description, dateTime, FORMAT_TIME, true, false));
        } else {
            tasks.add(new Event(description, dateTime, FORMAT_NO_TIME, false, false));
        }
        return getLatestTaskString();
    }

    /**
     * Prints information about the latest task that was just added.
     *
     * @return String representing the latest task that was added.
     */
    public String getLatestTaskString() {
        String result = "Got it. I've added this task:\n";
        result += "   " + tasks.get(tasks.size() - 1) + "\n";
        result += "Now you have " + tasks.size() + " task(s) in the list.";
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
        return result;
    }

    /**
     * Deletes a specific task.
     *
     * @param taskId ID of the task to be deleted.
     * @return Response to the task being deleted.
     */
    public String deleteTask(int taskId) throws DukeException {
        if (taskId < 1 || taskId > tasks.size()) {
            throw new DukeException("☹ OOPS!!! Please provide a task ID that exists.");
        }
        String output = "Noted. I have removed this task:\n";
        output += "   " + tasks.get((taskId - 1)) + "\n";
        System.out.println("Noted. I have removed this task:");
        System.out.println("   " + tasks.get(taskId - 1));
        tasks.remove(taskId - 1);
        output += "Now you have " + tasks.size() + " task(s) in the list.";
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
        return output;
    }

    /**
     * Find tasks that match a certain keyword.
     *
     * @param keyword Keyword supplied by the user.
     * @return String of tasks that match the keyword.
     */
    public String findTask(String keyword) {
        String output = "";
        ArrayList<Integer> taskIndexes = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT))) {
                taskIndexes.add(i);
            }
        }
        if (taskIndexes.isEmpty()) {
            output += "No matches found!";
            System.out.println("No matches found!");
        } else {
            output += "Here are the matching items in your list:";
            System.out.println("Here are the matching tasks in your list:");
            for (int i : taskIndexes) {
                output += "\n" + getTaskString(i);
            }
        }
        return output;
    }
}