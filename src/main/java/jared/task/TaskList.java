package jared.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;

import jared.common.DukeException;
import jared.common.Message;
import jared.storage.Storage;

/**
 * Deals with tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Constructor for task list.
     * @param tasks List of tasks stored on database.
     * @param storage Storage object.
     */
    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Adds a task to the list.
     * @param command User input string command.
     * @param fullCommand Full user input string.
     * @throws DukeException If user input command is invalid.
     */
    public String add(String command, String fullCommand) throws DukeException {
        Task newTask;

        if (command.equals("todo")) {
            newTask = createTodo(fullCommand);
        } else if (command.equals("deadline")) {
            newTask = createDeadline(fullCommand);
        } else if (command.equals("event")) {
            newTask = createEvent(fullCommand);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        tasks.add(newTask);
        storage.saveData(tasks);

        return String.format("Got it. I've added this task:\n"
                        + "%s\nNow you have %d tasks in the list.",
                newTask, tasks.size()
        );
    }

    /**
     * Creates a new Todo from user input
     * @param fullCommand Full user input string
     * @return new Todo object created
     * @throws DukeException if no description is provided
     */
    public Todo createTodo(String fullCommand) throws DukeException {
        try {
            String desc = fullCommand.split(" ", 2)[1];
            Todo newTodo = new Todo(desc);
            return newTodo;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Creates a new Deadline from user input
     * @param fullCommand Full user input string
     * @return new Deadline object created
     * @throws DukeException if no description is provided or date provided is invalid
     */
    public Deadline createDeadline(String fullCommand) throws DukeException {
        try {
            String body = fullCommand.split(" ", 2)[1];
            String desc = body.split("/by", 2)[0].trim();
            String dateStr = body.split("/by", 2)[1].trim();
            String[] dateTime = dateStr.split(" ");
            String d = dateTime[0];
            LocalDate date = LocalDate.parse(d);
            Deadline newDeadline;

            if (dateTime.length > 1) {
                String t = dateTime[1];
                LocalTime time = LocalTime.parse(t);
                newDeadline = new Deadline(desc, date, time);
            } else {
                newDeadline = new Deadline(desc, date);
            }
            return newDeadline;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date. Please enter the date (yyyy-mm-dd)");
        }
    }

    /**
     * Creates a new Event from user input
     * @param fullCommand Full user input string
     * @return new Event object created
     * @throws DukeException if no description is provided or date provided is invalid
     */
    public Event createEvent(String fullCommand) throws DukeException {
        try {
            String body = fullCommand.split(" ", 2)[1];
            String desc = body.split("/at", 2)[0].trim();
            String dateStr = body.split("/at", 2)[1].trim();
            String[] dateTime = dateStr.split(" ");
            String d = dateTime[0];
            LocalDate date = LocalDate.parse(d);
            Event newEvent;

            if (dateTime.length > 1) {
                String t = dateTime[1];
                LocalTime time = LocalTime.parse(t);
                newEvent = new Event(desc, date, time);
            } else {
                newEvent = new Event(desc, date);
            }
            return newEvent;

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date and time. Please enter the date (yyyy-mm-dd)");
        }
    }

    /**
     * Lists the tasks.
     */
    public String list() {
        String res = "Here are the tasks in your list:\n";
        String tasksStr = listTasks(this.tasks);
        return (res + tasksStr);
    }

    /**
     * Marks the specified task as done.
     * @param fullCommand Full user input string.
     * @throws DukeException If task is invalid.
     */
    public String done(String fullCommand) throws DukeException {
        int taskNum;
        Task currTask;

        try {
            taskNum = Integer.valueOf(fullCommand.split(" ", 2)[1]);
            currTask = tasks.get(taskNum - 1);
        } catch (Exception e) {
            throw new DukeException("Invalid task number provided.");
        }

        currTask.markDone();
        storage.saveData(tasks);

        String res = String.format("Nice! I've marked this task as done:\n%s",
                currTask);
        return res;
    }

    /**
     * Deletes task from list.
     * @param fullCommand Full user input string.
     * @throws DukeException If task is invalid.
     */
    public String delete(String fullCommand) throws DukeException {
        int taskNum;
        Task currTask;
        int index;
        try {
            taskNum = Integer.valueOf(fullCommand.split(" ", 2)[1]);
            index = taskNum - 1;
            currTask = tasks.get(index);
        } catch (Exception e) {
            throw new DukeException("Invalid task number provided.");
        }
        tasks.remove(index);
        storage.saveData(tasks);
        return (String.format("Noted. I've removed this task:\n"
                + "%s\nNow you have %d tasks in the list.",
                currTask.toString(), tasks.size()));
    }

    /**
     * Lists the tasks in string format.
     * @param tasks List of task to be listed into a string.
     * @return String of list of tasks.
     */
    public String listTasks(ArrayList<Task> tasks) {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            res += String.format("%d. %s\n", i + 1, currTask.toString());
        }
        return res;
    }

    /**
     * Finds the task from keyword.
     * @param fullCommand User input string.
     */
    public String find(String fullCommand) {
        String word = fullCommand.split(" ")[1];
        ArrayList<Task> matchedTasks = new ArrayList<>();
        String findMessage = "Here are the matching tasks in your list:\n";

        for (Task task: tasks) {
            if (task.getDescription().contains(word)) {
                matchedTasks.add(task);
            }
        }
        return (findMessage + listTasks(matchedTasks));
    }

    /**
     * Sorts existing tasks by date
     */
    public String sort() {
        Collections.sort(this.tasks);
        return listTasks(this.tasks);
    }

    public String help() {
        return Message.MESSAGE_HELP;
    }
}
