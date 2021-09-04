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

    private final List<Task> tasks;
    private final Calendar calendar;

    /**
     * Constructs a TaskList instance
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        calendar = new Calendar();
    }

    /**
     * Adds the list of previously saved tasks to the list of tasks
     *
     * @param addTasks The arraylist of previously saved tasks
     */
    public void addSavedTasks(ArrayList<Task> addTasks) {
        tasks.addAll(addTasks);
    }

    /**
     * Adds a task to the list of tasks
     *
     * @param task The task to be added
     */
    public String addTask(Task task) {
        tasks.add(task);
        String plural = (tasks.size() + 1) > 0 ? " tasks!" : " task!";
        return "Okay. I've added this task:\n" + task + "\nYou now have " + tasks.size() + plural;
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
            Task toBeDeleted = tasks.remove(indexOfTask);
            if (toBeDeleted.isTimeable()) {
                calendar.updateCalendar(tasks);
            }
            return "Okay. I've deleted this task:\n" + toBeDeleted + "\nYou now have " + tasks.size()
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
            Task taskToBeCompleted = tasks.get(Integer.parseInt(indexOfTask) - 1);
            return taskToBeCompleted.taskDone();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException(Responses.INVALID_TASK_NO);
        }
    }

    /**
     * Returns a string representation of the tasks
     *
     * @return String containing the number, type, and description of tasks
     */
    public String printList() {
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
        keyword = keyword.trim();
        int count = 1;
        StringBuilder result = new StringBuilder("Here are the tasks:");
        for (Task t : tasks) {
            if (t.isKeyWordPresent(keyword)) {
                result.append('\n').append(count).append(". ").append(t);
                count += 1;
            }
        }
        if (count == 1) { //No tasks appended
            return "No tasks!";
        }
        return result.toString();
    }

    /**
     * Returns a formatted string representation of the list of tasks that can be used for saving
     *
     * @return Formatted string representation of all the user-added tasks
     */
    public String formatForSaving() {
        int count = 1;
        StringBuilder result = new StringBuilder();
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
