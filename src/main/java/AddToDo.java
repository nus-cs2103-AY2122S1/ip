public class AddToDo extends Command {
    ToDos todoTask;

    public AddToDo(String description) {
        this.todoTask = new ToDos(description);
    }
    @Override
    void execute(ToDo taskList, Ui ui, Storage storage) throws KermitException {
        taskList.add(todoTask);
        ui.showAddTaskMessage(todoTask, taskList);
        storage.save(taskList);
    }

    @Override
    boolean isExit() {
        return false;
    }
}