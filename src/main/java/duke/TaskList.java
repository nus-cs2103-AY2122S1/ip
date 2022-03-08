package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Stores tasks and allows for operations to be performed on them.
 */
public class TaskList {
    /** The list of tasks. */
    private final ArrayList<Task> USER_TASKS;

    /**
     * Constructs a new instance of the TaskList with no tasks inside.
     */
    public TaskList() {
        this.USER_TASKS = new ArrayList<>();
    }

    /**
     * Constructs a new instance of the TaskList with the specified tasks inside.
     * Specified tasks come in the format of a stream of strings, the data of which are cleaned.
     *
     * @param tasks The stream of strings which represent the data of each task.
     */
    public TaskList(Stream<String> tasks) {
        this.USER_TASKS = new ArrayList<>();
        tasks.forEach(x -> USER_TASKS.add(cleanData(x)));
    }

    /**
     * Gets the list of tasks as a string array.
     * Labels the tasks in addition to their string representation.
     *
     * @return A array of strings representing the list of tasks, one element for one task.
     */
    public String[] getStringArr() {
        String[] tasks = new String[USER_TASKS.size() + 1];
        for (int i = 0; i < USER_TASKS.size(); i++) {
            int tempNum = i + 1;
            tasks[tempNum] = tempNum + ". " + USER_TASKS.get(i);
        }
        return tasks;
    }

    /**
     * Marks a task as done.
     * Gets the task based on the index passed to the method.
     *
     * @param index The index of the task to be marked as done.
     * @return The string representation of the task marked as done.
     */
    public String markAsDone(int index) {
        USER_TASKS.get(index).markAsDone();
        return "  " + USER_TASKS.get(index).toString();
    }

    /**
     * Removes a task from the task list.
     * Gets the task based on the index passed to the method.
     *
     * @param index The index of the task to be marked as done.
     * @return The string representation of the task deleted.
     */
    public String removeTask(int index) {
        String res = USER_TASKS.get(index).toString();
        USER_TASKS.remove(index);
        return "  " + res;
    }

    /**
     * Adds a new ToDo task to the task list.
     * Gets the name of the ToDo task based on the string passed to the method.
     *
     * @param name The name of the ToDo task.
     * @return The string representation of the ToDo task added.
     */
    public String addToDo(String name) {
        USER_TASKS.add(new ToDo(name));
        return "  " + USER_TASKS.get(USER_TASKS.size() - 1).toString();
    }

    /**
     * Adds a new Deadline task to the task list.
     * Gets the name of the deadline task and the date and time it is due from the strings passed to the method.
     *
     * @param name The name of the Deadline task.
     * @param due The date and time the Deadline task is due.
     * @return The string representation of the Deadline task added.
     */
    public String addDeadline(String name, String due) {
        USER_TASKS.add(new Deadline(name, due));
        return "  " + USER_TASKS.get(USER_TASKS.size() - 1).toString();
    }

    /**
     * Adds a new Event task to the task list.
     * Gets the name of the event task and the time it happens from the strings passed to the method.
     *
     * @param name The name of the Event task.
     * @param time The time the Event task happens.
     * @return The string representation of the Event task added.
     */
    public String addEvent(String name, String time) {
        USER_TASKS.add(new Event(name, time));
        return "  " + USER_TASKS.get(USER_TASKS.size() - 1).toString();
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public String numTasks() {
        return "Now you have " + USER_TASKS.size() + " tasks in the list.";
    }

    /**
     * Converts the tasks in the task list into the proper format to be saved.
     * Each task is converted to their save format and added to the final string to be returned.
     *
     * @return A string representing all the tasks in the task list in the proper save format.
     */
    public String convertToSaveFormat() {
        String dataStr = "";
        if (USER_TASKS.size() == 0) {
            return dataStr;
        }
        for (int i = 0; i < USER_TASKS.size() - 1; i++) {
            Task data = USER_TASKS.get(i);
            dataStr += data.convertToSaveFormat() + "\n";
        }
        dataStr += USER_TASKS.get(USER_TASKS.size() - 1).convertToSaveFormat();
        return dataStr;
    }

    /**
     * Finds a list of tasks which contain the specified search string.
     * Converts the ArrayList to a stream to perform filtering and mapping operations.
     *
     * @param textToSearch The text which to search for in the names of the tasks.
     * @return A list containing the string representations of tasks which contain the specified search string.
     */
    public List<String> searchTasks(String textToSearch) {
        return USER_TASKS.stream().filter(task -> task.getName().contains(textToSearch))
                .map(Task::toString).collect(Collectors.toList());
    }

    /**
     * Cleans the data to get the required task information.
     * Handles each task differently based on their type. The data comes from the save format in
     * the storage file on the user's computer.
     *
     * @param data The data to be cleaned.
     * @return A task based on the data given.
     */
    private Task cleanData(String data) {
        String[] dataParts = data.split("[|]");
        assert dataParts.length > 2 : "Your task data has been corrupted.";
        Task tempTask = new ToDo("");
        if (dataParts.length == 3) {
            tempTask = new ToDo(dataParts[2]);
        } else if (dataParts[0].equals("D")) {
            tempTask = new Deadline(dataParts[2], dataParts[3]);
        } else if (dataParts[0].equals("E")) {
            tempTask = new Event(dataParts[2], dataParts[3]);
        }

        if (dataParts[1].equals("1")) {
            tempTask.markAsDone();
        }

        assert !tempTask.toString().equals(new ToDo("").toString()) : "Could not get task from data";
        return tempTask;
    }
}
