package iris.command;

import iris.IrisException;
import iris.TaskList;
import iris.task.TaskPriority;

public class EventCommand extends AddCommand {
    private String name;
    private String at;
    private TaskPriority taskPriority;

    /**
     * Creates a new EventCommand
     *
     * @param name name of the event
     * @param at   date for this event e.g. "2021-08-23"
     * @param taskPriority priority level for this event
     */
    public EventCommand(String name, String at, TaskPriority taskPriority) {
        this.name = name;
        this.at = at;
        this.taskPriority = taskPriority;
    }

    @Override
    public String run(TaskList tasks) throws IrisException {
        assert tasks != null;
        tasks.addEvent(this.name, this.at, this.taskPriority);
        return super.run(tasks);
    }
}
