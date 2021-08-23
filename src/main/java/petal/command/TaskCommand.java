package petal.command;

import petal.components.Responses;
import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;
import petal.exception.EmptyDescException;
import petal.exception.InvalidInputException;

public class TaskCommand implements Command {

    private String type;
    private String input;

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.handleTasks(type, input);
        } catch (EmptyDescException | InvalidInputException e) {
            ui.output(e.getMessage());
            ui.output(Responses.REQUIRED_FORMAT);
        }
    }

    public TaskCommand(String type, String input) {
        this.type = type;
        this.input = input;
    }
}
