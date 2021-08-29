package duke.commands;

import duke.*;
import duke.tasks.Deadline;
import duke.tasks.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineCommand extends Command {

    private static final Pattern EVENT_FORMAT = Pattern.compile("(?<description>\\S+) /by (?<by>.*)");

    private final String description, by;

    public DeadlineCommand(String args) throws DukeException {
        Matcher matcher = EVENT_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new DukeException("Events must be in the format 'deadline <description> /by <dd MMM yyyy>");
        }
        description = matcher.group("description").trim();
        by = matcher.group("by").trim();
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task deadline = new Deadline(description, by);
        taskList.addTask(deadline);
        storage.write(taskList);
        ui.showTaskAdded(deadline, taskList);
    }

}
