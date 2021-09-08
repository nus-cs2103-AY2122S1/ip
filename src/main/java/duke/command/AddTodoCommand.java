package duke.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.ToDo;

/**
 * Handles the todo command which
 * adds a single todo.
 */
public class AddTodoCommand extends Command {
    /**
     * Regex pattern for finding todo commands
     */
    private static final Pattern PATTERN_TODO = Pattern.compile("^todo (.*)$");

    public AddTodoCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks) {
        Matcher matcher = PATTERN_TODO.matcher(input);
        if (!matcher.find()) {
            throw new DukeException("Give me a description of the todo to add it as a task");
        }
        String description = matcher.group(1);

        ToDo todo = new ToDo(description, false);

        // Add to store
        tasks.add(todo);

        // Inform user
        return Ui.notifyAdd(todo, tasks.size());
    }
}
