package petal.components;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import petal.exception.EmptyDescException;
import petal.exception.InvalidInputException;
import petal.task.Deadline;
import petal.task.Event;
import petal.task.Task;
import petal.task.Timeable;
import petal.task.ToDo;

/**
 * The TaskList class represents the tasks, and handles the operations
 * done to the tasks
 */
public class TaskList {

    private final List<Task> currentTasks;
    private final List<Task> archivedTasks;
    private final Calendar calendar;

    /**
     * Constructs a TaskList instance
     */
    public TaskList() {
        this.currentTasks = new ArrayList<>();
        this.calendar = new Calendar();
        this.archivedTasks = new ArrayList<>();
    }

    /**
     * Adds the list of previously saved tasks to the list of tasks
     *
     * @param addTasks The arraylist of previously saved tasks
     */
    @SafeVarargs
    public final void addTasks(ArrayList<Task>... addTasks) {
        currentTasks.addAll(addTasks[0]);
        archivedTasks.addAll(addTasks[1]);
    }

    /**
     * Adds a task to the list of tasks
     *
     * @param task The task to be added
     */
    public String addTask(Task task) {
        currentTasks.add(task);
        String plural = (currentTasks.size() + 1) > 0 ? " tasks!" : " task!";
        return "Okay. I've added this task:\n" + task + "\nYou now have " + currentTasks.size() + plural;
    }

    /**
     * Deletes a task from the list of tasks
     *
     * @param index The message given by the user input
     * @throws InvalidInputException Thrown if no index inputted by the user or
     *                               when index is out-of-bounds/not valid int or when
     *                               desc is empty
     */
    public String deleteTask(String index) throws InvalidInputException {
        try {
            int indexOfTask = Integer.parseInt(index) - 1;
            Task toBeDeleted = currentTasks.remove(indexOfTask);
            if (toBeDeleted.isTimeable()) {
                calendar.updateCalendar(currentTasks);
            }
            return "Okay. I've deleted this task:\n" + toBeDeleted + "\nYou now have " + currentTasks.size()
                    + " task(s)!";
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException(Responses.INVALID_TASK_NUMBER, e);
        }
    }

    /**
     * Handles the task, depending on the command given
     *
     * @param type The type of task: To.Do, deadline, event
     * @throws EmptyDescException Thrown when the task lacks a description
     * @throws InvalidInputException Thrown when an invalid format is given or when a time is not given
     */
    public String handleTask(String type, String description) throws EmptyDescException, InvalidInputException {
        String[] deadlineEvent = (type.equals("deadline")) ? description.split("/by")
                                                           : description.split("/at");
        checkIfValidFormat(type, description, deadlineEvent);

        if (type.equals("todo")) {
            return handleToDo(description);
        } else {
            return handleTimeable(type, deadlineEvent);
        }
    }

    /**
     * Handles a To.Do task
     *
     * @param message The description
     * @return The string representing that the task was added
     */
    private String handleToDo(String message) {
        Task todo = new ToDo(message, false);
        return addTask(todo);
    }

    /**
     * Handles a Timeable (Deadline or Event)
     *
     * @param type The type of task (Deadline or Event)
     * @param deadlineEvent The array that contains the description and date of the task
     * @return The string representing that the task was added
     */
    private String handleTimeable(String type, String[] deadlineEvent) throws InvalidInputException {
        try {
            Task timeable = (type.equals("deadline")) ? new Deadline(deadlineEvent[0], deadlineEvent[1], false)
                                                      : new Event(deadlineEvent[0], deadlineEvent[1], false);
            calendar.addToCalendar((Timeable) timeable);
            return addTask(timeable);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputException(Responses.INVALID_DATE_TIME);
        }
    }

    /**
     * Adds the task to the list of archived task
     *
     * @param index The index of the index
     * @throws InvalidInputException If index is invalid
     */
    public void archiveTask(String index) throws InvalidInputException {
        try {
            int indexOfTask = Integer.parseInt(index) - 1;
            Task toArchive = currentTasks.get(indexOfTask);
            deleteTask(index);
            archivedTasks.add(toArchive);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException(Responses.INVALID_TASK_NO);
        }
    }

    /**
     * Checks if the task command was given in the correct format
     *
     * @param type The type of task
     * @param message The description of task
     * @param deadlineEvent The array with the description/date (only for Timeable)
     * @throws EmptyDescException Thrown if desc is empty
     * @throws InvalidInputException Thrown if other components are missing from format
     */
    public void checkIfValidFormat(String type, String message, String[] deadlineEvent) throws EmptyDescException,
            InvalidInputException {
        if (message.isBlank() || deadlineEvent[0].isBlank()) {
            throw new EmptyDescException(Responses.EMPTY_DESCRIPTION);
        }
        if ((type.equals("deadline") || type.equals("event")) && deadlineEvent.length < 2) {
            throw new InvalidInputException(Responses.INVALID_FORMAT);
        }
    }

    /**
     * Marks a particular task as done
     *
     * @param indexOfTask String representation of the index of the task
     * @throws IndexOutOfBoundsException Thrown if string is not within size of list
     * @throws NumberFormatException Thrown if string cannot be converted into valid int
     */
    public String markTaskAsDone(String indexOfTask) throws InvalidInputException {
        try {
            Task taskToBeCompleted = currentTasks.get(Integer.parseInt(indexOfTask) - 1);
            return taskToBeCompleted.taskDone();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException(Responses.INVALID_TASK_NO);
        }
    }

    /**
     * Returns a string representation of the current tasks
     *
     * @return String containing the number, type, and description of the saved tasks
     */
    public String printCurrTasks() {
        return printListOfTasks(currentTasks);
    }

    /**
     * Returns a string representation of the archived tasks
     *
     * @return String containing, the number, type, and description of the archived tasks
     */
    public String printArchive() {
        return printListOfTasks(archivedTasks);
    }

    /**
     * Returns a string representation of the tasks in the given list
     *
     * @param tasks The given list of tasks
     * @return String containing, the number, type, and description of the archived tasks
     */
    public static String printListOfTasks(List<Task> tasks) {
        if (tasks.size() == 0) {
            return "No items in list yet!";
        }
        int count = 1;
        StringBuilder list = new StringBuilder();
        for (Task t : tasks) {
            if (count == 1) {
                list.append(count++).append(". ").append(t);
            } else {
                list.append("\n").append(count++).append(". ").append(t);
            }
        }
        return list.toString();
    }

    /**
     * Displays the tasks on the given date
     *
     * @param date The date given
     * @throws InvalidInputException Thrown if the parameter has an invalid format/input
     */
    public String showTaskOnDate(String date) throws InvalidInputException {
        return calendar.showTasksOnDate(Parser.parseDate(date));
    }

    /**
     * Returns string of tasks that contain the keyword
     *
     * @param keyword The keyword to be found
     */
    public String findTaskWithKeyword(String keyword) throws InvalidInputException {
        final int[] count = {1};
        StringBuilder result = new StringBuilder("Here are the tasks:");
        currentTasks.stream().filter(x -> x.isKeyWordPresent(keyword))
                .forEach(x -> result.append('\n').append(count[0]++).append(". ").append(x));
        if (count[0] == 1) {
            return "No tasks!";
        }
        return result.toString();
    }

    /**
     * Returns the string representation of the current tasks for saving
     *
     * @return Formatted string representation for saving
     */
    public String formatForArchivesSaving() {
        return formatTasksForSaving(archivedTasks);
    }

    /**
     * Returns a formatted string representation of the archived tasks for saving
     *
     * @return Formatted string representation for saving
     */
    public String formatForCurrSaving() {
        return formatTasksForSaving(currentTasks);
    }

    /**
     * Returns the string representation of the tasks in the list
     *
     * @param tasks The list of tasks
     * @return Formatted string representation for saving
     */
    private String formatTasksForSaving(List<Task> tasks) {
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (Task m : tasks) {
            if (count == 1) {
                result.append(m.formatStrForSaving());
            } else {
                result.append("\n").append(m.formatStrForSaving());
            }
            count++;
        }
        return result.toString();
    }

}
