package petal.command;

import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;
import petal.exception.InvalidInputException;

public class DateCommand implements Command {

    private String input;

    public DateCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.tasksOnThisDay(input);
        } catch (InvalidInputException e) {
            ui.output(e.getMessage());
        }
    }
}
