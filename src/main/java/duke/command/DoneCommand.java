package duke.command;

import duke.ToDoList;
import duke.Ui;

public class DoneCommand extends Command {
    private ToDoList tdl;
    private Ui ui;
    private int index;

    public DoneCommand(ToDoList tdl, Ui ui, int num) {
        this.tdl = tdl;
        this.ui = ui;
        this.index = num;
    }

    @Override
    public void execute() {
        try {
            this.tdl.markAsDone(this.index);
        } catch (StringIndexOutOfBoundsException e) {
            ui.prettyPrinter("And I'm supposed to guess which item you're done with?");
        } catch (IndexOutOfBoundsException e) {
            ui.prettyPrinter("Where's this item? It's not even on the list!");
        }
    }
}
