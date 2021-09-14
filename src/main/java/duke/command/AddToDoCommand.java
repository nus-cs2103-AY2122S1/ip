package duke.command;

import duke.ArchiveList;
import duke.TaskList;
import duke.exception.IncompleteToDoException;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Representation for the AddToDoCommand of Duke.
 */
public class AddToDoCommand extends AddCommand {

    /**
     * Constructor for AddCommand.
     *
     * @param toAdd Task to add to taskList.
     */
    public AddToDoCommand(Task toAdd) {
        super(toAdd);
    }

    /**
     * Factory method which generates the AddToDoCommand from the userInput.
     *
     * @param userInput User Input which is used to generate the AddToDoCommand.
     * @param taskList taskList of duke.
     * @param archiveList archiveList of duke.
     * @return AddToDoCommand to be executed.
     * @throws IncompleteToDoException if insufficient values are passed in.
     */
    public static AddToDoCommand generateCommand(
            String userInput, TaskList taskList, ArchiveList archiveList) throws IncompleteToDoException {
        String[] str = splitToDoInput(userInput);

        assert str.length == 1;

        ToDo add = new ToDo(str[0]);
        return new AddToDoCommand(add);
    }

    /**
     * Splits string which contains a description for the ToDo object.
     *
     * @param input Input which contains a description of the ToDo Object
     * @return String[] containing the description at index 0.
     * @throws IncompleteToDoException If no description for ToDo objects.
     */
    public static String[] splitToDoInput(String input) throws IncompleteToDoException {
        String[] str = input.split(SPACE);

        if (str.length == 1) {
            throw new IncompleteToDoException();
        } else {
            String description = getDescription(str);
            return new String[]{description};
        }
    }

}
