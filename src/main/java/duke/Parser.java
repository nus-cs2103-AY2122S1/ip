package duke;

import exception.InvalidTaskException;
import exception.NoDescriptionException;
import exception.WrongDescriptionException;


/**
 * A parser to make sense of the user's input
 */
public class Parser {

    /**
     * Returns the type of command from the user input
     *
     * @param input user input
     * @return the type of command
     * @throws InvalidTaskException if the input does not match the supported command
     */
    public Command parseCommand(String input) throws InvalidTaskException {
        String parsed = input.split(" ", 2)[0];
        boolean validCommand = false;
        Command parsedCommand = null;
        for (Command command: Command.values()) {
            if (command.command.equals(parsed)) {
                validCommand = true;
                parsedCommand = command;
            }
        }

        if (!validCommand) {
            throw new InvalidTaskException("Invalid command! Please enter the following commands only:\n"
                                           + "list\ndone (task number)\n"
                                           + "delete (task number)\ntodo (description)\n"
                                           + "deadline (description) /by (time)\nevent (description) /at (time)");
        } else {
            return parsedCommand;
        }

    }

    /**
     * Returns the task number from the user input
     *
     * @param input user input
     * @return task number
     * @throws NoDescriptionException if task number not included in user input
     */
    public int getTaskNo(String input) throws NoDescriptionException {
        String[] parsed = input.split(" ", 2);
        if (parsed.length == 1) {
            throw new NoDescriptionException("Please enter the task no.!");
        } else {
            return Integer.parseInt(parsed[1]);
        }
    }

    /**
     * Returns the description of the task from the user input
     *
     * @param input user input
     * @return description of the task
     * @throws NoDescriptionException if description not included in user input
     */
    public String parseDescription(String input) throws NoDescriptionException {
        String[] parsed = input.split(" ", 2);
        if (parsed.length == 1) {
            throw new NoDescriptionException("Description of task cannot be empty!");
        } else {
            return parsed[1];
        }
    }


    /**
     * Returns the description of the task from the user input
     *
     * @param input user input
     * @param conjunction "at" or "by"
     * @return separated description and time
     * @throws NoDescriptionException if description not included in user input
     * @throws WrongDescriptionException if conjunction not included in user input
     */

    public String[] parseDescription(String input, String conjunction) throws NoDescriptionException,
                                                                                WrongDescriptionException {
        String[] parsed = input.split(" ", 2);
        if (parsed.length == 1) {
            throw new NoDescriptionException("Please enter the task no.!");
        } else {
            String description = parsed[1];
            int index = description.indexOf(conjunction);
            if (index == - 1) {
                if (conjunction.equals("by")) {
                    throw new WrongDescriptionException("Deadline not included! Try: deadline ... /by ...");
                } else {
                    throw new WrongDescriptionException("Event time not included! Try: event ... /at ...");
                }
            } else {
                return new String[] {description.substring(0, index - 2), description.substring(index + 3)};
            }
        }
    }

}
