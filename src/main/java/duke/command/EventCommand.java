package duke.command;

import java.util.ArrayList;

import duke.exception.InvalidDateTimeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the event command.
 */
public class EventCommand extends Command {

    /** Parsed information about the event to be added. */
    private ArrayList<String> event;

    /**
     * Constructor for EventCommand.
     *
     * @param event
     */
    public EventCommand(ArrayList<String> event) {
        this.event = event;
    }

    /**
     * Executes the command.
     *
     * @param tasks list of tasks.
     * @param ui ui to handle user interaction.
     * @param storage handles reading and writing of data file.
     * @return appropriate message for adding an event.
     * @throws InvalidDateTimeException if the format of date or time entered is incorrect.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateTimeException {
        return tasks.addEvent(event);
    }
}
