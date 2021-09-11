package tokio.commands;

import tokio.storage.Storage;
import tokio.tasks.TaskList;
import tokio.tasks.Todos;
import tokio.ui.Ui;

import java.io.IOException;

public class AddTodoCommand extends Command {
    protected String description;

    public AddTodoCommand(String description) {
        this.description = description.trim();
    }
    
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Todos addTodo = new Todos(description);
        tasks.addTask(addTodo);
        storage.writeTask(addTodo);
        return ui.printAddCommand(addTodo, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
