import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkAsDoneCommand extends Command {

    // Regex pattern for finding done commands
    private static final Pattern DONE_PATTERN = Pattern.compile("^done (\\d*)$");

    MarkAsDoneCommand(String input) {
        super(input);
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) {
        // Check if user is attempting to mark a task as done.
        Matcher matcher =  DONE_PATTERN.matcher(input);
        if (!matcher.find()) {
            throw new DukeException("Mark a task as done like this: done <task number>");
        }

        String taskPositionString = matcher.group(1);
        int taskPosition = Integer.parseInt(taskPositionString);

        Task task = tasks.markAsDone(taskPosition - 1);

        ui.notifyMarkDone(task, taskPosition - 1);
        return false;
    }
}
