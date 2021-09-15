package duke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.AddCommand;
import duke.command.AddCommandType;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.RescheduleCommand;
import duke.command.UpdateCommand;
import duke.task.TaskList;

/**
 * The type Parser that parses user input and returns the appropriate command.
 */
public class Parser {

    /** User-inputted string. */
    private final String userInput;
    /** List of tasks to pass to the commands. */
    private final TaskList tasks;

    /**
     * Instantiates a new Parser.
     *
     * @param userInput the user-inputted string.
     * @param tasks     list of tasks to pass to the commands generated.
     */
    public Parser(String userInput, TaskList tasks) {
        this.userInput = userInput.trim();
        this.tasks = tasks;
    }

    /**
     * Checks operation command to be executed.
     *
     * @return the command to be executed.
     * @throws DukeException the duke exception for unrecognised commands.
     */
    public final Command checkOperation() throws DukeException {
        if (isBye()) {
            return new ExitCommand();
        } else if (isList()) {
            return new ListCommand(tasks);
        } else if (isDone()) {
            return createDoneCommand();
        } else if (isDelete()) {
            return createDeleteCommand();
        } else if (isFind()) {
            return createFindCommand();
        } else if (isTodo()) {
            return createTodoCommand();
        } else if (isEvent()) {
            return createEventCommand();
        } else if (isDeadline()) {
            return createDeadlineCommand();
        } else if (isReschedule()) {
            return createRescheduleCommand();
        } else if (isUpdate()) {
            return createUpdateCommand();
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Checks if the exit command is to be executed.
     *
     * @return boolean.
     */
    private boolean isBye() {
        return this.userInput.equals("bye");
    }

    /**
     * Checks if the list command is to be executed.
     *
     * @return boolean.
     */
    private boolean isList() {
        return this.userInput.equals("list");
    }

    /**
     * Checks if the done command is to be executed.
     *
     * @return boolean.
     */
    private boolean isDone() {
        Pattern donePattern = Pattern.compile("^done\\h\\d+$");
        Matcher doneMatcher = donePattern.matcher(userInput);
        return doneMatcher.find();
    }

    /**
     * Creates and returns a new instance of DoneCommand to be executed.
     *
     * @return new instance of DoneCommand.
     */
    private Command createDoneCommand() {
        String[] inputs = userInput.split(" ");
        int idx = Integer.parseInt(inputs[1]) - 1;
        return new DoneCommand(idx, tasks);
    }

    /**
     * Checks if the delete command is to be executed.
     *
     * @return boolean.
     */
    private boolean isDelete() {
        Pattern deletePattern = Pattern.compile("^delete\\h\\d+$");
        Matcher deleteMatcher = deletePattern.matcher(userInput);
        return deleteMatcher.find();
    }

    /**
     * Creates and returns a new instance of DeleteCommand to be executed.
     *
     * @return new instance of DeleteCommand.
     */
    private Command createDeleteCommand() {
        String[] inputs = userInput.split(" ");
        int idx = Integer.parseInt(inputs[1]) - 1;
        return new DeleteCommand(idx, tasks);
    }

    /**
     * Checks if the add command is to be executed for a Todo task.
     *
     * @return boolean.
     */
    private boolean isTodo() {
        Pattern todoPattern = Pattern.compile("^todo\\h\\w.*");
        Matcher todoMatcher = todoPattern.matcher(userInput);
        return todoMatcher.find();
    }

    /**
     * Creates and returns a new instance of AddCommand to be executed, for AddCommandType.todo.
     *
     * @return new AddCommand for creating a Todo.
     */
    private Command createTodoCommand() {
        String[] inputs = userInput.split(" ", 2);
        return new AddCommand(AddCommandType.todo, inputs[1], tasks);
    }

    /**
     * Checks if the add command is to be executed for an Event task.
     *
     * @return boolean.
     */
    private boolean isEvent() {
        Pattern eventDateTimePattern =
                Pattern.compile("^event\\h\\w.*/at\\h\\d{4}-\\d{2}-\\d{2}\\h\\d{2}:\\d{2}\\h*$");
        Matcher eventDateTimeMatcher = eventDateTimePattern.matcher(userInput);

        Pattern eventDatePattern =
                Pattern.compile("^event\\h\\w.*/at\\h\\d{4}-\\d{2}-\\d{2}\\h*$");
        Matcher eventDateMatcher = eventDatePattern.matcher(userInput);

        return eventDateTimeMatcher.find() || eventDateMatcher.find();
    }

    /**
     * Creates and returns a new instance of AddCommand to be executed, for AddCommandType.event.
     *
     * @return new AddCommand for creating an Event.
     */
    private Command createEventCommand() {
        String[] inputs = userInput.split(" ", 2);
        String[] args = inputs[1].split(" /at ", 2);
        String[] datetimeArgs = args[1].split(" ", 2);

        if (datetimeArgs.length > 1) {
            return new AddCommand(AddCommandType.event, args[0], tasks, datetimeArgs[0], datetimeArgs[1]);
        }
        return new AddCommand(AddCommandType.event, args[0], tasks, datetimeArgs[0]);
    }

    /**
     * Checks if the add command is to be executed for a Deadline task.
     *
     * @return boolean.
     */
    private boolean isDeadline() {
        Pattern deadlineDateTimePattern =
                Pattern.compile("^deadline\\h\\w.*/by\\h\\d{4}-\\d{2}-\\d{2}\\h\\d{2}:\\d{2}\\h*$");
        Matcher deadlineDateTimeMatcher = deadlineDateTimePattern.matcher(userInput);

        Pattern deadlineDatePattern =
                Pattern.compile("^deadline\\h\\w.*/by\\h\\d{4}-\\d{2}-\\d{2}\\h*$");
        Matcher deadlineDateMatcher = deadlineDatePattern.matcher(userInput);

        return deadlineDateTimeMatcher.find() || deadlineDateMatcher.find();
    }

    /**
     * Creates and returns a new instance of AddCommand to be executed, for AddCommandType.deadline.
     *
     * @return new AddCommand for creating a Deadline.
     */
    private Command createDeadlineCommand() {
        String[] inputs = userInput.split(" ", 2);
        String[] args = inputs[1].split(" /by ", 2);
        String[] datetimeArgs = args[1].split(" ", 2);

        if (datetimeArgs.length > 1) {
            return new AddCommand(AddCommandType.deadline, args[0], tasks, datetimeArgs[0], datetimeArgs[1]);
        }
        return new AddCommand(AddCommandType.deadline, args[0], tasks, datetimeArgs[0]);
    }

    /**
     * Checks if the find command is to be executed for finding tasks with matching substrings.
     *
     * @return boolean.
     */
    private boolean isFind() {
        Pattern findPattern = Pattern.compile("^find\\h\\w.*");
        Matcher findMatcher = findPattern.matcher(userInput);
        return findMatcher.find();
    }

    /**
     * Creates and returns a new instance of FindCommand to be executed.
     *
     * @return new instance of FindCommand.
     */
    private Command createFindCommand() {
        String substring = userInput.split(" ", 2)[1];
        return new FindCommand(substring, tasks);
    }

    /**
     * Checks if the rescheduling command is to be executed for rescheduling tasks with date and/or time constraints.
     *
     * @return boolean.
     */
    private boolean isReschedule() {
        Pattern rescheduleDateTimePattern =
                Pattern.compile("^reschedule\\h\\d+\\h/to\\h\\d{4}-\\d{2}-\\d{2}\\h\\d{2}:\\d{2}\\h*$");
        Matcher rescheduleDateTimeMatcher = rescheduleDateTimePattern.matcher(userInput);

        Pattern rescheduleDatePattern =
                Pattern.compile("^reschedule\\h\\d+\\h/to\\h\\d{4}-\\d{2}-\\d{2}\\h*$");
        Matcher rescheduleDateMatcher = rescheduleDatePattern.matcher(userInput);

        return rescheduleDateTimeMatcher.find() || rescheduleDateMatcher.find();
    }

    /**
     * Creates and returns a new instance of RescheduleCommand to be executed.
     *
     * @return new instance of RescheduleCommand.
     */
    private Command createRescheduleCommand() {
        String[] inputs = userInput.split(" ");
        int idx = Integer.parseInt(inputs[1]) - 1;
        String dateString = inputs[3];

        if (inputs.length > 4) {
            String timeString = inputs[4];
            return new RescheduleCommand(idx, tasks, dateString, timeString);
        }

        return new RescheduleCommand(idx, tasks, dateString);
    }

    /**
     * Checks if the update command is to be executed to update the description of the task.
     *
     * @return boolean.
     */
    private boolean isUpdate() {
        Pattern updatePattern = Pattern.compile("^update\\h\\d+\\h/to\\h.*");
        Matcher updateMatcher = updatePattern.matcher(userInput);
        return updateMatcher.find();
    }

    /**
     * Creates and returns a new instance of UpdateCommand to be executed.
     *
     * @return new instance of UpdateCommand.
     */
    private Command createUpdateCommand() {
        String[] inputs = userInput.split(" ", 2);
        String[] args = inputs[1].split(" /to ", 2);
        int idx = Integer.parseInt(args[0]) - 1;
        String newDescription = args[1];
        return new UpdateCommand(idx, tasks, newDescription);

    }
}
