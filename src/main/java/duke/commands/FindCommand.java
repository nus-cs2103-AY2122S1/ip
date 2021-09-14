package duke.commands;

import duke.DukeException;
import duke.TaskList;

/**
 * Class that represents the command to find tasks containing the keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param input The user input.
     * @throws DukeException If user input is in an invalid format.
     */
    public FindCommand(String input) throws DukeException {
        keyword = getKeyword(input);
    }

    private String getKeyword(String input) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length < 2) {
            throw new DukeException("Please specify a search keyword");
        } else {
            assert splitInput.length >= 2 : "Improper input length for find command";
            return splitInput[1].strip();
        }
    }

    /**
     * Executes the command.
     *
     * @param taskList The list of tasks to search from.
     * @return Response to be displayed in the GUI.
     * @throws DukeException If no tasks are found in the list.
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        TaskList matches = taskList.find(keyword);
        return "Here are the matching tasks in your list:\n" + matches.toString();
    }
}
