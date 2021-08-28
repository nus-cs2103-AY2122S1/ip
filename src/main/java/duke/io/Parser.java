package duke.io;

import duke.io.Command.CommandName;
import duke.DukeException;

import java.util.Scanner;

/**
 * Parser class intended to be used to parse user input to extract the correct command.
 */
public class Parser {

    /**
     * Retrieves the next line of input, disregarding extra whitespaces.
     *
     * @param sc The scanner object to retrieve the next line of input from
     * @return The next line of input after trimming whitespace from both ends
     */
    public static String getInput(Scanner sc) {
        return sc.nextLine().trim();
    }

    /**
     * Parses a line of given user input to extract the relevant/correct command.
     *
     * @param userInput A String containing a line of input to parse as a single command
     * @return A Command object containing an identifier of the command and its arguments
     * @throws DukeException When given input cannot be parsed successfully
     */
    public static Command parse(String userInput) throws DukeException {
        if (userInput.equals("bye")) {
            // EXIT command
            return new Command(CommandName.BYE);
        } else if (userInput.equals("list")) {
            // LIST command
            return new Command(CommandName.LIST);
        } else if (userInput.startsWith("done")) {
            // DONE command
            int index = Integer.parseInt(userInput.substring(5));
            return new Command(CommandName.DONE, new String[]{String.valueOf(index)});
        } else if (userInput.startsWith("delete")) {
            // DELETE command
            int index = Integer.parseInt(userInput.substring(7));
            return new Command(CommandName.DELETE, new String[]{String.valueOf(index)});
        } else if (userInput.startsWith("todo")) {
            // TODO command

            // If no arguments provided
            if (userInput.length() == 4) {
                throw new DukeException("The description of a todo cannot be empty.");
            } else if (!userInput.startsWith("todo ")) {
                // If it does not start with "todo " after trimming, it is an invalid word
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }

            // Command name + description only
            return new Command(CommandName.TODO, new String[]{userInput.substring(5).trim()});
        } else if (userInput.startsWith("deadline")) {
            // DEADLINE command

            // If no arguments provided
            if (userInput.length() == 8) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else if (!userInput.startsWith("deadline ")) {
                // If it does not start with "deadline " after trimming, it is an invalid word
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            } else if (!userInput.contains("/by ")) {
                throw new DukeException(
                        ("A deadline must contain a deadline indicated after the /by command"));
            }

            String description = userInput.substring(9, userInput.indexOf("/by")).trim();
            String deadline = userInput.substring(userInput.indexOf("/by")).trim();
            if (description.length() == 0) {
                throw new DukeException("The description of a deadline cannot be empty");
            }

            // Command name + description + deadline
            return new Command(CommandName.DEADLINE, new String[]{description, deadline.substring(4)});

        } else if (userInput.startsWith("event")) {
            // EVENT command

            // If no arguments provided
            if (userInput.length() == 5) {
                throw new DukeException("The description of a event cannot be empty.");
            } else if (!userInput.startsWith("event ")) {
                // If it does not start with "deadline " after trimming, it is an invalid word
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            } else if (!userInput.contains("/at ")) {
                throw new DukeException(
                        ("An event must contain a datetime indicated after the /at command"));
            }

            String description = userInput.substring(5, userInput.indexOf("/at")).trim();
            String dateTime = userInput.substring(userInput.indexOf("/at")).trim(); // Inclusive of the /by command
            if (description.length() == 0) {
                throw new DukeException("The description of an event cannot be empty");
            }

            // Command name + description + dateTime
            return new Command(CommandName.EVENT, new String[]{description, dateTime.substring(4)});

        } else if (userInput.startsWith("date")) {
            // DATE command

            if (userInput.length() == 4) {
                throw new DukeException(
                        "A date must be provided to find events and deadlines occurring on that day");
            } else if (!userInput.startsWith("date ")) {
                // If it does not start with "date " after trimming, it is an invalid command
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }

            // Command name + date only
            return new Command(CommandName.DATE, new String[]{userInput.substring(5)});

        } else {
            // Unknown command
            throw new DukeException(("I'm sorry, but I don't know what that means :-("));
        }
    }
}
