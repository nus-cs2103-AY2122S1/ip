package duke.commands;

import duke.exceptions.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import java.util.Arrays;
import java.util.List;

public abstract class Command {
    protected List<String> userInputList;

    /**
     * Creates command from userInput in String
     *
     * @param userInput User input in string formats
     * @throws DukeException If user doesn't specify description/time of task
     */
    public Command(String userInput) throws DukeException {
        this.userInputList = Arrays.asList(userInput.split(" "));
    }

    /**
     * Executes the command to have effect on TaskList, Ui, Storage
     *
     * @param taskList The object that holds a list of Task
     * @param ui The object responsible for updating Ui response
     * @param storage The object responsible to save/load list of task to/from hard disk
     */
    abstract public void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Returns if a command is terminal command that will exit the program
     *
     * @return True if it is exit command, False otherwise
     */
    abstract public boolean isExit();
}