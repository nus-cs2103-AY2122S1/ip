package iris.command;

import iris.IrisException;
import iris.TaskList;
import iris.task.TaskPriority;

public class DeadlineCommand extends AddCommand {
    private String name;
    private String by;
    private TaskPriority taskPriority;

    /**
     * Creates a new DeadlineCommand
     *
     * @param name name of the deadline
     * @param by   due date for this deadline e.g. "2021-08-23"
     * @param taskPriority priority level for this deadline
     */
    public DeadlineCommand(String name, String by, TaskPriority taskPriority) {
        this.name = name;
        this.by = by;
        this.taskPriority = taskPriority;
    }

    @Override
    public String run(TaskList tasks) throws IrisException {
        assert tasks != null;
        tasks.addDeadline(this.name, this.by, this.taskPriority);
        return super.run(tasks);
    }
}
