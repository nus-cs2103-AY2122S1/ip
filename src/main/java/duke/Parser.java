package duke;

import duke.command.Command;
import duke.command.deadlineCommand;
import duke.command.doneCommand;
import duke.command.eventCommand;
import duke.command.listCommand;
import duke.command.InvalidCommand;
import duke.command.deleteCommand;
import duke.command.todoCommand;
import duke.command.findCommand;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    private String command;

    /**
     * Constructor for the Parser class where the user command is initialized
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
        if (command.equals("list")) {
            return new listCommand(command);
        } else if (command.startsWith("done") && Character.isDigit(command.charAt(command.length() - 1)) && command.length() <= 8 
                && !Character.isAlphabetic(command.charAt(command.length() - 2))  && Character.isDigit(command.charAt(5))) {
            return new doneCommand(command);
        } else {
            if (command.startsWith("todo")) {
                return new todoCommand(command);
            } else if (command.startsWith("deadline")) {
                return new deadlineCommand(command);
            } else if (command.startsWith("event")) {
                return new eventCommand(command);
            } else if (command.startsWith("delete")) {
                return new deleteCommand(command);
            } else if (command.startsWith("find")) {
                return new findCommand(command);
            } else {
                return new InvalidCommand(command);
            }
        }
    }

    /**
     * Checks if the user types the command 'bye' or the user clicks enter without typing any command.
     *
     * @return True or False.
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
