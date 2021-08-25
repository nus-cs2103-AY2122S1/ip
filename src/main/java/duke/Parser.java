package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
/**
 * Class that parse user commands
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
        default:
            throw new DukeException("Sorry I don't know what that means");
        }
    }

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
        default :
            return new String[] {};
        }
    }

    public LocalDate parseDate(String input) throws DukeException {
        try {
            return LocalDate.parse(input);
        } catch(DateTimeParseException err) {
            throw new DukeException("Invalid Date Format");
        }
    }

    public Integer parseInt(String input) throws DukeException {
        try {
            return Integer.parseInt(input);
        } catch(NumberFormatException err) {
            throw new DukeException("Invalid number input");
        }
    }

    public Boolean isBye(Command input) {
        return input.equals(Command.BYE);
    }

    public Boolean isValid(Command input) {
        return !input.equals(Command.INVALID);
    }

    private String getFirstWord(String input) {
        return input.split(" ")[0];
    }

    private void trimMap (String[] array) {
        for (int i = 0 ; i < array.length; i++) {
            array[i] = array[i].trim();
        }
    }

}
