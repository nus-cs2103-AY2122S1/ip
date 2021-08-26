package iris.command;

import iris.TaskList;

public class ToDoCommand extends AddCommand {
    private String name;

    public ToDoCommand(String name) {
        this.name = name;
    }

    @Override
    public void runSilently(TaskList tasks) {
        tasks.addTodo(this.name);
    }
}
