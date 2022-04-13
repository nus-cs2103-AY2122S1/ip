package duke.command;

import duke.util.ToDoList;

/**
 * DoneCommand is a Command that encapsulates the attributes and behaviour of marking a Task as completed.
 *
 * @author leezhixuan
 */
public class DoneCommand extends Command {
    private ToDoList tdl;
    private int index;

    /**
     * Creates an instance of DoneCommand.
     *
     * @param tdl Instance of ToDoList in use.
     * @param num Index of the Task on ToDoList to be marked as completed.
     */
    public DoneCommand(ToDoList tdl, int num) {
        this.tdl = tdl;
        this.index = num;
    }

    @Override
    public String execute() {
        try {
            return this.tdl.markAsDone(this.index);
        } catch (StringIndexOutOfBoundsException e) {
            return "And I'm supposed to guess which item you're done with?";
        } catch (IndexOutOfBoundsException e) {
            return "Where's this item? It's not even on the list!";
        }
    }
}
