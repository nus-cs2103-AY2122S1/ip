package duke.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Event;
import duke.tasks.Task;

/**
 * The EventCommand handles when an event command is entered.
 */
public class EventCommand extends Command {

    private static final Pattern EVENT_FORMAT = Pattern.compile("(?<description>.*?) /at (?<at>.*)");

    private final String description;
    private final String at;

    /**
     * Constructs an EventCommand object with given arguments.
     * @param args Arguments entered for the event.
     * @throws DukeException Invalid arguments entered.
     */
    public EventCommand(String args) throws DukeException {
        Matcher matcher = EVENT_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new DukeException("Events must be in the format 'event <description> /at <dd MMM yyyy HH:mm>");
        }
        description = matcher.group("description").trim();
        at = matcher.group("at").trim();
    }

    /**
     * Executes the addition of an Event being added to the TaskList.
     *
     * @param taskList The current TaskList being used.
     * @param ui The current Ui being used.
     * @param storage The current Storage being used.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task event = new Event(description, at);
        taskList.addTask(event);
        storage.write(taskList);
        return ui.showTaskAdded(event, taskList);
    }

}
