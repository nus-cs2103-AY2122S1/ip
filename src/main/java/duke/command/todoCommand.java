package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import duke.Storage;

import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;

import duke.Ui;

public class todoCommand extends Command {
    private String command;
    
    public todoCommand(String command) {
        super(command);
        this.command = command;
    }

    public String toString() {
        return "This is a todo command";
    }

    public void execute(TaskList taskList, Storage storage) {
        if (command.length() <= 5) {
            DukeException exp = new EmptyDescriptionException("OOPS!!! The description of a todo cannot be empty.");
            System.out.println(exp);
        } else {
            Task task = new Todo(command.substring(5));
            taskList.addTask(task);
            Ui.taskResponse(task);
            storage.writeToFile("./duke.txt", taskList);
        } 
    }
}