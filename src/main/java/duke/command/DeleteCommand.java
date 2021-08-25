package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles the delete command which
 * deletes a single task.
 */
public class DeleteCommand extends Command {
    // Regex pattern for finding delete commands
    private static final Pattern PATTERN_DELETE = Pattern.compile("^delete (\\d*)$");

    public DeleteCommand(String input) {
        super(input);
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) {


        // Check if user is attempting to mark a task as done.
        Matcher matcher =  PATTERN_DELETE.matcher(input);
        if (!matcher.find()) {
            throw new DukeException("Delete a task like this: delete <task number>");
        }

        String taskPositionString = matcher.group(1);
        int taskPosition = Integer.parseInt(taskPositionString);

        // Remove the task from the store.
        Task task = tasks.delete(taskPosition - 1);

        ui.notifyDelete(task, tasks.size());

        return false;
    }
}
