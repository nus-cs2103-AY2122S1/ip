package duke.commands;

import duke.DateTimeHandler;
import duke.Storage;
import duke.TaskList;
import duke.Todo;
import duke.UI;

/**
 * Encapsulates the todo command, used to create a todo task
 */
public class TodoCommand extends Command {

    public TodoCommand(String arguments) {
        super(arguments);
    }

    @Override
    public Command of(String arguments) {
        return new TodoCommand(arguments);
    }

    @Override
    public String execute(TaskList tl, Storage s, UI ui, DateTimeHandler dth) {
        String args = super.getArguments();
        if (args.length() == 0) {
            return "Please enter the name of the task after todo";
        }
        Todo t = new Todo(args, false);
        tl.addToList(t);
        return ui.formatMessage(tl.taskAddedMessage(t));
    }

    @Override
    public String startsWith() {
        return "todo";
    }
}
