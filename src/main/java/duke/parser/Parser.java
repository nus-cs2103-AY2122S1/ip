package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.stream.Collectors;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

/**
 * Represents a Parser that deals with making sense
 * of the user inputs.
 *
 * @author ruiquan
 */
public class Parser {
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_QUIT = "quit";

    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_LS = "ls";

    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_FINISH = "finish";
    private static final String COMMAND_COMPLETE = "complete";

    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_REMOVE = "remove";

    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_SEARCH = "search";

    /**
     * Takes in a String input by the user and
     * returns a Command.
     *
     * @param input The String input by the user.
     * @return A Command.
     * @throws DukeException If the user input is not in the correct form.
     */
    public Command parse(String input) throws DukeException {
        String command = extractCommand(input);
        String details = extractDetails(input);
        if (isExitCommand(command)) {
            return handleExit();
        } else if (isListCommand(command)) {
            return handleList();
        } else if (isToDoCommand(command)) {
            return handleToDo(details);
        } else if (isDeadlineCommand(command)) {
            return handleDeadline(details);
        } else if (isEventCommand(command)) {
            return handleEvent(details);
        } else if (isDoneCommand(command)) {
            return handleDone(details);
        } else if (isDeleteCommand(command)) {
            return handleDelete(details);
        } else if (isFindCommand(command)) {
            return handleFind(details);
        } else {
            String message = String.format("I'm sorry but I don't know what is %s", input);
            throw new DukeException(message);
        }
    }

    private boolean isExitCommand(String command) {
        return command.equals(COMMAND_EXIT) || command.equals(COMMAND_BYE) || command.equals(COMMAND_QUIT);
    }

    private boolean isListCommand(String command) {
        return command.equals(COMMAND_LIST) || command.equals(COMMAND_LS);
    }

    private boolean isDoneCommand(String command) {
        return command.equals(COMMAND_DONE) || command.equals(COMMAND_FINISH) || command.equals(COMMAND_COMPLETE);
    }

    private boolean isDeleteCommand(String command) {
        return command.equals(COMMAND_DELETE) || command.equals(COMMAND_REMOVE);
    }

    private boolean isFindCommand(String command) {
        return command.equals(COMMAND_FIND) || command.equals(COMMAND_SEARCH);
    }

    private boolean isToDoCommand(String command) {
        return command.equals(COMMAND_TODO);
    }

    private boolean isDeadlineCommand(String command) {
        return command.equals(COMMAND_DEADLINE);
    }

    private boolean isEventCommand(String command) {
        return command.equals(COMMAND_EVENT);
    }

    private String extractCommand(String input) throws DukeException {
        String[] inputArr = input.split(" ");
        if (inputArr.length <= 0) {
            throw new DukeException("Please don't give me an empty input. My life is already empty enough.");
        }

        return inputArr[0].toLowerCase().trim();
    }

    private String extractDetails(String input) {
        String[] inputArr = input.split(" ");
        String details = Arrays
                .stream(inputArr)
                .skip(1)
                .map(String::trim)
                .filter(s -> !s.equals(""))
                .collect(Collectors.joining(" "));
        return details;
    }

    private Command handleExit() {
        return new ExitCommand();
    }

    private Command handleList() {
        return new ListCommand();
    }

    private Command handleToDo(String details) throws DukeException {
        if (details.equals("")) {
            throw new DukeException("Dude, the description of a todo cannot be empty la");
        }

        return new AddCommand(new ToDo(details));
    }

    private Command handleDeadline(String details) throws DukeException {
        if (details.equals("")) {
            throw new DukeException("Dude, the details of a deadline cannot be empty la");
        }

        String[] argArr = details.split("/by");
        if (argArr.length <= 1) {
            throw new DukeException("Please provide both the deadline description and date");
        }

        String description = argArr[0].trim();
        try {
            LocalDate date = LocalDate.parse(argArr[1].trim());
            return new AddCommand(new Deadline(description, date));
        } catch (DateTimeParseException e) {
            throw new DukeException("Date must be in the form YYYY-MM-DD");
        }
    }

    private Command handleEvent(String details) throws DukeException {
        if (details.equals("")) {
            throw new DukeException("Dude, the details of an event cannot be empty la");
        }

        String[] argArr = details.split("/at");
        if (argArr.length <= 1) {
            throw new DukeException("Please provide both the event description and date");
        }

        String description = argArr[0].trim();
        try {
            LocalDate date = LocalDate.parse(argArr[1].trim());
            return new AddCommand(new Event(description, date));
        } catch (DateTimeParseException e) {
            throw new DukeException("Date must be in the format YYYY-MM-DD");
        }
    }

    private Command handleDone(String details) throws DukeException {
        try {
            int index = Integer.parseInt(details);
            return new DoneCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("You need to provide the index number of the task to be mark as done");
        }
    }

    private Command handleDelete(String details) throws DukeException {
        try {
            int index = Integer.parseInt(details);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("You need to provide the index number of the task to be deleted");
        }
    }

    private Command handleFind(String details) throws DukeException {
        if (details.equals("")) {
            throw new DukeException("Dude, the search query cannot be empty la");
        }

        return new FindCommand(details);
    }
}
