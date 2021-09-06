package dino.command;

import dino.exception.*;
import dino.task.TaskList;
import dino.util.Storage;

/**
 * Represents the command for which the user wants to search for a keyword in the task list
 */
public class FindCommand extends Command {
    private final String cmdString;

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
     * @return the output message after execution
     * @throws InvalidInputException if the user input command does not specify the keyword to search for
     * @throws TaskNotFoundException if there is no matching result for the current keyword
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws InvalidInputException, TaskNotFoundException {
        return taskList.searchKeyword(this.getKeyword());
    }

    /**
     * Extracts the keyword given in the fina command
     * @return the keyword that is extracted from the find command
     * @throws InvalidInputException if the keyword is not specified
     */
    public String[] getKeyword() throws InvalidInputException {
        if (this.cmdString.length() < 6) {
            throw new InvalidInputException();
        }
        String keywords = this.cmdString.substring(5).trim();
        if (keywords.length() == 0) {
            throw new InvalidInputException();
        }
        return keywords.split(", ");
    }
}
