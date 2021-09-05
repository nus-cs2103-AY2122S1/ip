package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.RemoveCommand;
import duke.exception.DukeException;
import duke.exception.IncompleteDeadlineException;
import duke.exception.IncompleteEventException;
import duke.exception.IncompleteFindException;
import duke.exception.IncompleteToDoException;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser which parses the input given by users of Duke.
 */
public class Parser {
    /**
     * Checks if dateString is in a valid date form depicted by dateFormatter.
     *
     * @param dateString String which contains a date.
     * @param dateFormatter Format which you want to check if dateString is in.
     * @return true if dateString is in valid form, else otherwise.
     */
    public static boolean isValidDate(String dateString, DateTimeFormatter dateFormatter) {
        try {
            LocalDate.parse(dateString, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if timeString is in a valid date form depicted by timeFormatter.
     *
     * @param timeString String which contains a time.
     * @param timeFormatter Format which you want to check if timeString is in.
     * @return true if timeString is in valid form, else otherwise.
     */
    public static boolean isValidTime(String timeString, DateTimeFormatter timeFormatter) {
        try {
            LocalTime.parse(timeString, timeFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if input is a remove command.
     *
     * @param input Input which is being checked.
     * @return true if input is a remove command.
     */
    public static boolean isRemove(String input) {
        String[] separated = input.split(" ");
        return separated[0].equals("remove");
    }

    /**
     * Checks if input is a done command.
     *
     * @param input Input which is being checked.
     * @return true if input is a done command.
     */
    public static boolean isDone(String input) {
        String[] separated = input.split(" ");
        return separated[0].equals("done");
    }

    /**
     * Checks if user input is a find command.
     *
     * @param input User input to check if it is a find command.
     * @return true if it is a find command, else false.
     */
    public static boolean isFind(String input) {
        String[] separated = input.split(" ");
        return separated[0].equals("find");
    }

    /**
     * Checks if input is a list command.
     *
     * @param input Input which is being checked.
     * @return true if input is a list command.
     */
    private static boolean isList(String input) {
        return input.equals("list");
    }

    /**
     * Checks if input is an event command.
     *
     * @param input Input which is being checked.
     * @return true if input is a event command.
     */
    private static boolean isEvent(String input) {
        String[] separated = input.split(" ");
        return separated[0].equals("event");
    }

    /**
     * Checks if input is a deadline command.
     *
     * @param input Input which is being checked.
     * @return true if input is a deadline command.
     */
    private static boolean isDeadline(String input) {
        String[] separated = input.split(" ");
        return separated[0].equals("deadline");
    }

    /**
     * Checks if input is a todo command.
     *
     * @param input Input which is being checked.
     * @return true if input is a todo command.
     */
    private static boolean isTodo(String input) {
        String[] separated = input.split(" ");
        return separated[0].equals("todo");
    }

    /**
     * Checks if input is a bye command.
     *
     * @param input Input which is being checked.
     * @return true if input is a bye command.
     */
    private static boolean isBye(String input) {
        return input.equals("bye");
    }

    /**
     * Splits string which contains a description and possibly a deadline of Task Objects.
     *
     * @param input Input which contains a description and possibly a deadline.
     * @param type Type of Task information the input contains.
     * @return String[] containing the description at index 0 and the deadline (if any) at index 1.
     * @throws DukeException If no deadline or description for respective Task objects.
     */
    public static String[] splitInput(String input, String type) throws DukeException {
        if (Parser.isDeadline(type) || Parser.isEvent(type)) {
            String[] str = input.split("/");

            if (str.length == 1) {
                if (type.equals("deadline")) {
                    throw new IncompleteDeadlineException();
                } else if (type.equals("event")) {
                    throw new IncompleteEventException();
                } else {
                    throw new InvalidCommandException();
                }
            } else {
                String[] first = str[0].split(" ");
                String[] second = str[1].split(" ");

                String description = getDescription(first);
                String deadline = getDeadline(second);

                return new String[]{description, deadline};
            }
        } else if (Parser.isTodo(type)) {
            String[] str = input.split(" ");

            if (str.length == 1) {
                throw new IncompleteToDoException();
            } else {
                String description = getDescription(str);
                return new String[]{description};
            }
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Gets the deadline from an array of string.
     *
     * @param strings Array which deadline will be derived from.
     * @return The deadline.
     */
    private static String getDeadline(String[] strings) {
        String deadline = "";

        for (int i = 1; i < strings.length; i++) {

            if (i == 1 && (strings[i].equals("by") || strings[i].equals("at"))) {
                // handle the case where user formatted command wrongly (include a space after "/")
                continue;
            }

            if (Parser.isValidDate(strings[i], DateTimeFormatter.ISO_LOCAL_DATE)) {
                deadline += LocalDate.parse(strings[i], DateTimeFormatter.ISO_LOCAL_DATE)
                        .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } else if (Parser.isValidTime(strings[i], DateTimeFormatter.ISO_LOCAL_TIME)) {
                deadline += LocalTime.parse(strings[i], DateTimeFormatter.ISO_LOCAL_TIME)
                        .format(DateTimeFormatter.ofPattern("hh:mm a"));
            } else {
                deadline += strings[i];
            }

            if (i != strings.length - 1) {
                deadline += " ";
            }
        }

        return deadline;
    }

    /**
     * Gets the description from an array of strings.
     *
     * @param strings Array which description would be derived from.
     * @return The description.
     */
    private static String getDescription(String[] strings) {
        String description = "";

        for (int i = 1; i < strings.length; i++) {
            description += strings[i];
            if (i != strings.length - 1) {
                description += " ";
            }
        }

        return description;
    }

    /**
     * Parses the input string and returns a command for Duke to execute.
     *
     * @param userInput Input which needs to be parsed.
     * @param ui Ui object from Duke class.
     * @param taskList TaskList object from Duke class.
     * @return Command to execute.
     * @throws DukeException If incorrect values are passed for remove or done commands.
     */
    public static Command parse(String userInput, Ui ui, TaskList taskList) throws DukeException {
        if (Parser.isList(userInput)) {
            return new ListCommand();
        } else if (Parser.isDone(userInput)) {
            return parseDoneCommand(userInput, taskList);
        } else if (Parser.isRemove(userInput)) {
            return parseRemoveCommand(userInput, taskList);
        } else if (Parser.isBye(userInput)) {
            return new ExitCommand();
        } else if (Parser.isFind(userInput)) {
            return parseFindCommand(userInput);
        } else if (Parser.isTodo(userInput)) {
            return parseToDoCommand(userInput);
        } else if (Parser.isDeadline(userInput)) {
            return parseDeadlineCommand(userInput);
        } else if (Parser.isEvent(userInput)) {
            return parseEventCommand(userInput);
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses the command string into an AddCommand which adds an
     * Event object.
     *
     * @param userInput Command which user entered.
     * @return An AddCommand which adds the new Event object.
     * @throws DukeException If insufficient values are passed in.
     */
    private static AddCommand parseEventCommand(String userInput) throws DukeException {
        String[] separated = userInput.split(" ");
        String[] str = Parser.splitInput(userInput, separated[0]);

        assert str.length == 2;

        Event add = new Event(str[0], str[1]);
        return new AddCommand(add);
    }

    /**
     * Parses the command string into an AddCommand which adds an
     * deadline object.
     *
     * @param userInput Command which user entered.
     * @return An AddCommand which adds the new Deadline object.
     * @throws DukeException If insufficient values are passed in.
     */
    private static AddCommand parseDeadlineCommand(String userInput) throws DukeException {
        String[] separated = userInput.split(" ");
        String[] str = Parser.splitInput(userInput, separated[0]);

        assert str.length == 2;

        Deadline add = new Deadline(str[0], str[1]);
        return new AddCommand(add);
    }

    /**
     * Parses the command string into an AddCommand which adds an
     * Todo object.
     *
     * @param userInput Command which user entered.
     * @return An AddCommand which adds the new Todo object.
     * @throws DukeException If insufficient values are passed in.
     */
    private static AddCommand parseToDoCommand(String userInput) throws DukeException {
        String[] separated = userInput.split(" ");
        String[] str = Parser.splitInput(userInput, separated[0]);

        assert str.length == 1;

        ToDo add = new ToDo(str[0]);
        return new AddCommand(add);
    }

    /**
     * Parses the command string into a FindCommand.
     *
     * @param userInput Command which user entered.
     * @return A FindCommand with a keyword to find.
     * @throws IncompleteFindException If insufficient values are passed in.
     */
    private static FindCommand parseFindCommand(String userInput) throws IncompleteFindException {
        String[] separated = userInput.split(" ");

        if (separated.length == 1) {
            throw new IncompleteFindException();
        }

        String keyword = separated[1];

        return new FindCommand(keyword);
    }

    /**
     * Parses the command string into a RemoveCommand.
     *
     * @param userInput Command which user entered.
     * @param taskList taskList which RemoveCommand remove from.
     * @return A RemoveCommand with index to remove.
     * @throws DukeException If insufficient values are passed in.
     */
    private static RemoveCommand parseRemoveCommand(
            String userInput, TaskList taskList) throws DukeException {

        String[] separated = userInput.split(" ");

        if (separated.length < 2 || !separated[1].matches("\\d+")
                || Integer.valueOf(separated[1]) > taskList.getSize()) {
            throw new DukeException("Please key in valid number to remove.");
        } else {
            return new RemoveCommand(Integer.valueOf(separated[1]) - 1);
        }
    }

    /**
     * Parses the command string into a DoneCommand.
     *
     * @param userInput Command which user entered.
     * @param taskList taskList which DoneCommand marks as done from.
     * @return A DoneCommand with index to mark as done.
     * @throws DukeException If insufficient values are passed in.
     */
    private static DoneCommand parseDoneCommand(
            String userInput, TaskList taskList) throws DukeException {

        String[] separated = userInput.split(" ");

        if (separated.length < 2 || !separated[1].matches("\\d+")
                || Integer.valueOf(separated[1]) > taskList.getSize()) {
            throw new DukeException("Please key in valid number to mark as done.");
        } else {
            int index = Integer.valueOf(separated[1]) - 1;
            return new DoneCommand(index);
        }
    }
}
