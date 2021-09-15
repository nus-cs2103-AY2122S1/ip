package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.InvalidCommand;
import duke.command.DeleteCommand;
import duke.command.TodoCommand;
import duke.command.FindCommand;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    private String command;

    /**
     * Represents a constructor for the Parser class where the user command is initialized.
     *
     * @param command Command typed the user.
     */
    public Parser(String command) {
        this.command = command;
    }

    /**
     * Returns the instance of the type of command entered by the user.
     *
     * @return Command object.
     */
    public Command parse() {
        int len = command.length();
        if (command.equals("list")) {
            return new ListCommand(command);
        } else if (command.startsWith("done") && Character.isDigit(command.charAt(len - 1))) {
            return new DoneCommand(command);
        } else if (command.startsWith("todo")) {
            return new TodoCommand(command);
        } else if (command.startsWith("deadline")) {
            return new DeadlineCommand(command);
        } else if (command.startsWith("event")) {
            return new EventCommand(command);
        } else if (command.startsWith("delete")) {
            return new DeleteCommand(command);
        } else if (command.startsWith("find")) {
            return new FindCommand(command);
        } else {
            return new InvalidCommand(command);
        }
    }

    /**
     * Checks if the user types the command 'bye' or clicks 'enter' without typing any command.
     *
     * @return true or false.
     */
    public boolean isExit() {
        if (command.equals("bye") || command.equals("")) {
            Ui.sayBye();
            Ui.showLine();
            return true;
        }
        return false;
    }
}
