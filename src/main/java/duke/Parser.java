package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a Parser class. Parses user input into commands.
 */
public class Parser {
    private final TaskList tasks;

    /**
     * Constructs a Parser.
     *
     * @param tasks TaskList to send commands to.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses a command and manipulates the TaskList accordingly.
     *
     * @param command String representation of command.
     * @return An array of message strings.
     * @throws DukeException If an invalid command is given.
     */
    public String[] parseCommand(String command) throws DukeException {
        switch(getFirstWord(command)) {
        case "bye":
            return parseByeCommand();
        case "list":
            return parseListCommand();
        case "find":
            return parseFindCommand(command);
        case "schedule":
            return parseScheduleCommand(command);
        case "done":
            return parseDoneCommand(command);
        case "delete":
            return parseDeleteCommand(command);
        case "todo":
            return parseTodoCommand(command);
        case "deadline":
            return parseDeadlineCommand(command);
        case "event":
            return parseEventCommand(command);
        default:
            return parseUnknownCommand();
        }
    }

    /**
     * Retrieves the first word of a command.
     *
     * @param command Command as string.
     * @return String representing first word.
     */
    private String getFirstWord(String command) {
        int index = command.indexOf(' ');
        if (index == -1) {
            return command;
        }
        return command.substring(0, index);
    }

    /**
     * Parses a bye command.
     *
     * @return A bye message which will terminate the program.
     */
    private String[] parseByeCommand() {
        return new String[] {Ui.BYE_MSG};
    }

    /**
     * Parses a list command.
     *
     * @return String array of task list.
     */
    private String[] parseListCommand() {
        return tasks.getTaskStrings();
    }

    /**
     * Parses a find command.
     *
     * @param command Input from user.
     * @return String array of matching tasks.
     * @throws DukeException If query not specified properly.
     */
    private String[] parseFindCommand(String command) throws DukeException {
        int cutOffIndex = "find ".length();
        if (command.length() <= cutOffIndex) {
            throw new DukeException("Invalid find command! Use find [search query]!");
        }
        return tasks.findMatchingTasks(command.substring(cutOffIndex));
    }

    /**
     * Parses a schedule command.
     *
     * @param command Input from user.
     * @return String array of matching tasks.
     * @throws DukeException If command not formatted properly.
     */
    private String[] parseScheduleCommand(String command) throws DukeException {
        int cutOffIndex = "schedule ".length();
        if (command.length() <= cutOffIndex) {
            throw new DukeException("Invalid schedule command!");
        }
        return tasks.getSchedule(parseTimeString(command.substring(cutOffIndex)));
    }

    /**
     * Parses a done command.
     *
     * @param command Input from user.
     * @return Affirmation of completion of task.
     * @throws DukeException If index out of range or not specified properly.
     */
    private String[] parseDoneCommand(String command) throws DukeException {
        int cutOffIndex = "done ".length();
        if (command.length() <= cutOffIndex) {
            throw new DukeException("Invalid done command!");
        }
        return tasks.markTask(Integer.parseInt(command.substring(cutOffIndex)) - 1);
    }

    /**
     * Parses a delete command.
     *
     * @param command Input from user.
     * @return Affirmation of deletion of task.
     * @throws DukeException If index out of range or not specified properly.
     */
    private String[] parseDeleteCommand(String command) throws DukeException {
        int cutOffIndex = "delete ".length();
        if (command.length() <= cutOffIndex) {
            throw new DukeException("Invalid delete command!");
        }
        return tasks.deleteTask(Integer.parseInt(command.substring(cutOffIndex)) - 1);
    }

    /**
     * Parses a todo command.
     *
     * @param command Input from user.
     * @return Affirmation of todo created.
     * @throws DukeException if invalid description given.
     */
    private String[] parseTodoCommand(String command) throws DukeException {
        int cutOffIndex = "todo ".length();
        if (command.length() <= cutOffIndex) {
            throw new DukeException("Don't give me an empty todo!");
        }
        return tasks.addTask(new Todo(command.substring(cutOffIndex)));
    }

    /**
     * Parses a deadline command.
     *
     * @param command Input from user.
     * @return Affirmation of deadline created.
     * @throws DukeException if invalid description or time.
     */
    private String[] parseDeadlineCommand(String command) throws DukeException {
        int cutOffIndex = "deadline ".length();
        if (command.length() <= cutOffIndex) {
            throw new DukeException("Don't give me an empty deadline!");
        }
        int byIndex = command.indexOf(" /by");
        if (byIndex == -1) {
            throw new DukeException("/by not found.");
        }
        int timeIndex = byIndex + " /by ".length();
        return tasks.addTask(new Deadline(
                command.substring(cutOffIndex, byIndex),
                parseTimeString(command.substring(timeIndex))));
    }

    /**
     * Parses an event command.
     * @param command Input from user.
     * @return Affirmation of event created.
     * @throws DukeException If invalid description or time.
     */
    private String[] parseEventCommand(String command) throws DukeException {
        int cutOffIndex = "event ".length();
        if (command.length() <= cutOffIndex) {
            throw new DukeException("Don't give me an empty event!");
        }
        int atIndex = command.indexOf(" /at");
        if (atIndex == -1) {
            throw new DukeException("/at not found.");
        }
        int timeIndex = atIndex + " /at ".length();
        return tasks.addTask(new Event(
                command.substring(cutOffIndex, atIndex),
                parseTimeString(command.substring(timeIndex))));
    }

    /**
     * Parses a time string into LocalDate time.
     *
     * @param timeString String describing a date.
     * @return LocalDate representation of time.
     * @throws DukeException if time cannot be parsed.
     */
    private LocalDate parseTimeString(String timeString) throws DukeException {
        try {
            return LocalDate.parse(timeString);
        } catch (DateTimeParseException e) {
            throw new DukeException("Cannot parse time input. Try using YYYY-MM-DD!");
        }
    }

    /**
     * Parses an unknown command.
     *
     * @return Does not return any valid response.
     * @throws DukeException whenever an unknown command is detected.
     */
    private String[] parseUnknownCommand() throws DukeException {
        throw new DukeException("What on earth does that mean? Are you okay?");
    }
}
