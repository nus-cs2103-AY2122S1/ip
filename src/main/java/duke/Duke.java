package duke;

import duke.command.Command;
import duke.command.CommandType;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

public class Duke {
    
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.displayErrorMessage(e.getMessage());
            this.taskList = new TaskList(new ArrayList<>());
        }
    }
    
    public void run() {
        ui.greet();
        while (true) {
            try {
                Command command = ui.receiveCommand();
                if (command.type == CommandType.BYE) {
                    ui.sayGoodbye();
                    break;
                } else if (command.type == CommandType.ADD) {
                    Task task = (Task) command.payload;
                    taskList.add(task);
                    ui.displayAddedTask(task, taskList.getTaskCount());
                } else if (command.type == CommandType.DONE) {
                    int serialNo = (int) command.payload;
                    ui.displayDoneTask(taskList.markDone(serialNo));
                } else if (command.type == CommandType.DELETE) {
                    int serialNo = (int) command.payload;
                    ui.displayRemovedTask(taskList.remove(serialNo), taskList.getTaskCount());
                } else if (command.type == CommandType.LIST) {
                    ui.displayTasks(taskList.getTasks());
                } else if (command.type == CommandType.FIND) {
                    ui.displayFoundTasks(taskList.findMatchingTasks((String) command.payload));
                }
            } catch (DukeException e) {
                ui.displayErrorMessage(e.getMessage());
            }
        }
        ui.close();
        storage.write(taskList.getTasks());
    }
    
    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
