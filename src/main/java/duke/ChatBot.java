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
        ui.showStartMessage();
    }

    /**
     * This method is to stop the program from running and prints
     * out the exit message.
     */
    public void handleExit() {
        exitStatus = 0;
        ui.showExitMessage();
    }

    /**
     * This method is to call the UI to print all the tasks that is stored.
     */
    public void handleList() {
        ui.getListMessage(tasklist);
    }

    /**
     * This method is to mark the user specified task as done
     * and print out the corresponding UI message.
     *
     * @param index Index of the task.
     */
    public void handleDone(int index) {
        Task t = tasklist.completeTask(index);
        ui.showCompleteTaskMessage(t);
        s.saveTasks(tasklist);
    }

    /**
     * This method is to add a new deadline to the storage and
     * print out the add message when the task is added successfully.
     *
     * @param name Name of the deadline.
     * @param deadline The date and time of the deadline.
     */
    public void handleDeadline(String name, LocalDateTime deadline) {
        Task t = tasklist.addDeadline(name, deadline, false);
        ui.showAddTaskMessage(t, tasklist.getTotalTasksNumber());
        s.saveTasks(tasklist);
    }

    /**
     * This method is to add a new task to the storage and
     * print out the add message when the task is added successfully.
     * @param name Name of the task.
     */
    public void handleTodo(String name) {
        Task t = tasklist.addTodo(name, false);
        ui.showAddTaskMessage(t, tasklist.getTotalTasksNumber());
        s.saveTasks(tasklist);
    }

    /**
     * This method is to add a new event to the storage and
     * print out the add message when the task is added successfully.
     *
     * @param name Name of the deadline.
     * @param time The date and time of the event.
     */
    public void handleEvent(String name, LocalDateTime time) {
        Task t = tasklist.addEvent(name, time, false);
        ui.showAddTaskMessage(t, tasklist.getTotalTasksNumber());
        s.saveTasks(tasklist);
    }

    /**
     * This method deletes the task specified by the user and
     * print the delete message on successful deletion.
     *
     * @param index Index of the task specified by the user.
     */
    public void handleDelete(int index) {
        Task t = tasklist.deleteTask(index);
        ui.showDeleteTaskMessage(t, tasklist.getTotalTasksNumber());
        s.saveTasks(tasklist);
    }

    /**
     * This method calls TaskList to find tasks related to the
     * input by the user and calls UI to display them.
     * @param name User input task to find.
     */
    public void handleFind(String name) {
        String findTask = tasklist.findTask(name);
        ui.showFoundTask(findTask);
    }

    /**
     * This method prints the list of all commands.
     */
    public void handleWrongCommand() {
        ui.showListOfCommands();
    }

    /**
     * This method prints the error message thrown by the program.
     * @param message The error message.
     */
    public void handleErrorMessage(String message) {
        ui.showErrorMessage(message);
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
