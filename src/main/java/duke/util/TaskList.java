package duke.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import duke.exception.DukeException;
import duke.exception.DukeIoException;
import duke.exception.DukeNegativeIndexException;
import duke.exception.InvalidIndexException;
import duke.task.Task;

/**
 * This class encapsulates a List used by Duke to keep track of tasks.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class TaskList {
    private static final String COMPLETE_TASK_MESSAGE = "Good job on completing this task!";
    private static final String ERROR_TASK_ALREADY_CREATED =
            "You have already added this task before! See what tasks you have by using the 'list' command.";
    private static final String ERROR_MULTIPLE_TODOS_TASK_ALREADY_CREATED =
            "There is a task that you have already added before! See what tasks you have by using the "
                    + "'list' command.";

    private final ArrayList<Task> list;
    private final DataManager dataManager;
    // @@author {zhuhangming}-reused
    private final HashMap<String, Boolean> descriptions;

    /** Instantiates a new To do list. */
    public TaskList(ArrayList<Task> list, DataManager dataManager) {
        this.list = list;
        this.dataManager = dataManager;
        this.descriptions = new HashMap<>();

        for (Task t : list) {
            descriptions.put(t.toString().toLowerCase(), true);
        }
    }

    /** Gets the list of items that the user entered. */
    public String printList() {
        return Ui.printList(list);
    }

    /**
     * Adds user input to list and pretty prints a visual feedback.
     *
     * @param tasks Tasks/task to be stored into the list.
     * @return string to be printed out.
     */
    public String addToList(Task... tasks) {
        if (tasks.length > 1) {
            return handleMultipleAddTask(tasks);
        }

        return handleSingleAddTask(tasks[0]);
    }

    private String handleSingleAddTask(Task task) {
        // @@author {zhuhangming}-reused
        boolean isDuplicated = this.descriptions.containsKey(task.toString().toLowerCase());
        if (isDuplicated) {
            return ERROR_TASK_ALREADY_CREATED;
        }

        list.add(task);
        return String.format("Got it. I've added this task:" + Ui.LINE_SEPARATOR
                        + "  %sNow you have %s tasks in the list.",
                task + Ui.LINE_SEPARATOR, list.size());
    }

    private String handleMultipleAddTask(Task[] tasks) {
        assert tasks.length >= 2 : "There should be at least 2 tasks entered by the user.";
        StringBuilder sb = new StringBuilder("Got it. I've added these tasks:\n");
        for (Task t : tasks) {
            // @@author {zhuhangming}-reused
            if (this.descriptions.containsKey(t.toString().toLowerCase())) {
                return ERROR_MULTIPLE_TODOS_TASK_ALREADY_CREATED;
            }

            list.add(t);
            sb.append("  ").append(t).append("\n");
        }
        sb.append("Now you have ").append(list.size()).append(" tasks in the list.");
        return sb.toString();
    }

    /**
     * Marks the task at index specified to be done.
     *
     * @param index Index of task to be marked as done.
     * @return string to be printed out.
     * @throws InvalidIndexException if user inputs a number larger than number of tasks currently in list.
     */
    public String markTaskAsDone(int index) throws DukeException {
        if (index > list.size()) { // Guard Clause
            throw new InvalidIndexException(list.size());
        }

        if (index < 0) { // Guard Clause
            throw new DukeNegativeIndexException();
        }

        Task task = list.get(index - 1);
        task.markAsDone();
        return String.format(COMPLETE_TASK_MESSAGE + "\n  %s", task);
    }

    /**
     * Removes the task at index specified from the list.
     *
     * @param index Index of task to be removed.
     * @throws InvalidIndexException if user inputs a number larger than number of tasks currently in list.
     * @throws DukeNegativeIndexException if user inputs a negative number.
     */
    public String removeFromList(int index) throws DukeException {
        if (index > list.size()) { // Guard Clause
            throw new InvalidIndexException(list.size());
        }

        if (index < 0) { // Guard Clause
            throw new DukeNegativeIndexException();
        }

        Task task = list.get(index - 1);
        list.remove(index - 1);
        return String.format("Noted. I've removed this task:\n  %sNow you have %s tasks in the list.",
                        task + Ui.LINE_SEPARATOR, list.size());
    }

    /**
     * Updates the persisted storage with the current state of the list.
     *
     * @throws DukeIoException if there is error writing to the storage file.
     */
    public void updateData() throws DukeException {
        dataManager.updateData(list);
    }

    /**
     * Returns an ArrayList with tasks that matches date and time specified by user.
     *
     * @param dateTime the date and time to filter the tasks with.
     * @return ArrayList containing task that matches the date and time specified.
     */
    public List<Task> filterList(String dateTime) {
        return list.stream().filter(task -> task.isSameDateTime(dateTime)).collect(Collectors.toList());
    }

    /**
     * Searches through the list and filters out tasks containing a keyword.
     * Returns ArrayList of tasks containing the keyword.
     *
     * @param keyword Keyword to search the list with.
     * @return ArrayList of tasks containing the keyword.
     */
    public List<Task> searchList(String keyword) {
        // @@author {chrisgzf}-reused
        return list.stream().filter(task -> task.toString().contains(keyword)).collect(Collectors.toList());
    }
}
