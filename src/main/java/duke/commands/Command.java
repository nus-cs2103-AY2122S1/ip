package commands;

import exceptions.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;

import java.util.Arrays;
import java.util.List;

public abstract class Command {
    protected List<String> userInputList;

    public Command(String userInput) {
        this.userInputList = Arrays.asList(userInput.split(" "));
    }

    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    abstract public boolean isExit();
}