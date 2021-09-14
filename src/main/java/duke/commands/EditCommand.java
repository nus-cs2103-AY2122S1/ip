package duke.commands;

import duke.DukeException;
import duke.TaskList;

/**
 * Class that represents 'done' or 'delete' commands.
 */
public class EditCommand extends Command {
    private String editAction;
    private String index;

    /**
     * The constructor for an EditCommand.
     *
     * @param input The user input.
     * @throws DukeException If user input is in an invalid format.
     */
    public EditCommand(String input) throws DukeException {
        checkInput(input);
        this.editAction = getEditAction(input);
        this.index = getIndex(input);
    }

    private String getEditAction(String input) {
        String[] splitInput = input.split(" ");
        return splitInput[0];
    }

    private String getIndex(String input) throws DukeException {
        try {
            String[] splitInput = input.split(" ");
            int index = Integer.parseInt(splitInput[1]);
            return splitInput[1];
        } catch (NumberFormatException e) {
            throw new DukeException("Task index should be a valid integer");
        }
    }

    private void checkInput(String input) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length < 2) {
            throw new DukeException("Please specify the index of the task to be edited"
                    + "\n i.e. done 3");
        }
    }

    /**
     * Executes the command.
     *
     * @param taskList The list of tasks which will be edited.
     * @return Response to be displayed in the GUI.
     * @throws DukeException If the specified task in the task list is unable to be edited.
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        assert editAction.equals("done") || editAction.equals("delete");
        String[] args = new String[] {editAction, index};
        String response = taskList.editTask(args);
        return response;
    }
}
