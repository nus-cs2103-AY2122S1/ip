package duke.commands;

import duke.exceptions.DuplicateTaskException;
import duke.tasks.Event;
import duke.utils.TaskList;

public class AddEvent extends Command {
    private String taskName;
    private String timePeriod;

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
