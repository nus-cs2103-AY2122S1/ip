package duke.command;

import duke.*;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The CommandTodo class handles the command "to do" that adds a new To Do task
 * to the task list.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class CommandTodo extends Command {
    public static final String KEYWORD = "todo";
    private static final String ARG_FORMAT = "\\w+";
    private ArrayList<String> arguments;


    public CommandTodo(ArrayList<String> arguments) {
        this.arguments = arguments;
    }

    /**
     * This method checks if the description specified in the arguments
     * is in a valid format.
     *
     * @return A boolean indicating if the arguments are in a valid format.
     */
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
