package petal.command;

import petal.Petal;
import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;
import petal.exception.InvalidInputException;

import java.io.IOException;

public class ByeCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.saveTasks();
        } catch (IOException e) {
            ui.output("Sorry, the task couldn't be saved :/");
        } finally {
            ui.goodBye();
        }
    }

}
