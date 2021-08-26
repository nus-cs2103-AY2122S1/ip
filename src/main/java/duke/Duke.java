package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.time.LocalDateTime;

public class Duke {
    
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage(); 
            taskList = new TaskList(storage.load());
        } catch (DukeException ex) {
            ui.showStorageError();
            storage = null;
            taskList = new TaskList();
        }
    }
    
    public void run() {
        ui.showWelcome();
        ui.beginListen();
        boolean isExit = false;
        
        while (!isExit) {
            try {
                String newCommand = ui.readCommand();
                String[] parsedCommand = Parser.parse(newCommand);
                switch (parsedCommand[0]) {
                case "bye":
                    ui.showGoodbye();
                    isExit = true;
                    break;
                case "list":
                    ui.showList(taskList.getList());
                    break;
                case "add":
                    addTask(parsedCommand);
                    break;
                case "done":
                    markTaskDone(parsedCommand);
                    break;
                case "delete":
                    deleteTask(parsedCommand);
                    break;
                case "fail":
                    ui.showCommandFail();
                    break;
                default:
                    break;
                }
            } catch (DukeException | IOException ex) {
                ui.showError(ex.getMessage());
            }
        }
        
        ui.stopListen();
    }

    private void addTask(String[] parsedCommand) throws IOException {
        Task newTask = null;
        switch (parsedCommand[1]) {
        case "todo":
            newTask = new Todo(parsedCommand[2]);
            break;
        case "deadline":
            LocalDateTime by = Parser.parseDateTime(parsedCommand[3]);
            newTask = new Deadline(parsedCommand[2], by);
            break;
        case "event":
            LocalDateTime from = Parser.parseDateTime(parsedCommand[3]);
            LocalDateTime to = Parser.parseDateTime(parsedCommand[3]);
            newTask = new Event(parsedCommand[2], from, to);
            break;
        default:
            break;
        }
        if (newTask != null) {
            storage.storeAdd(newTask.toStorage());
            String message = taskList.addTask(newTask);
            ui.showMessage(message);
        }
    }
    
    private void markTaskDone(String[] parsedCommand) throws IOException {
        int taskIndex = Parser.parseIndex(parsedCommand[1]);
        if (taskList.isIndexValid(taskIndex)) {
            String message = taskList.markDone(taskIndex);
            ui.showMessage(message);
            String taskStorage = taskList.getTaskStorage(taskIndex);
            storage.storeDone(taskIndex, taskStorage);
        } else {
            ui.showError("That task doesn't exist.\nPlease Try again.");   
        }
    }

    private void deleteTask(String[] parsedCommand) throws IOException {
        int taskIndex = Parser.parseIndex(parsedCommand[1]);
        if (taskList.isIndexValid(taskIndex)) {
            String message = taskList.deleteTask(taskIndex);
            ui.showMessage(message);
            storage.storeDelete(taskIndex);
        } else {
            ui.showError("That task doesn't exist.\nPlease Try again.");
        }
    }
    
    public static void main(String[] args) {
        new Duke().run();
    }
}
