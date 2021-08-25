package duke.command;

import duke.DukeException;
import duke.task.Event;
import duke.TaskList;
import duke.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles the event command which adds
 * a single event.
 */
public class AddEventCommand extends Command {
    /**
     * Regex pattern for finding event commands
     */
    private static final Pattern EVENT_PATTERN = Pattern.compile("^event (.*) /at (\\d{4}-\\d{2}-\\d{2})$");

    public AddEventCommand(String input) {
        super(input);
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) {
        Matcher matcher = EVENT_PATTERN.matcher(input);
        if (!matcher.find()) {
            throw new DukeException("Tell me an event like this: event <task> /at YYYY-MM-DD");
        }
        String description = matcher.group(1);
        String eventDateTime = matcher.group(2);

        Event event = new Event(description, false, parseDate(eventDateTime));

        // Add to store
        tasks.add(event);

        // Inform user
        ui.notifyAdd(event, tasks.size());
        return false;
    }
}
