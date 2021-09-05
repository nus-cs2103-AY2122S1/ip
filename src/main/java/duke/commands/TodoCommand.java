package duke.commands;

import java.util.ArrayList;

import duke.DateTimeHandler;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Todo;
import duke.Ui;

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
    public String execute(TaskList tl, Storage s, Ui ui, DateTimeHandler dth) {
        String args = super.getArguments();
        if (args.length() == 0) {
            return "Please enter the name of the task after todo";
        }
        String description;
        String[] tags = new String[0];
        if (args.contains("/t")) {
            ArrayList<String> parts = Parser.parseCommandArguments(args, "t");
            description = parts.get(0);
            tags = parts.get(1).split(" ");
        } else {
            description = args;
        }
        Todo t = new Todo(description, false, tags);
        tl.addToList(t);
        return ui.formatMessage(tl.taskAddedMessage(t));
    }

    @Override
    public String getCommandPrefix() {
        return "todo";
    }
}
