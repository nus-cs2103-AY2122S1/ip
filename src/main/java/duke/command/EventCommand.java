package duke.command;

import duke.ToDoList;

/**
 * EventCommand is a Command that encapsulates the attributes and behaviour of adding an Event to the
 * ToDoList.
 *
 * @author leezhixuan
 */
public class EventCommand extends Command {
    private ToDoList tdl;
    private String item;
    private String duration;

    /**
     * Creates an instance of EventCommand.
     *
     * @param tdl Instance of ToDoList in use.
     * @param item Name of Event.
     * @param duration Time between start and end of the Event.
     */
    public EventCommand(ToDoList tdl, String item, String duration) {
        this.tdl = tdl;
        this.item = item;
        this.duration = duration;
    }

    @Override
    public void execute() {
        this.tdl.addEvent(this.item, this.duration);
    }
}
