package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.command.*;

/**
 * This class represents a Parser, which parses the input given by the user.
 */
public class Parser {

    /**
     * Parses the input given by the user and returns the necessary command to be executed.
     *
     * @param input input given by user.
     * @return necessary command to be executed.
     */
    public static Command parse(String input) {
        String[] splitText = input.trim().split("\\s+", 2);
        String command = splitText[0];
        Command output = new EmptyCommand();
        if (command.equals("welcome")) {
            output = new WelcomeCommand();
        }
        if (command.equals("todo")) {
            output = handleToDo(splitText[1]);
        }
        if (command.equals("event")) {
            output = handleEvent(splitText[1]);
        }
        if (command.equals("deadline")) {
            output = handleDeadline(splitText[1]);
        }
        if (command.equals("list")) {
            output = new ListCommand();
        }
        if ("done".equals(command)) {
            output = handleDone(splitText[1]);
        }
        if ("delete".equals(command)) {
            output = handleDelete(splitText[1]);
        }
        if ("find".equals(command)) {
            output = handleFind(splitText[1]);
        }
        if ("tag".equals(command)) {
            output = handleTag(splitText[1]);
        }
        if ("bye".equals(command)) {
            output = new ByeCommand();
        }
        assert !output.equals(null): "output should not be null";
        return output;
    }

    /**
     * Handles the case where the user inputs 'todo'
     *
     * @param name name of task
     * @return the appropriate command
     */
    private static Command handleToDo(String name) {
        try {
            return new ToDoCommand(name);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new UnexpectedCommand("array out of bounds exception, todo");
        }
    }

    /**
     * Handles the case where the user inputs 'event'
     *
     * @param task name of task
     * @return the appropriate command
     */
    private static Command handleEvent(String task) {
        try {
            String[] furtherSplitEvent = task.trim().split("/at ");
            String eventTask = furtherSplitEvent[0];
            LocalDateTime eventTime = LocalDateTime.parse(furtherSplitEvent[1], Ui.TIME_FORMATTER);
            return new EventCommand(eventTask, eventTime);
        } catch (DateTimeParseException e) {
            return new UnexpectedCommand("date time parse exception");
        } catch (ArrayIndexOutOfBoundsException e) {
            return new UnexpectedCommand("array out of bounds exception, event");
        }
    }

    /**
     * Handles the case where the user inputs 'deadline'
     *
     * @param task name of task
     * @return the appropriate command
     */
    private static Command handleDeadline(String task) {
        try {
            String[] furtherSplitDeadline = task.trim().split("/by ");
            String deadlineTask = furtherSplitDeadline[0];
            LocalDateTime deadlineBy = LocalDateTime.parse(furtherSplitDeadline[1], Ui.TIME_FORMATTER);
            return new DeadlineCommand(deadlineTask, deadlineBy);
        } catch (DateTimeParseException e) {
            return new UnexpectedCommand("date time parse exception");
        } catch (ArrayIndexOutOfBoundsException e) {
            return new UnexpectedCommand("array out of bounds exception, deadline");
        }
    }

    /**
     * Handles the case where the user inputs 'done'
     *
     * @param indexStr index of the item to be completed
     * @return the appropriate command
     */
    private static Command handleDone(String indexStr) {
        try {
            int index = Integer.parseInt(indexStr);
            return new DoneCommand(index);
        } catch (NumberFormatException e) {
            return new UnexpectedCommand("number format exception");
        } catch (ArrayIndexOutOfBoundsException e) {
            return new UnexpectedCommand("array out of bounds exception");
        }
    }

    /**
     * Handles the case where the user inputs 'delete'
     *
     * @param indexStr index of the item to be deleted
     * @return the appropriate command
     */
    private static Command handleDelete(String indexStr) {
        try {
            int index = Integer.parseInt(indexStr);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            return new UnexpectedCommand("number format exception");
        } catch (ArrayIndexOutOfBoundsException e) {
            return new UnexpectedCommand("array out of bounds exception");
        }
    }

    /**
     * Handles the case where the user inputs 'find'
     *
     * @param keyword keyword to be searched
     * @return the appropriate command
     */
    private static Command handleFind(String keyword) {
        try {
            return new FindCommand(keyword);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new UnexpectedCommand("array out of bounds exception, find");
        }
    }

    /**
     * Handles the case where the user inputs 'tag'
     *
     * @param tag tag to be added
     * @return the appropriate command
     */
    private static Command handleTag(String tag) {
        try {
            String[] furtherSplitTag = tag.trim().split("#");
            int index = Integer.parseInt(furtherSplitTag[0].substring(0, 1));
            return new TagCommand(index, furtherSplitTag);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new UnexpectedCommand("array out of bounds exception, tag");
        }
    }
}
