package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles the done command which
 * marks a single task as done.
 */
public class MarkAsDoneCommand extends Command {

    // Regex pattern for finding done commands
    private static final Pattern PATTERN_DONE = Pattern.compile("^done (\\d*)$");

    public MarkAsDoneCommand(String input) {
        super(input);
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) {
        // Check if user is attempting to mark a task as done.
        Matcher matcher =  PATTERN_DONE.matcher(input);
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
