package duke.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.commands.*;

import duke.exceptions.InvalidInputException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class Parser {

    /**
     * Return command according to user input
     * 
     * @param command the user input string
     * @return the corresponding command according to given command String
     * @throws InvalidInputException if input is invalid
     */
    public static Command parse(String command) throws InvalidInputException {
        String[] commands = command.split(" ", 2);
        command = commands[0];

        String description = commands.length == 2 ? commands[1] : "";

        switch (command) {
            case "bye":
                return parseExitCommand(description);
            case "list":
                return parseListCommand(description);
            case "sort":
                return parseSortCommand(description);
            case "done":
                return parseDoneCommand(description);
            case "delete":
                return parseDeleteCommand(description);
            case "find":
                return parseFindCommand(description);
            case "todo":
                return parseTodoCommand(description);
            case "deadline":
                return parseDeadlineCommand(description);
            case "event":
                return parseEventCommand(description);
            default:
                throw new InvalidInputException("Invalid command");
        }
    }

    /**
     * Parses description for Exit command
     * Return ExitCommand if description is empty
     *
     * @param description description given by user
     * @return ExitCommand
     * @throws InvalidInputException if description is not empty
     */
    public static Command parseExitCommand(String description) throws InvalidInputException {
        if (description.isBlank()) {
            return new ExitCommand();
        } else {
            throw new InvalidInputException("Did you mean \'bye\'?");
        }
    }

    /**
     * Parses description for List command
     * Return ListCommand if description is empty
     *
     * @param description description given by user
     * @return ListCommand
     * @throws InvalidInputException if description is not empty
     */
    public static Command parseListCommand(String description) throws InvalidInputException {
        if (description.isBlank()) {
            return new ListCommand();
        } else {
            throw new InvalidInputException("Did you mean \'list\'?");
        }
    }

    /**
     * Parses description for Sort command
     * Return SortCommand if description is empty
     *
     * @param description description given by user
     * @return SortCommand
     * @throws InvalidInputException if description is not empty
     */
    public static Command parseSortCommand(String description) throws InvalidInputException {
        if (description.isBlank()) {
            return new SortCommand();
        } else {
            throw new InvalidInputException("Did you mean \'sort\'?");
        }
    }

    /**
     * Parses description for Done command
     * Return DoneCommand if description is an integer index
     *
     * @param description description given by user
     * @return DoneCommand
     * @throws InvalidInputException if description is invalid
     */
    public static Command parseDoneCommand(String description) throws InvalidInputException {
        try {
            int index = Integer.parseInt(description) - 1;

            return new DoneCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(
                    "Invalid description!\n" +
                    "The command format for Done is:\n" +
                    "done {TaskIndex}");
        }

    }

    /**
     * Parses description for Delete command
     * Return DeleteCommand if description is an integer index
     *
     * @param description description given by user
     * @return DeleteCommand
     * @throws InvalidInputException if description is invalid
     */
    public static Command parseDeleteCommand(String description) throws InvalidInputException {
        try {
            int index = Integer.parseInt(description) - 1;

            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(
                    "Invalid description!\n" +
                    "The command format for Delete is:\n" +
                    "delete {TaskIndex}");
        }

    }

    /**
     * Parses description for Find command
     * Return FindCommand if description is a string
     *
     * @param description description given by user
     * @return FindCommand
     * @throws InvalidInputException if description is invalid
     */
    public static Command parseFindCommand(String description) throws InvalidInputException {
        if (description.isBlank()) {
            throw new InvalidInputException("find's description cannot be empty!");
        } else {
            return new FindCommand(description);
        }
    }

    /**
     * Parses description for Todo command
     * Return AddCommand containing Todo Task if description is a string
     *
     * @param description description given by user
     * @return AddCommand containing Todo Task
     * @throws InvalidInputException if description is invalid
     */
    public static Command parseTodoCommand(String description) throws InvalidInputException {
        if (description.isBlank()) {
            throw new InvalidInputException("todo's description cannot be empty!");
        } else {
            Task newTask = new ToDo(description);

            return new AddCommand(newTask);
        }
    }

    /**
     * Parses description for Deadline command
     * Return AddCommand containing Deadline Task if description is of following format:
     * "{description} /by {date}"
     *
     * @param description description given by user
     * @return AddCommand containing Deadline Task
     * @throws InvalidInputException if description is invalid
     */
    public static Command parseDeadlineCommand(String description) throws InvalidInputException {
        if (description.isBlank()) {
            throw new InvalidInputException("deadline's description cannot be empty!");
        }

        String[] fields = description.split("/by", 2);

        if (fields.length == 2) {
            try {
                description = fields[0].trim();
                LocalDate date = LocalDate.parse(fields[1].trim());

                Task newTask = new Deadline(description, date);

                return new AddCommand(newTask);
            } catch (DateTimeParseException e) {
                throw new InvalidInputException(
                        "Invalid Date format!\n" +
                        "The Date format is:\n" +
                        "YYYY-MM-DD");
            }
        } else {
            throw new InvalidInputException(
                    "Invalid description!\n" +
                    "The command format for Deadline is:\n" +
                    "deadline /by {YYYY-MM-DD}");
        }
    }

    /**
     * Parses description for Event command
     * Return AddCommand containing Event Task if description is of following format:
     * "{description} /from {start date} /to {end date}"
     *
     * @param description description given by user
     * @return AddCommand containing Event Task
     * @throws InvalidInputException if description is invalid
     */
    public static Command parseEventCommand(String description) throws InvalidInputException {
        if (description.isBlank()) {
            throw new InvalidInputException("event's description cannot be empty!");
        }

        String[] fields = description.split("/from", 2);

        if (fields.length == 2) {
            description = fields[0].trim();
            String[] dateFields = fields[1].split("/to", 2);

            if (dateFields.length == 2) {
                try {
                    LocalDate startDate = LocalDate.parse(dateFields[0].trim());
                    LocalDate endDate = LocalDate.parse(dateFields[1].trim());

                    Task newTask = new Event(description, startDate, endDate);

                    return new AddCommand(newTask);
                } catch (DateTimeParseException e) {
                    throw new InvalidInputException(
                            "Invalid Date format!\n" +
                                    "The Date format is:\n" +
                                    "YYYY-MM-DD");
                }

            }
        }

        throw new InvalidInputException(
                "Invalid description!\n" +
                "The command format for Event is:\n" +
                "event /from {YYYY-MM-DD} /to {YYYY-MM-DD}");
    }

}