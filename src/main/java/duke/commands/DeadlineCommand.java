package duke.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Deadline;
import duke.tasks.Task;

/**
 * The DeadlineCommand handles when a deadline command is entered.
 */
public class DeadlineCommand extends Command {

    private static final Pattern EVENT_FORMAT = Pattern.compile("(?<description>\\S+) /by (?<by>.*)");

    private final String description;
    private final String by;

    /**
     * Constructs a DeadlineCommand object.
     *
     * @param args Arguments entered for the deadline.
     * @throws DukeException Invalid arguments entered.
     */
    public DeadlineCommand(String args) throws DukeException {
        Matcher matcher = EVENT_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new DukeException("Deadlines must be in the format 'deadline <description> /by <dd MMM yyyy>");
        }
        description = matcher.group("description").trim();
        by = matcher.group("by").trim();
    }

    /**
     * Executes the addition of a Deadline being added to the TaskList.
     *
     * @param taskList The current TaskList being used.
     * @param ui The current Ui being used.
     * @param storage The current Storage being used.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task deadline = new Deadline(description, by);
        taskList.addTask(deadline);
        storage.write(taskList);
        return ui.showTaskAdded(deadline, taskList);
    }

}
