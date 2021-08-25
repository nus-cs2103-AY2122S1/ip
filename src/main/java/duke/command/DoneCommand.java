package duke.command;

import duke.ToDoList;
import duke.Ui;

/**
 * DoneCommand is a Command that encapsulates the attributes and behaviour of marking a Task as completed.
 *
 * @author leezhixuan
 */
public class DoneCommand extends Command {
    private ToDoList tdl;
    private Ui ui;
    private int index;

    /**
     * Creates an instance of DoneCommand.
     *
     * @param tdl Instance of ToDoList in use.
     * @param ui Instance of User Interface in use.
     * @param num Index of the Task on ToDoList to be marked as completed.
     */
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
            ui.printProper("And I'm supposed to guess which item you're done with?");
        } catch (IndexOutOfBoundsException e) {
            ui.printProper("Where's this item? It's not even on the list!");
        }
    }
}
