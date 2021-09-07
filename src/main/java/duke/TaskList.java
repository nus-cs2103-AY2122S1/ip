package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import duke.exception.DukeException;
import duke.exception.DukeFileNotFoundException;
import duke.exception.DukeInvalidTaskNumberFormatException;
import duke.exception.DukeTaskNotFoundException;
import duke.exception.DukeTaskNumberOutOfBoundsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents the list of tasks a user has. Tasks can be added, deleted,
 * completed or simply printed out.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Constructor for empty TaskList.
     */
    public TaskList() {
        TaskList.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList with initial tasks.
     *
     * @param tasks Initial tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getNumTasks() {
        return tasks.size();
    }

    /**
     * Marks a task as done.
     * 
     * @param taskNum the task number to mark as done
     * @throws DukeFileNotFoundException if file not found
     */
    private static String completeTask(int taskNum) throws DukeFileNotFoundException {
        int taskIdx = taskNum - 1;
        assert taskIdx >= 0;
        Task task = tasks.get(taskIdx);
        task.setDone(true);
        return ("Good job! I've marked this task as done:\n\n\t" + task + "\n");
    }

    /**
     * Deletes a task from the ArrayList of tasks.
     * 
     * @param num the task number to delete
     * @throws DukeFileNotFoundException if file not found
     */
    private static String deleteSingleTask(int num) throws DukeFileNotFoundException {
        int taskIdx = num - 1;
        assert taskIdx >= 0;
        Task taskToDelete = getTasks().get(taskIdx);
        getTasks().remove(taskIdx);
        return ("Noted. I've removed this task:\n\t" + taskToDelete + "\n\tNow you have " + tasks.size()
                + " tasks in the list.\n");
    }

    /**
     * Deletes multiple tasks from the ArrayList of tasks. User inputs the task
     * numbers separated by spaces.
     *
     * @param taskNums the task numbers to delete
     * @throws DukeFileNotFoundException if file not found
     */
    private static String deleteTasks(int[] taskNums) throws DukeFileNotFoundException {
        if (taskNums.length == 1) {
            return deleteSingleTask(taskNums[0]);
        }

        Arrays.sort(taskNums);

        // Delete from the end of the list so we don't run into indexing errors
        for (int i = taskNums.length - 1; i >= 0; i--) {
            System.out.println(i);
            deleteSingleTask(taskNums[i]);
        }

        return ("Noted. I've removed task numbers: " + Arrays.toString(taskNums) + "\n\tNow you have " + tasks.size()
                + " tasks in the list.\n");
    }

    /**
     * Gets the task number in the list.
     * 
     * @param command Command to extract task number from.
     * @return Task number.
     * @throws DukeInvalidTaskNumberFormatException if task number not an integer
     * @throws DukeTaskNumberOutOfBoundsException   if task number not valid
     */
    private static int[] getTaskNums(String command)
            throws DukeInvalidTaskNumberFormatException, DukeTaskNumberOutOfBoundsException {
        try {
            String[] splitCommand = command.split(" ");
            String[] taskNums = Arrays.copyOfRange(splitCommand, 1, splitCommand.length);
            return Arrays.stream(taskNums).mapToInt(Integer::parseInt).toArray();
        } catch (NumberFormatException err) {
            System.out.println(err.getMessage());
            throw new DukeInvalidTaskNumberFormatException();
        } catch (IndexOutOfBoundsException err) {
            throw new DukeTaskNumberOutOfBoundsException();
        }
    }

    /**
     * Handles task deletion.
     * 
     * @param command user input to parse
     * @throws DukeTaskNotFoundException if task not specified
     */
    public static String handleDelete(String command) throws DukeTaskNotFoundException,
            DukeInvalidTaskNumberFormatException, DukeTaskNumberOutOfBoundsException, DukeFileNotFoundException {
        if (command.equals("delete")) {
            throw new DukeTaskNotFoundException();
        }

        int[] taskNum = getTaskNums(command);
        return deleteTasks(taskNum);
    }

    /**
     * Handles task completion.
     * 
     * @param command user input to parse
     * @throws DukeTaskNotFoundException            if task not specified
     * @throws DukeInvalidTaskNumberFormatException if task is not an integer
     * @throws DukeTaskNumberOutOfBoundsException   if task number not valid
     * @throws DukeFileNotFoundException            if file not found
     */
    public static String handleDone(String command) throws DukeTaskNotFoundException,
            DukeInvalidTaskNumberFormatException, DukeTaskNumberOutOfBoundsException, DukeFileNotFoundException {
        if (command.equals("done")) {
            throw new DukeTaskNotFoundException();
        }

        int[] taskNum = getTaskNums(command);
        return completeTask(taskNum[0]);
    }

    /**
     * Adds a task to the ArrayList of tasks.
     *
     * @param t the task to add
     * @throws DukeFileNotFoundException if file not found
     */
    private static String addTask(Task t) throws DukeFileNotFoundException {
        TaskList.getTasks().add(t);
        return ("Got it. I've added this task:\n\t" + t + "\n\tNow you have " + tasks.size() + " tasks in the list.\n");
    }

    /**
     * Adds ToDo task to the ArrayList of tasks.
     *
     * @param command user input to extract task
     * @throws DukeTaskNotFoundException if task not specified
     * @throws DukeFileNotFoundException if file not found
     */
    public static String addToDo(String command) throws DukeTaskNotFoundException, DukeFileNotFoundException {
        if (command.equals("todo")) {
            throw new DukeTaskNotFoundException();
        }

        String todo = command.split("todo")[1].trim();
        return addTask(new ToDo(todo));
    }

    /**
     * Adds event task with datetime.
     *
     * @param command User input to extract task and datetime
     * @throws DukeException Task not specified
     */
    public static String addEvent(String command) throws DukeException {
        if (command.equals("event")) {
            throw new DukeException("You need to specify which event you want to add!\n");
        }

        String eventDetails = command.substring(6);
        String[] commandSplit = splitCommand(eventDetails, "/at"); // "taskName /at datetime"
        String task = getTask(commandSplit);
        String dateTime = getDateTime(commandSplit); // dateTime is the 2nd part of the command
        return addTask(new Event(task, dateTime));
    }

    /**
     * Adds deadline task with date/time.
     *
     * @param command User input to extract task and datetime
     * @throws DukeException Task not specified
     */
    public static String addDeadline(String command) throws DukeException {
        if (command.equals("deadline")) {
            throw new DukeException("You need to specify which deadline you want to add!\n");
        }

        String taskDetails = command.substring(9);
        String[] commandSplit = splitCommand(taskDetails, "/by");
        String task = getTask(commandSplit);
        String dateTime = getDateTime(commandSplit);

        // Check if the time is in the yyyy-mm-dd datetime format
        try {
            LocalDate date = LocalDate.parse(dateTime);
            return addTask(new Deadline(task, date));
        } catch (DateTimeParseException err) {
            throw new DukeException("Please use the yyyy-mm-dd format for deadline!\n");
        }
    }

    /**
     * Splits the command into task and datetime.
     * 
     * @param command User input to extract task and dateTime
     * @param by      The string to split the command by
     * @return The task and dateTime in a String array
     */
    private static String[] splitCommand(String command, String by) throws DukeException {
        String[] commandSplit = command.split(by);

        // If cannot split the command, throw an exception
        if (commandSplit.length <= 1) {
            throw new DukeException("You need to provide a date/time!" + "\n");
        }

        return commandSplit;
    }

    /**
     * Gets the task from the split original command.
     */
    private static String getTask(String[] commandSplit) throws DukeException {
        String task = commandSplit[0].trim(); // Trim the first part of the original command

        if (task.isEmpty()) {
            throw new DukeException("You need to provide a task!" + "\n");
        }

        return task;
    }

    /**
     * Gets the datetime from the split original command.
     *
     * @param commandSplit the original command split into 2 parts
     * @return the datetime in String format
     */
    private static String getDateTime(String[] commandSplit) {
        return commandSplit[1].trim(); // Get the 2nd part of the command
    }

    /**
     * Prints all the tasks in the ArrayList of tasks.
     *
     * @param keyword string to find in tasks
     * @return all tasks containing keyword
     */
    public static String printTasks(String keyword) {
        if (tasks.isEmpty()) {
            return "You have no tasks!\n";
        }

        StringBuilder taskListMessage = new StringBuilder("I present to you, your collection of tasks!\n\n");

        for (int i = 0; i < tasks.size(); i++) {
            int taskNum = i + 1;
            String task = "\t" + taskNum + ". " + tasks.get(i);
            if (task.contains(keyword)) {
                taskListMessage.append(task).append("\n");
            }
        }

        return taskListMessage.toString();
    }

    /**
     * Searches for tasks.
     *
     * @param command find command typed by the user
     * @return the tasks matching the keywords provided by the user
     * @throws DukeException no keyword specified
     */
    public static String findTasks(String command) throws DukeException {
        if (command.trim().equals("find")) {
            throw new DukeException("You need to specify a keyword!");
        }

        String keyword = command.split(" ")[1];
        return printTasks(keyword);
    }
}
