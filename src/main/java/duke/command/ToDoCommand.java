package duke.command;

import duke.ToDoList;

/**
 * ToDoCommand is a Command that encapsulates the attributes and behaviours of adding a ToDo to ToDoList.
 *
 * @author leezhixuan
 */
public class ToDoCommand extends Command {
    private ToDoList tdl;
    private String item;

    /**
     * Creates an instance of ToDoCommand.
     *
     * @param tdl Instance of ToDoList in use.
     * @param item Name of the Task to be done.
     */
    public ToDoCommand(ToDoList tdl, String item) {
        this.tdl = tdl;
        this.item = item;
    }

    @Override
    public void execute() {
        this.tdl.addToDo(this.item);
    }
}
