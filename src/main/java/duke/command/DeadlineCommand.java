package duke.command;

import java.util.ArrayList;

import duke.exception.InvalidDateTimeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the deadline command.
 */
public class DeadlineCommand extends Command {

    /** Parsed information about the deadline to be added. */
    private final ArrayList<String> parsedDeadline;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param parsedDeadline
     */
    public DeadlineCommand(ArrayList<String> parsedDeadline) {
        this.parsedDeadline = parsedDeadline;
    }

    /**
     * Executes the command.
     *
     * @param tasks list of tasks.
     * @param ui ui to handle user interaction.
     * @param storage handles reading and writing of data file.
     * @return appropriate message for adding a deadline.
     * @throws InvalidDateTimeException if the format of the date or time entered is incorrect.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateTimeException {
        return tasks.addDeadline(parsedDeadline);
    }
}
