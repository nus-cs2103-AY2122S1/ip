package duke.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

/**
 * Handles the event command which adds
 * a single event.
 */
public class AddEventCommand extends Command {
    /**
     * Regex pattern for finding event commands
     */
    private static final Pattern PATTERN_EVENT = Pattern.compile("^event (.*) /at (\\d{4}-\\d{2}-\\d{2})$");

    public AddEventCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks) {
        Matcher matcher = PATTERN_EVENT.matcher(input);
        if (!matcher.find()) {
            throw new DukeException("Tell me an event like this: event <task> /at YYYY-MM-DD");
        }
        String description = matcher.group(1);
        String eventDateTime = matcher.group(2);

        Event event = new Event(description, false, parseDate(eventDateTime));

        // Add to store
        tasks.add(event);

        // Inform user
        return Ui.notifyAdd(event, tasks.size());
    }
}
