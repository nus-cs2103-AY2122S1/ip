package service;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskManager class.
 *
 * This class acts as the manager of tasks held by the Duke.
 */
public class TaskManager {

    private final static int MAX_STORAGE = 100;

    private final List<Task> TASK_LIST = new ArrayList<>();

    /**
     * Gets the current number of tasks stored.
     *
     * @return number of tasks stored currently
     */
    public int getTaskListSize() {
        return TASK_LIST.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param newTask task to save
     * @return saved task
     * @throws DukeException if task cannot be saved, due to full capacity of task list
     */
    public Task addTask(Task newTask) throws DukeException {
        if (TASK_LIST.size() == MAX_STORAGE) {
            throw new DukeException("Unable to add task as list is full.");
        }
        TASK_LIST.add(newTask);
        return newTask;
    }

    /**
     * Adds a Todo Task.
     *
     * @param userInput user input with parameters for a Deadline
     * @return Todo Task saved
     * @throws DukeException if parsing is incorrect, task is not present,
     * or task list is full
     */
    public Task addToDoTask(String userInput) throws DukeException {
        String description = userInput.trim();
        if (description.isEmpty()) {
            throw new DukeException("Task description cannot be empty.");
        }
        Todo todo = new Todo(description);
        return addTask(todo);
    }

    /**
     * Adds an Event Task.
     *
     * @param userInput user input with parameters for a Deadline
     * @return Event Task saved
     * @throws DukeException if parsing is incorrect, task is not present,
     * or task list is full
     */
    public Task addEventTask(String userInput) throws DukeException {
        String[] parameterArray = splitUserInput(Event.SPLITTER, userInput);
        Event event = new Event(parameterArray[0], parameterArray[1]); // desc, timing
        return addTask(event);
    }

    /**
     * Adds a Deadline Task.
     *
     * @param userInput user input with parameters for a Deadline
     * @return Deadline Task saved
     * @throws DukeException if parsing is incorrect, task is not present,
     * or task list is full
     */
    public Task addDeadlineTask(String userInput) throws DukeException {
        String[] parameterArray = splitUserInput(Deadline.SPLITTER, userInput);
        Deadline deadline = new Deadline(parameterArray[0], parameterArray[1]); // desc, by
        return addTask(deadline);
    }

    /**
     * Splits the user input based on the splitKey into a String array.
     *
     * @param splitKey key String to split userInput with
     * @param userInput user input to parse into the relevant parameters
     * @return String array containing description of Task as well as other parameters
     * @throws DukeException if splitKey is not present in userInput,
     * or if either parameter is empty
     */
    public String[] splitUserInput(String splitKey, String userInput)
            throws DukeException {

        if (!userInput.contains(splitKey)) {
            throw new DukeException("Missing key '" + splitKey + "' in command.");
        }

        String[] inputParts = userInput.split(splitKey);
        if (inputParts.length != 2) {
            throw new DukeException("Ensure input has both parameters for task.");
        }

        String description = inputParts[0].trim();
        if (description.isEmpty()) {
            throw new DukeException("Task description cannot be empty.");
        }

        String split = inputParts[1].trim();
        if (split.isEmpty()) {
            throw new DukeException("Parameter after '" + splitKey + "' cannot be empty.");
        }
        return new String[] {description, split};
    }

    /**
     * Marks a Task completed based on the input Task number fed as a String.
     *
     * @param taskNumberString String format of the Task number to delete
     * @return completed Task
     * @throws DukeException if String cannot be parsed into an integer or
     * the Task number is not valid
     */
    public Task markTaskAsDone(String taskNumberString) throws DukeException {
        Task selectedTask = getTaskFromNumberString(taskNumberString);
        selectedTask.markAsDone();
        return selectedTask;
    }

    /**
     * Deletes a Task based on the input Task number fed as a String.
     *
     * @param taskNumberString String format of the Task number to delete
     * @return Task to delete
     * @throws DukeException if String cannot be parsed into an integer or
     * the Task number is not valid
     */
    public Task deleteTask(String taskNumberString) throws DukeException {
        Task selectedTask = getTaskFromNumberString(taskNumberString);
        TASK_LIST.remove(selectedTask); // remove shifts tasks to the right backwards
        return selectedTask;
    }

    /**
     * Gets the Task based on the input Task number fed as a String.
     *
     * @param taskNumberString String format of the Task number to obtain
     * @return associated Task
     * @throws DukeException if String cannot be parsed into an integer or
     * the Task number is not valid
     */
    public Task getTaskFromNumberString(String taskNumberString) throws DukeException {
        if (TASK_LIST.isEmpty()) {
            throw new DukeException("Unable to perform action as list is empty.");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskNumberString);
            if (taskNumber < 0 || TASK_LIST.size() < taskNumber) {
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException exception) {
            throw new DukeException("'" + taskNumberString + "' is not an integer.");

        } catch (IllegalArgumentException exception) {
            throw new DukeException("Task number '" + taskNumberString + "' is invalid.");
        }

        return TASK_LIST.get(taskNumber - 1); // shift to 0-indexing
    }

    /**
     * Provides the Tasks in a formatted List of Strings, to be fed to a text printer.
     *
     * @return formatted tasks in a list of strings
     */
    public List<String> listTasks() {
        List<String> taskManagerStringList = new ArrayList<>();
        taskManagerStringList.add("Here are the tasks in your list:");
        for (int idx = 0; idx < TASK_LIST.size(); idx ++) {
            Task task = TASK_LIST.get(idx);
            int taskNumber = idx + 1; // shift to 1-indexing
            taskManagerStringList.add(String.format("%d. %s", taskNumber, task));
        }
        if (TASK_LIST.isEmpty()) {
            taskManagerStringList.add("-- Empty --");
        }
        return taskManagerStringList;
    }
}
