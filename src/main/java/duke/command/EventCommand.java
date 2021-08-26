package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.task.Event;

/**
 * Encapsulates a command that handles the addition of event tasks into the task list.
 */
public class EventCommand extends Command {
    private Event eventTask;

    /**
     * Constructs an EventCommand object.
     *
     * @param taskName Description of the event.
     * @param time Time that the event takes place.
     */
    public EventCommand(String taskName, String time) {
        eventTask = new Event(taskName, time);
    }

    /**
     * Adds the event task into the task list and storage file.
     *
     * @param taskList The TaskList object storing all the tasks.
     * @param storage The Storage object which was instantiated with the appropriate storage filepath.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.addTask(eventTask);
        storage.addTasksToFile(taskList);
    }

    /**
     * Returns a boolean specifying whether Duke should terminate.
     *
     * @return false
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
