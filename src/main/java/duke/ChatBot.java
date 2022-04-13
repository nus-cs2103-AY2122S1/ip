package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.util.Status;

/**
 * ChatBot is a class that encapsulates what a chat bot has.
 * It functions as a chat bot that responds to user inputs and
 * does tasks specified by the user.
 */
public class ChatBot {

    private ArrayList<Task> temp;
    private TaskList tasklist;
    private Storage s;
    private UI ui;

    private Status exitStatus = Status.START;

    /**
     * This is the constructor of ChatBot that initializes the UI and Storage.
     */
    public ChatBot() {
        this.s = new Storage();
        this.ui = new UI();
    }

    /**
     * This is the method called when the program is started.
     * It checks for the save directory and loads any previously
     * stored tasks. It also prints out the start message.
     */
    public void start() {
        s.makeDir();
        s.checkFile();
        this.temp = s.loadTasks();
        tasklist = new TaskList(temp);
    }

    /**
     * Returns the exit message.
     */
    public String handleExit() {
        exitStatus = Status.STOP;
        return ui.showExitMessage();
    }

    /**
     * Returns the list of all tasks.
     */
    public String handleList() {
        return ui.getListMessage(tasklist);
    }

    /**
     * Marks the task as completed and returns the completed task message.
     *
     * @param index Index of the task.
     */
    public String handleDone(int index) {
        Task t = tasklist.completeTask(index);
        s.saveTasks(tasklist);
        return ui.showCompleteTaskMessage(t);
    }

    /**
     * Adds a new deadline to the storage and
     * returns the add message when the task is added successfully.
     *
     * @param name Name of the deadline.
     * @param deadline The date and time of the deadline.
     */
    public String handleDeadline(String name, LocalDateTime deadline) {
        Task t = tasklist.addDeadline(name, deadline, false);
        s.saveTasks(tasklist);
        return ui.showAddTaskMessage(t, tasklist.getTotalTasksNumber());
    }

    /**
     * Adds a new task to the storage and
     * returns the add message when the task is added successfully.
     *
     * @param name Name of the task.
     */
    public String handleTodo(String name) {
        Task t = tasklist.addTodo(name, false);
        s.saveTasks(tasklist);
        return ui.showAddTaskMessage(t, tasklist.getTotalTasksNumber());
    }

    /**
     * Adds a new event to the storage and
     * returns the add message when the task is added successfully.
     *
     * @param name Name of the deadline.
     * @param time The date and time of the event.
     */
    public String handleEvent(String name, LocalDateTime time) {
        Task t = tasklist.addEvent(name, time, false);
        s.saveTasks(tasklist);
        return ui.showAddTaskMessage(t, tasklist.getTotalTasksNumber());

    }

    /**
     * Deletes the task specified by the user and
     * returns the delete message on successful deletion.
     *
     * @param index Index of the task specified by the user.
     */
    public String handleDelete(int index) {
        Task t = tasklist.deleteTask(index);
        s.saveTasks(tasklist);
        return ui.showDeleteTaskMessage(t, tasklist.getTotalTasksNumber());
    }

    /**
     * Finds the tasks related to the
     * input by the user and returns the string representation to display.
     *
     * @param name User input task to find.
     */
    public String handleFind(String name) {
        String findTask = tasklist.findTask(name);
        return ui.showFoundTask(findTask);
    }

    /**
     * Sorts the tasks by the given sorting keyword.
     *
     * @param sortName User input keyword to sort by.
     */
    public String handleSort(String sortName) throws DukeException {
        String sortedList = tasklist.sortTask(sortName);
        return ui.showSortedTask(sortedList);
    }

    /**
     * Returns the list of all commands. (help)
     */
    public String handleWrongCommand() {
        return ui.showListOfCommands();
    }

    /**
     * Returns the error message thrown by the program.
     *
     * @param message The error message.
     */
    public String handleErrorMessage(String message) {
        return ui.showErrorMessage(message);
    }

    /**
     * Returns the start message.
     */
    public String handleStart() {
        return ui.showStartMessage();
    }

    /**
     * Gets the total number of tasks.
     *
     * @return total number of tasks.
     */
    public int getTotalTasks() {
        return tasklist.getTotalTasksNumber();
    }

    /**
     * Returns an int depending on exit status.
     *
     * @return 0 to exit, 1 to continue.
     */
    public Status getExitStatus() {
        return exitStatus;
    }

}
