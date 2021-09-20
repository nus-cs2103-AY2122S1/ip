package uhtredragnarson.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import uhtredragnarson.task.Deadline;
import uhtredragnarson.task.Event;
import uhtredragnarson.task.Task;
import uhtredragnarson.task.ToDo;

/**
 * This class stores the user tasks and has methods to manipulate them.
 */
public class TaskList {

    private final List<Task> tasks;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        tasks = taskList;
    }

    protected int size() {
        return tasks.size();
    }

    public List<Task> getList() {
        return tasks;
    }

    /**
     * Adds a to-do task to the list and returns a message to the user.
     *
     * @param userInput The input of the user.
     * @param ui        Ui class to print messages to the user.
     * @return The String message.
     */
    public String addTodoTask(String userInput, Ui ui, Storage storage) throws IOException {
        if (userInput.equals("todo")) {
            return "☹ OOPS!!! You need to enter a description of the task!";
        }
        ToDo todo = new ToDo(userInput.substring(5));
        tasks.add(todo);
        storage.appendToFile(todo.toString());
        return ui.showTodoMessage(todo, tasks);
    }

    /**
     * Adds a deadline task to the list and returns a message to the user.
     *
     * @param userInput The input of the user.
     * @param ui        Ui class to print messages to the user.
     * @return The String message.
     */
    public String addDeadlineTask(String userInput, Ui ui, Storage storage) throws DateTimeParseException,
            IOException {
        if (userInput.equals("deadline")) {
            return "☹ OOPS!!! You need to enter a description along with the deadline!";
        }
        int index = userInput.indexOf('/');
        Deadline deadline = new Deadline(userInput.substring(9, index), formatInputForDeadline(userInput, index));
        tasks.add(deadline);
        storage.appendToFile(deadline.toString());
        return ui.showDeadlineMessage(deadline, tasks);
    }

    /**
     * Adds an event task to the list and returns a message to the user.
     *
     * @param userInput The input of the user.
     * @param ui        Ui class to print messages to the user.
     * @return The String message.
     */
    public String addEventTask(String userInput, Ui ui, Storage storage) throws IOException {
        if (userInput.equals("event")) {
            return "☹ OOPS!!! You need to enter a description along with the event timings!";
        }
        int index = userInput.indexOf('/');
        Event event = new Event(userInput.substring(6, index), formatInputForEvent(userInput, index));
        tasks.add(event);
        storage.appendToFile(event.toString());
        return ui.showEventMessage(event, tasks);
    }

    /**
     * Deletes a task from the list and returns a message to the user.
     *
     * @param userInput The input of the user.
     * @param ui        Ui class to print messages to the user.
     * @return The String message.
     */
    protected String deleteTask(String userInput, Ui ui, Storage storage) throws IOException {
        if (userInput.equals("delete")) {
            return "☹ OOPS!!! You need to enter a index shown by the list command!";
        }
        if (tasks.size() == 0) {
            return "You have nothing in your list to delete!";
        }
        int index;
        try {
            index = Integer.parseInt(userInput.split(" ")[1]);
        } catch (NumberFormatException e) {
            return "☹ OOPS!!! You need to enter a POSITIVE INTEGER!";
        }
        if (index > tasks.size()) {
            return "You do not have that many tasks!";
        } else if (index <= 0) {
            return "☹ OOPS!!! You need to input a POSITIVE INTEGER!";
        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        storage.deleteFromFile(task.toString());
        return ui.showDeleteMessage(task, tasks);
    }

    /**
     * Prints out all the user tasks.
     *
     * @return The user tasks.
     */
    protected String printTaskList() {
        if (tasks.size() == 0) {
            return "You have nothing in your list! You are free!!!";
        }
        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (Task t : tasks) {
            result.append(counter).append(".").append(t.toString()).append("\n");
            counter++;
        }
        return result.toString();
    }

    /**
     * Marks a task as done and returns a message to the user.
     *
     * @param userInput The input of the user.
     * @param ui        Ui class to print messages to the user.
     * @return The String message.
     */
    protected String markTaskAsDone(String userInput, Ui ui, Storage storage) throws IOException {
        if (userInput.equals("done")) {
            return "☹ OOPS!!! You need to enter a index shown by the list command!";
        }
        if (tasks.size() == 0) {
            return "You have nothing in your list to mark as done!";
        }
        int index;
        try {
            index = Integer.parseInt(userInput.split(" ")[1]);
        } catch (NumberFormatException e) {
            return "☹ OOPS!!! You need to enter a POSITIVE INTEGER!";
        }
        if (index > tasks.size()) {
            return "You do not have that many tasks!";
        } else if (index <= 0) {
            return "☹ OOPS!!! You need to input a POSITIVE INTEGER!";
        }
        storage.deleteFromFile(tasks.get(index - 1).toString());
        tasks.get(index - 1).markAsDone();
        Task task = tasks.get(index - 1);
        storage.appendToFile(task.toString());
        return ui.showDoneMessage(task);
    }

    /**
     * Finds matching tasks based on a keyword.
     *
     * @param userInput The input of the user.
     * @param ui        Ui class to print messages to the user.
     * @return The matching tasks.
     */
    protected String findTasks(String userInput, Ui ui) {
        if (userInput.equals("find")) {
            return "☹ OOPS!!! You need to input a keyword!";
        }
        String keyword = userInput.split(" ")[1];
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            for (String s : task.getTitle().split(" ")) {
                if (keyword.equals(s)) {
                    matchingTasks.add(task);
                }
            }
        }
        if (matchingTasks.size() == 0) {
            return "I found no matching tasks :(";
        }
        return ui.showMatchingTasks(matchingTasks);
    }

    private String formatInputForDeadline(String userInput, int index) {
        String by = userInput.substring(index + 4);
        LocalDateTime localDateTime = LocalDateTime.parse(by, formatter);
        return localDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    }

    private String formatInputForEvent(String userInput, int index) {
        String[] arr = userInput.substring(index + 4).split(" to ");
        String start = arr[0];
        String end = arr[1];
        LocalDateTime localDateTime = LocalDateTime.parse(start, formatter);
        start = localDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        return start + "-" + end;
    }
}
