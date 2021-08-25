package duke.command;

import duke.ToDoList;

/**
 * ListCommand is a Command that encapsulates the attributes and behaviours of listing Tasks in ToDoList.
 *
 * @author leezhixuan
 */
public class ListCommand extends Command {
    private ToDoList tdl;

    /**
     * Creates an instance of ListCommand.
     *
     * @param tdl Instance of ToDoList in use.
     */
    public ListCommand(ToDoList tdl) {
        this.tdl = tdl;
    }

    @Override
    public void execute() {
        this.tdl.displayList();
    }
}
