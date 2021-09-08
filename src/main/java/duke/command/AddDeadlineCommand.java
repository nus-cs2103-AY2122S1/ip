package duke.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

/**
 * Handles the deadline command which adds
 * a single deadline.
 */
public class AddDeadlineCommand extends Command {

    /**
     * Regex pattern for finding deadline commands
     */
    private static final Pattern PATTERN_DEADLINE = Pattern.compile("^deadline (.*) /by (\\d{4}-\\d{2}-\\d{2})$");

    public AddDeadlineCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks) {
        Matcher matcher = PATTERN_DEADLINE.matcher(input);
        if (!matcher.find()) {
            throw new DukeException("Give me a deadline like this: deadline <task> /by YYYY-MM-DD");
        }
        String description = matcher.group(1);
        String endDateTime = matcher.group(2);

        Deadline deadline = new Deadline(description, false, parseDate(endDateTime));

        // Add to store
        tasks.add(deadline);

        // Inform user
        return Ui.notifyAdd(deadline, tasks.size());
    }
}
