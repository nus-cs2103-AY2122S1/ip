package duke.command;

import duke.ToDoList;

public class EventCommand extends Command {
    private ToDoList tdl;
    private String item;
    private String duration;

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
