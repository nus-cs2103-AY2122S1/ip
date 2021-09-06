package iris.command;

import iris.IrisException;
import iris.TaskList;

public class ToDoCommand extends AddCommand {
    private String name;

    public ToDoCommand(String name) {
        this.name = name;
    }

    @Override
    public String run(TaskList tasks) throws IrisException {
        assert tasks != null;
        tasks.addTodo(this.name);
        return super.run(tasks);
    }
}
