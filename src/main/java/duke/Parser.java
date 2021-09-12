package duke;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.EditCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.SortCommand;
import duke.commands.TodoCommand;

/**
 * The Parser class that makes sense of the user input.
 */
public class Parser {
    private String input;
    private String[] inputArr;
    private String command;

    /**
     * Constructor for a Parser object.
     *
     * @param input The user input to be parsed.
     */
    public Parser(String input) throws DukeException {
        this.input = input.strip();

        checkForEmptyInput();

        this.inputArr = input.split(" ");
        this.command = inputArr[0];
    }

    private void checkForEmptyInput() throws DukeException {
        boolean isEmptyInput = input.length() == 0;
        if (isEmptyInput) {
            throw new DukeException("Enter a valid command to start!");
        }
    }

    private boolean isEditingTask() {
        return command.equals("done") || command.equals("delete");
    }

    private boolean isFindingTask() {
        return command.equals("find");
    }

    /**
     * Method that parses the user input and returns the relevant Command.
     *
     * @return The Command to be executed.
     * @throws DukeException If the user input is in an invalid format.
     */
    public Command parse() throws DukeException {
        if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("sort")) {
            return new SortCommand();
        } else if (isFindingTask()) {
            return new FindCommand(input);
        } else if (isEditingTask()) {
            return new EditCommand(input);
        } else if (input.startsWith("todo")) {
            return new TodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new DeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new EventCommand(input);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :(");
        }
    }
}
