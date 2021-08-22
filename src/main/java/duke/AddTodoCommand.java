package duke;

import java.io.IOException;

public class AddTodoCommand extends Command {
    private final Todo todo;

    public AddTodoCommand(Todo todo) {
        this.todo = todo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.todo);
        try {
            storage.updateFile(tasks.getTasks());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.showAddedMessage(this.todo, tasks);
    }
}
