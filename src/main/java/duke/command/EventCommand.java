package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

public class EventCommand extends Command {
    public static final String INSTRUCTION_EVENT = "event";
    protected Event event;

    /**
     * Class constructor for EventCommand Class specifying parameter_1 and parameter_2
     */
    public EventCommand(String parameter1, String parameter2) throws DukeException {
        if (parameter1.equals("") || parameter2.equals("")) {
            throw new DukeException("☹ OOPS!!! The description and the date of an event cannot be empty.");
        }
        event = new Event(parameter1, parameter2);
    }

    /**
     * Execute the command
     *
     * @param tasks    the TaskList
     * @param ui       the Ui
     * @param storage  the data source
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(this.event);
        tasks.add(this.event);
        ui.formatPrint("Got it. I've added this task:", "  " + this.event.toString(), tasks.toString());
    }

    /**
     * Check if the command is an ExitCommand
     *
     * @return           boolean stating if command is ExitCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Return the toString of the class
     *
     * @return           toString of the class
     */
    @Override
    public String toString() {
        return "[" + INSTRUCTION_EVENT + "] - " + event.toString();
    }
}
