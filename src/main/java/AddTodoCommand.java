import java.io.IOException;

public class AddTodoCommand extends AddTaskCommand {

    AddTodoCommand(String desc, boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        Todo newTodo = new Todo(this.desc, this.isDone);
        taskList.addTask(newTodo);
        ui.printAddTask(newTodo);
        try {
            storage.writeTasksToFile(taskList, storage.getTaskFile());
        } catch (IOException e) {
            ui.printFileWriteFail(storage.getTaskFile());
        }
    }
}
