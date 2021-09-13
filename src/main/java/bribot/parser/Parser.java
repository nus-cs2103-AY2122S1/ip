package bribot.parser;

import bribot.command.AddCommand;
import bribot.command.Command;
import bribot.command.DeleteCommand;
import bribot.command.DoneCommand;
import bribot.command.ExceptionCommand;
import bribot.command.ExitCommand;
import bribot.command.FindCommand;
import bribot.command.ListCommand;
import bribot.command.SortCommand;
import bribot.command.UnknownCommand;
import bribot.exception.DukeException;
import bribot.task.Deadline;
import bribot.task.Event;
import bribot.task.Task;
import bribot.task.Todo;

/**
 * Represents the parser that parses user input to make sense of the given input and produce
 * the appropriate command.
 */
public class Parser {
    /**
     * Takes in a String input and parses it to produce the appropriate command
     * @param input the user input.
     * @return the appropriate command.
     */
    public static Command parse(String input) {
        try {
            String[] words = input.split(" ", 2);
            String keyWord = words[0];
            String rest;
            String[] splitRest;
            String description;
            String[] dateTimeArr;
            String dateString;
            String timeString;
            int index;
            Task task;

            switch (keyWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "sort":
                return new SortCommand();
            case "done":
                if (words.length < 2) {
                    throw new DukeException("☹ OOPS!!!"
                            + " Please specify which task you wish to complete.");
                }
                assert words.length > 1 : "There should be a number to specify which task.";
                rest = words[1];
                index = Integer.parseInt(rest) - 1;
                return new DoneCommand(index);
            case "delete":
                if (words.length < 2) {
                    throw new DukeException("☹ OOPS!!!"
                            + " Please specify which task you wish to complete.");
                }
                assert words.length > 1 : "There should be a number to specify which task.";
                rest = words[1];
                index = Integer.parseInt(rest) - 1;
                return new DeleteCommand(index);
            case "todo":
                if (words.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
                }
                assert words.length > 1 : "There should be a description of the task.";
                rest = words[1];
                task = new Todo(rest);
                return new AddCommand(task);
            case "deadline":
                if (words.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
                }
                assert words.length > 1 : "There should be a description of the task.";
                rest = words[1];
                splitRest = rest.split(" /by ");
                if (splitRest.length < 2) {
                    throw new DukeException("☹ OOPS!!! Please ensure that the '/by' keyword is used and "
                            + "that a description and due date is given.");
                }
                description = splitRest[0];
                dateTimeArr = splitRest[1].split(" ");
                if (dateTimeArr.length != 2) {
                    throw new DukeException("Oops! Make sure that your date and time is valid"
                            + " and is formatted as 'dd/MM/yyyy HHmm'.");
                }
                dateString = dateTimeArr[0];
                timeString = dateTimeArr[1];
                task = new Deadline(description, dateString, timeString);
                return new AddCommand(task);
            case "event":
                if (words.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
                }
                rest = words[1];
                splitRest = rest.split(" /at ");
                if (splitRest.length < 2) {
                    throw new DukeException("☹ OOPS!!! Please ensure that the '/at' keyword is used and "
                            + "that a description a timing is given.");
                }
                description = splitRest[0];
                dateTimeArr = splitRest[1].split(" ");
                if (dateTimeArr.length != 2) {
                    throw new DukeException("Oops! Make sure that your date and time is valid"
                            + " and is formatted as 'dd/MM/yyyy HHmm'.");
                }
                dateString = dateTimeArr[0];
                timeString = dateTimeArr[1];
                task = new Event(description, dateString, timeString);
                return new AddCommand(task);
            case "find":
                if (words.length < 2) {
                    throw new DukeException("☹ OOPS!!! Please provide some words to find in the list of tasks.");
                }
                rest = words[1];
                return new FindCommand(rest);
            default:
                return new UnknownCommand();
            }
        } catch (DukeException e) {
            return new ExceptionCommand(e);
        }
    }
}
