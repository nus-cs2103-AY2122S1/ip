package dino.command;

import dino.exception.*;
import dino.task.TaskList;
import dino.util.Storage;

/**
 * Represents the command for which the user wants to search for a keyword in the task list
 */
public class FindCommand extends Command {
    String cmdString;

    /**
     * Constructs a FindCommand object
     *
     * @param cmdString the user input command
     */
    public FindCommand(String cmdString) {
        this.cmdString = cmdString;
    }

    /**
     * Executes the Find command to print out a list of tasks which contains the keyword
     *
     * @param storage the local storage file
     * @param taskList the current task list
     * @throws InvalidInputException if the user input command does not specify the keyword to search for
     * @throws TaskNotFoundException if there is no matching result for the current keyword
     */
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
