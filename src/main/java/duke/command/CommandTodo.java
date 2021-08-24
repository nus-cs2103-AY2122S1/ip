package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandTodo extends Command {
    public static final String KEYWORD = "todo";
    private static final String ARG_FORMAT = "\\w+";
    private ArrayList<String> arguments;


    public CommandTodo(ArrayList<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean isArgumentValid() {
        if (arguments.size() == 1) {
            Pattern pattern = Pattern.compile(ARG_FORMAT);
            Matcher matcher = pattern.matcher(arguments.get(0));
            return matcher.matches();
        } else {
            return false;
        }
    }

    @Override
    public void execute(TaskList tl, Storage st, Ui ui) {
        if (isArgumentValid()) {
            Todo newTodo = new Todo(arguments.get(0), "", "");
            tl.addTask(newTodo);
        } else {
            throw new DukeException("Invalid argument for duke.command: todo");
        }
    }
}
