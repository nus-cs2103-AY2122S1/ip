package petal.command;

import petal.components.Responses;
import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;
import petal.exception.EmptyDescException;
import petal.exception.InvalidInputException;

public class DoneCommand implements Command {

    private String input;

    public DoneCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.markTaskAsDone(input);
        } catch (InvalidInputException e) {
            ui.output(e.getMessage());
            ui.output(Responses.REQUIRED_FORMAT);
        }
    }
}
