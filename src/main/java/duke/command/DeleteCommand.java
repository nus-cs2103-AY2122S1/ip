package duke.command;

import duke.util.ToDoList;

/**
 * DeleteCommand is a Command that encapsulates the attributes and behavior of deleting a Task from the
 * ToDoList.
 *
 * @author leezhixuan
 */
public class DeleteCommand extends Command {
    private ToDoList tdl;
    private int index;

    /**
     * Creates an instance of DeleteCommand.
     *
     * @param tdl Instance of ToDoList in use.
     * @param index Index of the Task on the ToDoList that is to be deleted.
     */
    public DeleteCommand(ToDoList tdl, int index) {
        this.tdl = tdl;
        this.index = index;
    }

    @Override
    public String execute() {
        try {
            return this.tdl.delete(index);
        } catch (IndexOutOfBoundsException e) {
            return "You're trying to delete something non-existent? Damn who is this guy?";
        }
    }
}
