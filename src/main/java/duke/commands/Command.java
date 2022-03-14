package duke.commands;

import duke.exception.DukeException.EmptyKeywordException;

/**
 * Represents the main class that oversees all command operations. Responsible for the
 * respective command execution upon interpretation of command word.
 *
 * @author yeo-yiheng
 */
public class Command {

    public static final String MESSAGE = "Type \"help\" for the list of instructions!";

    /**
     * Interprets the command given by the user. If it is a legitimate command,
     * pass in the description into the specific command executor. Else, warn user
     * of invalid command.
     *
     * @param command command word given by the user
     * @param description command description given by the user
     * @return response upon command execution
     */
    public String interpretCommand(String command, String description) {
        new Command();
        Command matchingCommand;
        switch (command) {
        case "greet":
            matchingCommand = new GreetCommand();
            break;
        case "bye":
            matchingCommand = new ExitCommand();
            break;
        case "list":
            matchingCommand = new ListTasksCommand();
            break;
        case "help":
            matchingCommand = new HelpCommand();
            break;
        case "add":
            matchingCommand = new AddCommand(" " + description);
            break;
        case "done":
            matchingCommand = new DoneCommand(description);
            break;
        case "delete":
            matchingCommand = new DeleteCommand(description);
            break;
        case "find":
            try {
                matchingCommand = new FindCommand(description);
            } catch (EmptyKeywordException e) {
                return e.getMessage();
            }
            break;
        default:
            return "I do not recognize your command. Type \"help\" for the list of all commands!";
        }
        return matchingCommand.getMessage();
    }

    /**
     * Retrieves the message stored after execution.
     *
     * @return message stored after execution
     */
    public String getMessage() {
        return MESSAGE;
    }
}
