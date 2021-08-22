package duke;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;


import java.time.LocalDateTime;

import java.util.ArrayList;

public class ChatBot {

    private ArrayList<Task> temp;
    private TaskList tasklist;
    private Storage s;
    private UI ui;
    private int exitCode = 1;

    public ChatBot() {
        this.s = new Storage();
        this.ui = new UI();
        s.makeDir();
        s.checkFile();
        this.temp = s.loadTasks();
        tasklist = new TaskList(temp);
        ui.showStartMessage();
    }

    public void handleExit() {
        this.exitCode = 0;
        ui.showExitMessage();
    }

    public void handleList() {
        ui.getListMessage(tasklist);
    }

    public void handleDone(int index) {
        Task t = tasklist.completeTask(index);
        ui.showCompleteTaskMessage(t);
        s.saveTasks(tasklist);
    }

    public void handleDeadline(String name, LocalDateTime deadline) {
        Task t = tasklist.addDeadline(name, deadline, false);
        ui.showAddDeadlineMessage(t, tasklist.getTotalTasksNumber());
        s.saveTasks(tasklist);
    }

    public void handleTodo(String name) {
        Task t = tasklist.addTodo(name, false);
        ui.showAddTodoMessage(t, tasklist.getTotalTasksNumber());
        s.saveTasks(tasklist);
    }

    public void handleEvent(String name, LocalDateTime time) {
        Task t = tasklist.addEvent(name, time, false);
        ui.showAddTaskMessage(t, tasklist.getTotalTasksNumber());
        s.saveTasks(tasklist);
    }

    public void handleDelete(int index) {
        Task t = tasklist.deleteTask(index);
        ui.showDeleteTaskMessage(t, tasklist.getTotalTasksNumber());
        s.saveTasks(tasklist);
    }

    public void handleWrongCommand() {
        ui.showListOfCommands();
    }

    public void handleErrorMessage(String message) {
        ui.showErrorMessage(message);
    }

    public int getTotalTasks() {
        return tasklist.getTotalTasksNumber();
    }

    public int getExitCode() {
        return exitCode;
    }

}
