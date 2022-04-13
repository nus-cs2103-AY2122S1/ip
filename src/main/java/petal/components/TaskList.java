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

    public enum Action {
        TASK_ADD,
        TASK_DELETE,
        TASK_ARCHIVE
    }

    /**
     * Constructs a TaskList instance
     */
    public TaskList() {
        currentTasks = new ArrayList<>();
        calendar = new Calendar();
        archivedTasks = new ArrayList<>();
    }

    /**
     * Adds the list of previously saved tasks to the list of tasks
     *
     * @param addTasks The arraylist of previously saved tasks
     */
    @SafeVarargs
    protected final void addTasks(ArrayList<Task>... addTasks) {
        currentTasks.addAll(addTasks[0]);
        archivedTasks.addAll(addTasks[1]);
    }

    /**
     * Handles the task, depending on the command given
     *
     * @param type The type of task: To.Do, deadline, event
     * @throws EmptyDescException Thrown when the task lacks a description
     * @throws InvalidInputException Thrown when an invalid format is given or when a time is not given
     */
    public String handleTask(String type, String description) throws EmptyDescException, InvalidInputException {
        String[] deadlineEvent = (type.equals("deadline"))
                ? description.split("/by")
                : description.split("/at");
        checkIfValidFormat(type, description, deadlineEvent);
        if (type.equals("todo")) {
            return handleToDo(description);
        } else {
            return handleTimeable(type, deadlineEvent);
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
    private void checkIfValidFormat(String type, String message, String[] deadlineEvent) throws EmptyDescException,
            InvalidInputException {
        boolean emptyDesc = message.isBlank() || deadlineEvent[0].isBlank();
        boolean invalidTimeableFormat = (type.equals("deadline") || type.equals("event")) && deadlineEvent.length < 2;

        if (emptyDesc) {
            throw new EmptyDescException(Responses.EMPTY_DESCRIPTION);
        }
        if (invalidTimeableFormat) {
            throw new InvalidInputException(Responses.INVALID_FORMAT);
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
        return addTask(todo, false);
    }

    /**
     * Handles a Timeable (Deadline or Event)

     * @param type The type of task (Deadline or Event)
     * @param deadlineEvent The array that contains the description and date of the task
     * @return The string representing that the task was added
     */
    private String handleTimeable(String type, String[] deadlineEvent) throws InvalidInputException {
        try {
            Task timeable = (type.equals("deadline"))
                    ? new Deadline(deadlineEvent[0], deadlineEvent[1], false)
                    : new Event(deadlineEvent[0], deadlineEvent[1], false);
            return addTask(timeable, true);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputException(Responses.INVALID_DATE_TIME);
        }
    }

    /**
     * Adds a task to the list of tasks
     *
     * @param task The task to be added
     */
    private String addTask(Task task, boolean isTimeable) {
        if (isTimeable) {
            calendar.addToCalendar((Timeable) task);
        }
        currentTasks.add(task);
        return replyForTaskActions(task, Action.TASK_ADD);
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

            return replyForTaskActions(toBeDeleted, Action.TASK_DELETE);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException(Responses.INVALID_TASK_NUMBER, e);
        }
    }

    /**
     * Adds the task to the list of archived task
     *
     * @param index The index of the index
     * @throws InvalidInputException If index is invalid
     */
    public String archiveTask(String index) throws InvalidInputException {
        try {
            int indexOfTask = Integer.parseInt(index) - 1;
            Task toArchive = currentTasks.get(indexOfTask);

            deleteTask(index);
            archivedTasks.add(toArchive);

            return replyForTaskActions(toArchive, Action.TASK_ARCHIVE);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException(Responses.INVALID_TASK_NO);
        }
    }

    /**
     * Returns the reply for a certain action relating to a task has been
     * completed
     *
     * @param task The task the action modified
     * @param typeOfAction The type of action, e.g. add/delete/archive
     * @return String, which represents the reply
     */
    public String replyForTaskActions(Task task, Action typeOfAction) {
        String reply = "";
        String pluralCurrTasks = (currentTasks.size()) != 1 ? " tasks!" : " task!";
        String pluralArchivedTasks = (archivedTasks.size()) > 1
                ? " tasks in your archives!"
                : " task in your archives!";
        switch (typeOfAction) {
        case TASK_ADD:
            reply = "Okay. I've added this task:\n" + task + "\nYou now have " + currentTasks.size() + pluralCurrTasks;
            break;
        case TASK_DELETE:
            reply = "Okay. I've deleted this task:\n" + task + "\nYou now have " + currentTasks.size()
                    + pluralCurrTasks;
            break;
        case TASK_ARCHIVE:
            reply = "Okay. I've added this task:\n" + task
                    + "\nYou now have " + archivedTasks.size() + pluralArchivedTasks;
            break;
        default: //All cases handled
        }
        return reply;
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
            int index = Integer.parseInt(indexOfTask) - 1;
            Task taskToBeCompleted = currentTasks.get(index);
            String taskDoneMessage = taskToBeCompleted.taskDone();
            return taskDoneMessage;
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
     * e.g 1. [T][ ] Go for a run etc
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
                             .forEach(x -> result.append('\n')
                                                 .append(count[0]++)
                                                 .append(". ")
                                                 .append(x));
        if (count[0] == 1) {
            return "No tasks!";
        }
        return result.toString();
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
     * Returns the string representation of the current tasks for saving
     *
     * @return Formatted string representation for saving
     */
    public String formatForArchivesSaving() {
        return formatTasksForSaving(archivedTasks);
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
