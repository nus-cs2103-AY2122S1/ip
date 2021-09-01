package duke.util;

import duke.command.*;
import duke.exception.InvalidInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class Parser {


    public static String parseInput(String input) {
        return input.trim().replaceAll(" +", " ");
    }

    //todo: use switch statement and everything to create cases
    public static Command parseCommand(String input) throws InvalidInputException {

        String[] fullCommand = input.split(" ", 2);
        Command command = null;
        switch (fullCommand[0]) {
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
            default:
                command = new WrongCommand("Check your input again, fool.");
                break;

        }
        return command;
    }

}
