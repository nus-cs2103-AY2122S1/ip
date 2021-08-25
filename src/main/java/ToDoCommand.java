public class ToDoCommand extends Command {

    private final ToDo toDo;

    public ToDoCommand(ToDo toDo) {
        this.toDo = toDo;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(toDo);
        storage.appendToFile(toDo);
        ui.printWithLines("Got it. I've added this task:\n  " + toDo
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
