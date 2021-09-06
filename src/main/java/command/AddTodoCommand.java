package command;
import duke.Storage;
import duke.Ui;
import task.TaskList;
import task.Todo;

public class AddTodoCommand extends AddCommand {

    private String desc;

    public AddTodoCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // tasks
        Todo newTodo = new Todo(desc);
        tasks.add(newTodo);
        // ui
        String response = respond(newTodo, tasks.size());
        String result = ui.showResponse(response);
        // storage
        storage.save(tasks);

        return result;
    }
}
