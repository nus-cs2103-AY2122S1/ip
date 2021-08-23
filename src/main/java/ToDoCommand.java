/**
 * Contains the executables when the user uses the 'Todo' command.
 */
public class ToDoCommand extends Command {

    private String task;

    ToDoCommand(String task) {
        this.task = task;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        if (task.length() == 0) {
            ui.showError("OOPS!!! The description of a todo cannot be empty.\n");
            return;
        }
        Todo taskToDo = new Todo(task);
        taskList.add(taskToDo);
        storage.save(taskList);
        ui.addMessage();
        ui.showTask(taskToDo);
        ui.showListLength(taskList);

    }
}
