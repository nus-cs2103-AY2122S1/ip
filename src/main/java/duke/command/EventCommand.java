package duke.command;

import duke.task.Event;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class EventCommand extends Command {
    protected Event event;
    public static final String INSTRUCTION_EVENT = "event";

    public EventCommand(String parameter_1, String parameter_2) throws DukeException {
        if (parameter_1.equals("") || parameter_2.equals("")) {
            throw new DukeException("☹ OOPS!!! The description and the date of an event cannot be empty.");
        }
        event = new Event(parameter_1, parameter_2);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(this.event);
        tasks.add(this.event);
        ui.formatPrint("Got it. I've added this task:", "  " + this.event.toString(), tasks.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "[" + INSTRUCTION_EVENT + "] - " + event.toString();
    }
}
