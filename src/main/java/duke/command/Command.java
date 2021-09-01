package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;



public abstract class Command {

    private String userInput;

    public Command(String userInput) {
        this.userInput = userInput;
    }

    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();

    public String getUserInput() {
        return this.userInput;
    }

}
