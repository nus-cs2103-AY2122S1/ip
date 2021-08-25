package duke.command;

import duke.ToDoList;
import duke.Ui;

public class DeleteCommand extends Command {
    private ToDoList tdl;
    private Ui ui;
    private int index;

    public DeleteCommand(ToDoList tdl, Ui ui, int index) {
        this.tdl = tdl;
        this.ui = ui;
        this.index = index;
    }

    @Override
    public void execute() {
        try {
            this.tdl.delete(index);
        } catch (IndexOutOfBoundsException e) {
            ui.prettyPrinter("You're trying to delete something non-existent? Damn who is this guy?");
        }
    }
}
