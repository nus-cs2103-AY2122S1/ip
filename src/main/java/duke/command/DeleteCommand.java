package duke.command;

import duke.ToDoList;
import duke.Ui;

/**
 * DeleteCommand is a Command that encapsulates the attributes and behavior of deleting a Task from the
 * ToDoList.
 *
 * @author leezhixuan
 */
public class DeleteCommand extends Command {
    private ToDoList tdl;
    private Ui ui;
    private int index;

    /**
     * Creates an instance of DeleteCommand.
     *
     * @param tdl Instance of ToDoList in use.
     * @param ui Instance of User Interfaces in use.
     * @param index Index of the Task on the ToDoList that is to be deleted.
     */
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
            ui.printProper("You're trying to delete something non-existent? Damn who is this guy?");
        }
    }
}
