package dino.command;

import dino.exception.*;
import dino.task.TaskList;
import dino.util.Storage;

public class FindCommand extends Command {
    String cmdString;

    public FindCommand(String cmdString) {
        this.cmdString = cmdString;
    }

    @Override
    public void execute(Storage storage, TaskList taskList) throws InvalidInputException, TaskNotFoundException {
        taskList.searchKeyword(this.getKeyword());
    }

    public String getKeyword() throws InvalidInputException {
        if (this.cmdString.length() < 6) throw new InvalidInputException();
        if (this.cmdString.substring(5).trim().length() == 0) throw new InvalidInputException();
        else return this.cmdString.substring(5).trim();
    }
}
