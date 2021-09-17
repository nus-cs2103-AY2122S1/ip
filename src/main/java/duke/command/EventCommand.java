package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

/**
 * Class to encapsulate a EventCommand
 */
public class EventCommand extends Command {
    public static final String INSTRUCTION_EVENT = "event";
    protected Event event;

    /**
     * Class constructor for EventCommand Class specifying parameter_1 and parameter_2
     */
    public EventCommand(String parameter1, String parameter2) throws DukeException {
        if (parameter1.equals("") || parameter2.equals("")) {
            throw new DukeException("OOPS!!! The description and the date of an event cannot be empty.");
        }
        event = new Event(parameter1, parameter2);
    }

    /**
     * Executes the command
     *
     * @param tasks    the TaskList
     * @param ui       the Ui
     * @param storage  the data source
     *
     * @return         string stating the command result
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(this.event);
        tasks.add(this.event);
        return "Got it. I've added this task:\n" + "  " + this.event.toString() + "\n" + tasks.toString();
    }

    /**
     * Checks if the command is an ExitCommand
     *
     * @return           boolean stating if command is ExitCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the toString of the class
     *
     * @return           toString of the class
     */
    @Override
    public String toString() {
        return "[" + INSTRUCTION_EVENT + "] - " + event.toString();
    }
}
