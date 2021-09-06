package duke.command;

import duke.DukeList;
import duke.task.Event;

public class EventCommand extends DukeCommand {
    private final Event task;

    /**
     * Public constructor to create an EventCommand.
     *
     * @param task Event to be added.
     */
    public EventCommand(Event task) {
        super();
        this.task = task;
    }

    @Override
    public String run(DukeList list) {
        list.addTask(task);
        return stringifyMessage(list.size());
    }

    private String stringifyMessage(int listSize) {
        return "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + listSize + " tasks in the list";
    }
}
