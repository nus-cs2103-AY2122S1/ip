package iris.command;

import iris.IrisException;
import iris.TaskList;
import iris.task.TaskPriority;

public class ToDoCommand extends AddCommand {
    private String name;
    private TaskPriority taskPriority;

    /**
     * Creates a new TodoCommand
     * @param name name of this todo
     * @param taskPriority priority level of this todo
     */
    public ToDoCommand(String name, TaskPriority taskPriority) {
        this.name = name;
        this.taskPriority = taskPriority;
    }

    @Override
    public String run(TaskList tasks) throws IrisException {
        assert tasks != null;
        tasks.addTodo(this.name, this.taskPriority);
        return super.run(tasks);
    }
}
