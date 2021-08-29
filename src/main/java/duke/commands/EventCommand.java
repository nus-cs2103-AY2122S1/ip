package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.tasks.Task;
import duke.Ui;
import duke.Storage;
import duke.tasks.Event;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventCommand extends Command {

    private  static final Pattern EVENT_FORMAT = Pattern.compile("(?<description>\\S+) /at (?<at>.*)");

    private final String description, at;

    public EventCommand(String args) throws DukeException {
        Matcher matcher = EVENT_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new DukeException("Events must be in the format 'event <description> /at <dd MMM yyyy HH:mm>");
        }
        description = matcher.group("description").trim();
        at = matcher.group("at").trim();
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task event = new Event(description, at);
        taskList.addTask(event);
        storage.write(taskList);
        ui.showTaskAdded(event, taskList);
    }

}
