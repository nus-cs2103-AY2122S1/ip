public class CreateTodoCommand extends Command {
    private final String name;

    CreateTodoCommand(String name) {
        this.name = name.substring(5);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMsg(tasks.addToList(new ToDos(this.name)));
        storage.write(tasks.getSaveData());
    }
}
