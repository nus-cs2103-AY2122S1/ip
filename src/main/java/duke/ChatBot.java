package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

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

    private int exitStatus = 1;

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
//        ui.showStartMessage();
    }

    /**
     * This method is to stop the program from running and prints
     * out the exit message.
     */
    public String handleExit() {
        exitStatus = 0;
        return ui.showExitMessage();
    }

    /**
     * This method is to call the UI to print all the tasks that is stored.
     */
    public String handleList() {
        return ui.getListMessage(tasklist);
    }

    /**
     * This method is to mark the user specified task as done
     * and print out the corresponding UI message.
     *
     * @param index Index of the task.
     */
    public String handleDone(int index) {
        Task t = tasklist.completeTask(index);
        s.saveTasks(tasklist);
        return ui.showCompleteTaskMessage(t);
    }

    /**
     * This method is to add a new deadline to the storage and
     * print out the add message when the task is added successfully.
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
     * This method is to add a new task to the storage and
     * print out the add message when the task is added successfully.
     * @param name Name of the task.
     */
    public String handleTodo(String name) {
        Task t = tasklist.addTodo(name, false);
        s.saveTasks(tasklist);
        return ui.showAddTaskMessage(t, tasklist.getTotalTasksNumber());
    }

    /**
     * This method is to add a new event to the storage and
     * print out the add message when the task is added successfully.
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
     * This method deletes the task specified by the user and
     * print the delete message on successful deletion.
     *
     * @param index Index of the task specified by the user.
     */
    public String handleDelete(int index) {
        Task t = tasklist.deleteTask(index);
        s.saveTasks(tasklist);
        return ui.showDeleteTaskMessage(t, tasklist.getTotalTasksNumber());
    }

    /**
     * This method calls TaskList to find tasks related to the
     * input by the user and calls UI to display them.
     * @param name User input task to find.
     */
    public String handleFind(String name) {
        String findTask = tasklist.findTask(name);
        return ui.showFoundTask(findTask);
    }

    /**
     * This method prints the list of all commands.
     */
    public String handleWrongCommand() {
        return ui.showListOfCommands();
    }

    /**
     * This method prints the error message thrown by the program.
     * @param message The error message.
     */
    public String handleErrorMessage(String message) {
        return ui.showErrorMessage(message);
    }

    /**
     * Calls the UI to return the start message.
     */
    public String handleStart() {
        return ui.showStartMessage();
    }

    /**
     * This method gets the total number of tasks.
     * @return total number of tasks.
     */
    public int getTotalTasks() {
        return tasklist.getTotalTasksNumber();
    }

    /**
     * This method checks if the program should terminate.
     * @return 1 to continue and 0 to terminate.
     */
    public int getExitStatus() {
        return exitStatus;
    }

}
