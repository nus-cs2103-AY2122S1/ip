package duke.util;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.format.DateTimeParseException;

public class Parser {


    /**
     * Trims the input String into the default reading format.
     *
     * @param input The input String.
     * @return The trimmed input String to be processed.
     */
    public static String parseInput(String input) {
        return input.trim().replaceAll(" +", " ");
    }

    /**
     * Parses the given String into a Command.
     *
     * @param input The input String.
     * @return The specific Command to execute.
     */
    public static Command parseCommand(String input) {

        String[] fullCommand = input.split(" ", 2);
        Command command = null;
        switch (fullCommand[0]) {
        case "help":
            if (fullCommand.length > 2) {
                command = new WrongCommand("You cannot put anything behind the keyword \"bye\".");
            } else {
                command = new HelpCommand(input);
            }
            break;
        case "bye":
            if (fullCommand.length > 1) {
                command = new WrongCommand("You cannot put anything behind the keyword \"bye\".");
            } else {
                command = new ExitCommand();
            }
            break;
        case "list":
            if (fullCommand.length > 1) {
                command = new WrongCommand("You cannot put anything behind the keyword \"list\".");
            } else {
                command = new ListCommand();
            }
            break;
        case "todo":
            try {
                command = new AddCommand(ToDo.of(fullCommand[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                command = new WrongCommand("The description of a todo cannot be empty, idiot.");
            }
            break;
        case "deadline":
            try {
                String[] strings = input.split("/", 2);
                String[] strings1 = strings[0].split(" ", 2);
                String[] strings2 = strings[1].split(" ", 2);
                command = new AddCommand(Deadline.of(strings1[1], strings2[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                command = new WrongCommand("The description of a deadline cannot be empty, idiot.");
            }
            break;
        case "event":
            try {
                String[] strings = input.split("/", 2);
                String[] strings1 = strings[0].split(" ", 2);
                String[] strings2 = strings[1].split(" ", 2);
                command = new AddCommand(Event.of(strings1[1], strings2[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                command = new WrongCommand("The description of an event cannot be empty, idiot.");
            }
            break;
        case "done":
            command = new DoneCommand(input);
            break;
        case "delete":
            command = new DeleteCommand(input);
            break;
        case "find":
            if (fullCommand.length < 2) {
                command = new WrongCommand("You have to specify a keyword.");
            } else if (fullCommand.length < 3) {
                command = new FindCommand(fullCommand[1]);
            } else {
                command = new WrongCommand("Idk.");
            }
            break;
        default:
            command = new WrongCommand("Check your input again, fool.");
            break;

        }
        return command;
    }

}
