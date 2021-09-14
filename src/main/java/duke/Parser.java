package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
/**
 * Class that parse user commands.
 */
public class Parser {
    public Command parseCommand(String input) throws DukeException {
        String firstWord = getFirstWord(input).toLowerCase();
        switch (firstWord) {
        case("todo"):
            return Command.TODO;
        case("deadline"):
            return Command.DEADLINE;
        case("event"):
            return Command.EVENT;
        case("list"):
            return Command.LIST;
        case("done"):
            return Command.DONE;
        case("bye"):
            return Command.BYE;
        case("delete"):
            return Command.DELETE;
        case("find"):
            return Command.FIND;
        case("remind"):
            return Command.REMIND;
        default:
            throw new DukeException("Sorry I don't know what that means");
        }
    }

    /**
     * Parses the arguments based on the Command and input.
     * @param c Command that is associated with the arguments.
     * @param input Input string containing the arguments.
     * @return Array of arguments.
     * @throws DukeException If the input string is invalid.
     */
    public String[] parseArguments(Command c, String input) throws DukeException {
        switch(c) {
        case TODO:
            String todoArg = input.replace("todo", "").trim();
            if (todoArg.equals("")) {
                throw new DukeException("TODO needs to have an description");
            }
            return new String[] {todoArg};
        case DEADLINE:
            String[] deadlineArgs = input.replace("deadline ", "").split("/by");
            if (deadlineArgs.length != 2) {
                throw new DukeException("Invalid format for Deadline");
            }
            trimMap(deadlineArgs);
            return deadlineArgs;
        case EVENT:
            String[] eventArgs = input.replace("deadline ", "").split("/at");
            if (eventArgs.length != 2) {
                throw new DukeException("Invalid format for Event");
            }
            trimMap(eventArgs);
            return eventArgs;
        case DONE:
            String doneArgs = input.replace("done", "").trim();
            if (doneArgs.equals("")) {
                throw new DukeException("Index must be given for done");
            }

            return new String[] {doneArgs};
        case DELETE:
            return new String[] {input.split(" ")[1].trim()};
        case FIND:
            return new String[] {input.replace("find ", "").trim()};
        case REMIND:
            return new String[] {input.replace("remind ", "").trim()};
        default :
            return new String[] {};
        }
    }

    /**
     * Parses date in LocalDate format.
     *
     * @param input String to be parsed.
     * @return LocalDate object representing the String.
     * @throws DukeException When input is not a valid date format.
     */
    public LocalDate parseDate(String input) throws DukeException {
        try {
            return LocalDate.parse(input);
        } catch(DateTimeParseException err) {
            throw new DukeException("Invalid Date Format");
        }
    }

    /**
     * Parses a integer based on a string.
     * @param input Input String to be parsed.
     * @return Integer from the String.
     * @throws DukeException If the String is not a representation of a valid integer.
     */
    public Integer parseInt(String input) throws DukeException {
        try {
            return Integer.parseInt(input);
        } catch(NumberFormatException err) {
            throw new DukeException("Invalid number input");
        }
    }

    /**
     * Checks if a Command is BYE.
     * @param input Command to be checked.
     * @return If the Command is BYE.
     */
    public Boolean isBye(Command input) {
        return input.equals(Command.BYE);
    }

    /**
     * Returns the first word of a String, determined by the first whitespace.
     * @param input String where the word will be extracted.
     * @return First word of the string.
     */
    private String getFirstWord(String input) {
        return input.split(" ")[0];
    }

    /**
     * Trims every element in a given String array.
     * @param array Array to be trimmed.
     */
    private void trimMap (String[] array) {
        for (int i = 0 ; i < array.length; i++) {
            array[i] = array[i].trim();
        }
    }

}
