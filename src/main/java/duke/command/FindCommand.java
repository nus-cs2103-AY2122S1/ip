package duke.command;

import duke.util.ToDoList;

/**
 * FindCommand is a Command that encapsulates the attributes and behavior of looking for a Task
 * in the ToDoList
 */
public class FindCommand extends Command {
    private ToDoList tdl;
    private String target;

    /**
     * Creates an instance of the FindCommand.
     *
     * @param tdl Instance of ToDoList in use.
     * @param target String that the user is looking for in the name of Tasks
     */
    public FindCommand(ToDoList tdl, String target) {
        this.tdl = tdl;
        this.target = target;
    }

    @Override
    public String execute() {
        return this.tdl.find(target);
    }
}
