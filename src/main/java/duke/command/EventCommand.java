package duke.command;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;
/**
 * Contains the executables for the 'event' command.
 *
 * @author Benjamin Lui
 */
public class EventCommand extends Command {

    private String task;
    private final String errorMessage = "OOPS!!! The description of an event cannot be empty.\n";
    // Add 3 to index to avoid "at "
    private final int indexToAvoidBy = 3;

    public EventCommand(String task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (task.length() == 0) {
            return ui.showError(errorMessage);
        }
        char[] data = task.toCharArray();
        String eventTask = "";
        String by = "";
        int index = 0;
        while (index < data.length) {
            if (data[index] == '/') {
                break;
            } else {
                eventTask += data[index];
            }
            index++;
        }
        // Add 3 to index to avoid "at "
        index += indexToAvoidBy;
        while (index < data.length) {
            by += data[index];
            index++;
        }
        if (by.length() == 0) {
            return ui.showError("Time cannot be empty :(");
        }
        Event event = new Event(eventTask, by);
        if (taskList.contains(event)) {
            return ui.showError("To do event, " + event.toString() + " already exists!");
        }
        taskList.add(event);
        storage.save(taskList);
        return ui.addMessage() + ui.showTask(event) + ui.showListLength(taskList);
    }
}
