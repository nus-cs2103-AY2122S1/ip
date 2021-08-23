package command;
import task.*;
import duke.*;

public class AddTodoCommand extends AddCommand {

    private String desc;

    public AddTodoCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // tasks
        Todo newTodo = new Todo(desc);
        tasks.add(newTodo);
        // ui
        String response = respond(newTodo, tasks.size());
        ui.showResponse(response);
        // storage
        storage.save(tasks);
    }
}
