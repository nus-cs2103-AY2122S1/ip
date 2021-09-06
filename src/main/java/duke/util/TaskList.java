package duke.util;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;


/**
 * Deals with storing of task as well as operations involving the tasks.
 *
 * @author marcuspeh
 * @version Level-9
 * @since 21 Aug 2021
 */
public class TaskList {
    /** List to store all the task. */
    private List<Task> taskList;
    /** To deal with the errorMessages. */
    private Ui ui;
    /** Deals with load and saving of task. */
    private Storage storage;

    /**
     * Constructor for duke.main.TaskList.
     *
     * @param taskList List to store all the task.
     * @param ui To deal with the error messages.
     * @param storage deals with load and saving of tasks.
     */
    public TaskList(List<Task> taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Adds the task entered by the user into the list.
     *
     * @param task task input by the user.
     * @return message to be used by either the graphic UI or command line UI.
     */
    private Message addTask(Task task) {
        taskList.add(task);
        return ui.formatAddMessage(task, taskList.size());
    }

    /**
     * Adds a new Event to the task list.
     *
     * @param s Description of the task.
     * @param dateTime Date and time of the event.
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message addEvent(String s, String dateTime) {
        try {
            Message message = addTask(new Events(s, dateTime));
            saveTaskList();
            return message;
        } catch (ParseException e) {
            return ui.formatDateTimeErrorMessage();
        }
    }

    /**
     * Adds a new deadline to the task list.
     *
     * @param s Description of the task.
     * @param dateTime Deadline of the task.
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message addDeadline(String s, String dateTime) {
        try {
            Message message = addTask(new Deadlines(s, dateTime));
            saveTaskList();
            return message;
        } catch (ParseException e) {
            return ui.formatDateTimeErrorMessage();
        }
    }

    /**
     * Adds a new todo to the task list.
     *
     * @param s Description of the task.
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message addTodo(String s) {
        try {
            Message message = addTask(new ToDos(s));
            saveTaskList();
            return message;
        } catch (IndexOutOfBoundsException e) {
            return ui.formatTodoErrorMessage();
        }
    }

    /**
     * Marks the nth task as done.
     *
     * @param n the task to be mark as done.
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message markDone(int n) {
        Task task = taskList.get(n - 1);
        boolean isSuccess = task.markDone();
        if (isSuccess) {
            saveTaskList();
            return ui.formatDoneSuccessMessage(task);
        } else {
            return ui.formatDoneFailedMessage(task);
        }
    }

    /**
     * Deletes the nth task from the task list.
     *
     * @param n the task to be deleted.
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message deleteTask(int n) {
        Task task = taskList.remove(n - 1);
        saveTaskList();
        return ui.formatDeleteMessage(task, taskList.size());
    }

    /**
     * Saves the task list by calling the method in storage.
     */
    private void saveTaskList() {
        storage.exportTask(this.taskList);
    }

    /**
     * Finds all the task that contains the string S and returns a array containing
     * all the tasks.
     *
     * @param s Keyword to search for the task.
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message findTask(String s) {
        List<String> taskFiltered = taskList.stream()
                .filter(task -> task.getTask().contains(s))
                .map(x -> x.toString())
                .collect(Collectors.toList());
        return ui.formatSearchOutputMessage(taskFiltered);
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
