package sora.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import sora.exception.EmptyListException;
import sora.exception.EmptyResultException;
import sora.exception.IllegalFormatException;
import sora.exception.TaskNotFoundException;
import sora.util.Message;

/**
 * Represents a list of tasks.
 *
 * @author Zhang Shi Chen
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /** Constructor for TaskList. */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Overloading constructor for TaskList.
     * Allows user to input a list of tasks.
     *
     * @param tasks An existing list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Sorts tasks in the list by date and time.
     * Since todo does not have a time, it will always be behind.
     * This method compares the date and time of deadline vs date and start time of event.
     *
     * @param command Command entered by user (sort [-r])
     * @return String representation of the list of sorted tasks
     * @throws EmptyListException If number of tasks in the list is 0
     * @throws IllegalFormatException If user inputs an invalid command
     */
    public String sort(String command) throws EmptyListException, IllegalFormatException {
        // Throw exception if command does not follow format
        validateCommand(command, "^sort( -r)?", "sort [-r]");

        // Sort the tasks by date
        ArrayList<Task> sortedTasks = new ArrayList<>(tasks);
        sortedTasks.sort(Task::compareTo);

        // If user provides the -r flag, reverse the sorting
        if (command.length() > 4) {
            Collections.reverse(sortedTasks);
        }

        return printList(sortedTasks);
    }

    /**
     * Deletes the task with the same task number specified by user.
     *
     * @param command Command entered by user (delete [task number])
     * @return Confirmation message for successfully deleted a task
     * @throws IllegalFormatException If user gives empty or invalid task number
     * @throws TaskNotFoundException If the task specified by the task number does not exist
     */
    public String taskDelete(String command) throws IllegalFormatException, TaskNotFoundException {
        // Throw exception if user gives empty or non-integer task number
        validateCommand(command, "^delete [0-9]+", "delete [task number]");

        int taskNumber = extractTaskNumber(command, 7);

        // Delete the task
        Task task = tasks.remove(taskNumber);

        int size = tasks.size();

        // Return confirmation message
        return Message.DELETE + "\n  "
                + task + "\n" + (
                size == 0
                ? "You have no more tasks in the list!"
                : size == 1
                  ? "You still have 1 task in the list."
                  : "You still have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Marks the task with the same task number specified by user as done.
     *
     * @param command Command entered by user (done [task number])
     * @return Confirmation message for successfully marked a task as done
     * @throws IllegalFormatException If user gives empty or invalid task number
     * @throws TaskNotFoundException If the task specified by the task number does not exist
     */
    public String taskDone(String command) throws IllegalFormatException, TaskNotFoundException {
        // Throw exception if user gives empty or non-integer task number
        validateCommand(command, "^done [0-9]+", "done [task number]");

        int taskNumber = extractTaskNumber(command, 5);

        // Mark the task as done
        Task task = tasks.get(taskNumber);
        task.markAsDone();

        // Return confirmation message
        return Message.DONE + "\n  " + task;
    }

    private int extractTaskNumber(String command, int prefixLength) throws TaskNotFoundException {
        int taskNumber;

        try {
            taskNumber = Integer.parseInt(command.substring(prefixLength)) - 1;
        } catch (NumberFormatException e) {
            throw new TaskNotFoundException();
        }

        // Throw exception if taskNumber is out of range
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new TaskNotFoundException();
        }

        return taskNumber;
    }

    /**
     * Adds a new event to the list of tasks.
     *
     * @param command Command entered by user (event [description] /at [dd/MM/yy] /from [HHmm] /to [HHmm])
     * @return Confirmation message for successfully added a new event
     * @throws IllegalFormatException If user inputs an invalid command
     */
    public String addEvent(String command) throws IllegalFormatException {
        String correctFormat = "event [description] /at [dd/MM/yy] /from [HHmm] /to [HHmm]";

        // Throw exception if command does not follow format
        validateCommand(command, "^event .* /at .* /from .* /to .*", correctFormat);

        // Separate info and trim each of them
        String[] info = command.substring(6).split("/at|/from|/to");
        info = Arrays.stream(info)
                .map(String::trim)
                .toArray(String[]::new);

        LocalDate date;
        LocalTime startTime, endTime;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("Hmm");

        // Throw exception if command does not follow format
        try {
            date = LocalDate.parse(info[1], DateTimeFormatter.ofPattern("d/M/yy"));
            startTime = LocalTime.parse(info[2], timeFormatter);
            endTime = LocalTime.parse(info[3], timeFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalFormatException(correctFormat);
        }

        // Throw exception if start time is later than end time
        if (startTime.isAfter(endTime)) {
            throw new IllegalFormatException(correctFormat);
        }

        // Add new event into list of tasks
        Task newTask = new Event(info[0], date, startTime, endTime);
        tasks.add(newTask);

        // Return confirmation message
        return formatAddMessage(newTask, tasks.size());
    }

    /**
     * Adds a new deadline to the list of tasks.
     *
     * @param command Command entered by user (deadline [description] /by [dd/MM/yy] [HHmm])
     * @return Confirmation message for successfully added a new deadline
     * @throws IllegalFormatException If user inputs an invalid command
     */
    public String addDeadline(String command) throws IllegalFormatException {
        String correctFormat = "deadline [description] /by [dd/MM/yy] [HHmm]";

        // Throw exception if command does not follow format
        validateCommand(command, "^deadline .* /by .*", correctFormat);

        // Separate info and trim each of them
        String[] info = command.substring(9).split("/by");
        info = Arrays.stream(info)
                .map(String::trim)
                .toArray(String[]::new);

        LocalDateTime dateTime;

        // Throw exception if command does not follow format
        try {
            dateTime = LocalDateTime.parse(info[1], DateTimeFormatter.ofPattern("d/M/yy Hmm"));
        } catch (DateTimeParseException e) {
            throw new IllegalFormatException(correctFormat);
        }

        // Add new deadline into list of tasks
        Task newTask = new Deadline(info[0], dateTime);
        tasks.add(newTask);

        // Return confirmation message
        return formatAddMessage(newTask, tasks.size());
    }

    /**
     * Adds a new todo to the list of tasks.
     *
     * @param command Command entered by user (todo [description])
     * @return Confirmation message for successfully added a new todo
     * @throws IllegalFormatException If user inputs an invalid command
     */
    public String addTodo(String command) throws IllegalFormatException {
        // Throw exception if command does not follow format
        validateCommand(command, "^todo .*", "todo [description]");

        // Add new todo into list of tasks
        Task newTask = new Todo(command.substring(5).trim());
        tasks.add(newTask);

        // Return confirmation message
        return formatAddMessage(newTask, tasks.size());
    }

    private String formatAddMessage(Task task, int size) {
        return Message.ADD + "\n  "
                + task + "\n"
                + "Now you have " + size + " task" + (size == 1 ? "" : "s") + " in the list.";
    }

    /**
     * Prints only the tasks in the list that contains the keyword in their descriptions;
     * or task of the same type as keyword (e.g. keyword = "event").
     *
     * @param command Command entered by user (find [keyword])
     * @return String representation of the list of matched tasks
     * @throws EmptyListException If number of tasks to print is empty
     * @throws EmptyResultException If number of matched tasks in the list is 0
     * @throws IllegalFormatException If user inputs an invalid command
     */
    public String findInList(String command) throws EmptyResultException, IllegalFormatException, EmptyListException {
        // Throw exception if command does not follow format
        validateCommand(command, "^find .*", "find [keyword]");

        String keyword = command.substring(5).trim().toLowerCase();
        List<Task> filteredTasks = tasks.stream()
                .filter(task -> task.matchesKeyword(keyword))
                .collect(Collectors.toList());

        // Throw exception is search result is empty
        if (filteredTasks.isEmpty()) {
            throw new EmptyResultException();
        }

        return printList(filteredTasks);
    }

    /**
     * Prints all the tasks in the list.
     *
     * @return String representation of the list of tasks
     * @throws EmptyListException If the list of tasks is empty
     */
    public String printFullList() throws EmptyListException {
        return printList(tasks);
    }

    private String printList(List<Task> tasks) throws EmptyListException {
        int size = tasks.size();

        // Throw exception if list is empty
        if (size == 0) {
            throw new EmptyListException();
        }

        // Reformat the list of tasks into a string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(tasks.get(i));

            // Last task is special as it does not need the '\n'
            if (i + 1 != size) {
                sb.append("\n");
            }
        }

        // Return string representation of the list of tasks
        return Message.LIST + "\n" + sb;
    }

    private void validateCommand(String command, String regex, String format) throws IllegalFormatException {
        if (!command.matches(regex)) {
            throw new IllegalFormatException(format);
        }
    }
}
