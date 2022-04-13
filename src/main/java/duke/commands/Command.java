package duke.commands;

import java.time.format.DateTimeFormatter;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract Command class to categorise user commands.
 */
public abstract class Command {
    /**
     * Formatter for parsing String into Date.
     */
    public static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

    /** Error message to output when invalid date format is input. */
    static final String MESSAGE_INVALID_DATE = "Please add a valid date of format yyyy/MM/dd HHmm (24-hour format)!";

    /** Error message to output when invalid number is input. */
    static final String MESSAGE_INVALID_NUM = "Please enter a numeric character!";

    /** Error message when multiple date is input. */
    static final String MESSAGE_MULTIPLE_DATE = "Please only add ONE date!";

    /** Error message if there is missing description or date for deadline or event tasks. */
    static final String MESSAGE_MISSING_DESC_DATE = "Please add a description and/or date for your task!";

    /** Error message if there is missing description for the task. */
    static final String MESSAGE_MISSING_DESC = "Please add a description for your task!";

    protected String userCommand;

    public Command() {}

    public Command(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Returns true if the command is an exit command, return false otherwise.
     *
     * @return True if the command is an exit command, return false otherwise.
     */
    public abstract boolean isExit();

    /**
     * Executes the respective command given and displays the result or error message(s).
     *
     * @param tasks TaskList that stores the list of tasks.
     * @param ui Ui instance that prints various messages.
     * @param storage Storage instance that reads and writes the task list.
     * @return Message to show whether successful execution of the command or error.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
