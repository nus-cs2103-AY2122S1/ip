package duke.command;

import duke.task.Task;
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
        Task[] result = this.tdl.find(target);
        Integer number = 1;
        String response = "";
        if (result.length > 0) {
            response += "Here's what I've found: \n";
            for (Task t : result) {
                if (t.isCompleted()) {
                    response += number.toString() + ". " + t.logo() + "[X] " + t.toString() + "\n";
                } else {
                    response += number.toString() + ". " + t.logo() + "[ ] " + t.toString() + "\n";
                }
                number++;
            }
        } else {
            response += "No luck here.. Sorry buddy.";
        }
        return response;
    }
}
