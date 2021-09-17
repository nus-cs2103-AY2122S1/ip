package duke.commands;

import duke.exceptions.DuplicateTaskException;
import duke.tasks.Event;
import duke.utils.TaskList;

/**
 * Encapsulates a command to add an event task. Executing it will
 * return the specified event instance.
 */
public class AddEvent extends Command {
    private String taskName;
    private String timePeriod;

    /**
     * Creates a command to create an event task with the specified
     * name and time.
     *
     * @param taskName Name of the event task.
     * @param timePeriod Time of the event with the format YYYY-MM-DD.
     */
    public AddEvent(String taskName, String timePeriod) {
        this.taskName = taskName;
        this.timePeriod = timePeriod;
    }

    @Override
    public TaskList execute(TaskList taskList) throws DuplicateTaskException {
        Event event = new Event(this.taskName, this.timePeriod);
        taskList.add(event);
        return taskList;
    }
}
