import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddTodoCommand extends Command {

    // Regex pattern for finding todo commands
    private static final Pattern TODO_PATTERN = Pattern.compile("^todo (.*)$");

    AddTodoCommand(String input) {
        super(input);
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) {
        Matcher matcher = TODO_PATTERN.matcher(input);
        if (!matcher.find()) {
            throw new DukeException("Give me a description of the todo to add it as a task");
        }
        String description = matcher.group(1);

        ToDo todo = new ToDo(description, false);

        // Add to store
        tasks.add(todo);

        // Inform user
        ui.notifyAdd(todo, tasks.size());

        return false;
    }
}
