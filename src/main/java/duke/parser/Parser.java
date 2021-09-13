package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Set;
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
            String message = String.format("*groan* I'm sorry but I don't know what is %s", input);
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
        if (input.trim().equals("")) {
            throw new DukeException("*growl* Input cannot be empty");
        }

        String[] inputArr = input.split(" ");
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
            throw new DukeException("*growl* The description of todo cannot be empty");
        }

        return new AddCommand(new ToDo(details));
    }

    private Command handleDeadline(String details) throws DukeException {
        String[] argArr = details.split("/by");
        String description = argArr[0].trim();
        if (description.equals("")) {
            throw new DukeException("*growl* The description of deadline cannot be empty. "
                    + "Format: deadline <DESCRIPTION> /by <DATE:YYYY-MM-DD>");
        }

        try {
            LocalDate date = LocalDate.parse(argArr[1].trim());
            return new AddCommand(new Deadline(description, date));
        } catch (DateTimeParseException e) {
            throw new DukeException("*growl* Date must be in the format: YYYY-MM-DD. "
                    + "Format: deadline <DESCRIPTION> /by <DATE:YYYY-MM-DD>");
        }
    }

    private Command handleEvent(String details) throws DukeException {
        String[] argArr = details.split("/at");
        String description = argArr[0].trim();
        if (description.equals("")) {
            throw new DukeException("*growl* The description of event cannot be empty. "
                    + "Format: event <DESCRIPTION> /at <DATE:YYYY-MM-DD>");
        }

        try {
            LocalDate date = LocalDate.parse(argArr[1].trim());
            return new AddCommand(new Event(description, date));
        } catch (DateTimeParseException e) {
            throw new DukeException("*growl* Date must be in the format: YYYY-MM-DD. "
                    + "Format: event <DESCRIPTION> /at <DATE:YYYY-MM-DD>");
        }
    }

    private Command handleDone(String details) throws DukeException {
        String[] argArr = details.split(" ");
        Set<Integer> indices;
        try {
            indices = Arrays.stream(argArr).map(Integer::parseInt).collect(Collectors.toSet());
        } catch (NumberFormatException e) {
            throw new DukeException("*growl* The index entered is not valid. Format: done <INDEX_1> [INDEX_2] ...");
        }
        return new DoneCommand(indices);
    }

    private Command handleDelete(String details) throws DukeException {
        String[] argArr = details.split(" ");
        try {
            if (argArr.length == 1) {
                int index = Integer.parseInt(argArr[0]);
                return new DeleteCommand(index);
            } else if (argArr.length == 2) {
                int fromIndex = Integer.parseInt(argArr[0]) - 1;
                int toIndex = Integer.parseInt(argArr[1]);
                return new DeleteCommand(fromIndex, toIndex);
            } else {
                throw new DukeException("*growl* The index entered is not valid. "
                        + "Format: delete <INDEX> or delete <FROM_INDEX> <TO_INDEX>");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("*growl* The index entered is not valid. "
                    + "Format: delete <INDEX> or delete <FROM_INDEX> <TO_INDEX>");
        }
    }

    private Command handleFind(String details) throws DukeException {
        if (details.equals("")) {
            throw new DukeException("*growl* The search query cannot be empty");
        }

        return new FindCommand(details);
    }
}
