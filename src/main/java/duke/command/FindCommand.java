package duke.command;

import duke.ToDoList;

public class FindCommand extends Command {
    private ToDoList tdl;
    private String target;

    public FindCommand(ToDoList tdl, String target) {
        this.tdl = tdl;
        this.target = target;
    }

    @Override
    public void execute() {
        this.tdl.find(target);
    }
}
