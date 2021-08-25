public class AddToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String USAGE_MESSAGE = "To add a new todo, use 'todo <name>'.";

    private final String commandArguments;

    public AddToDoCommand(String commandArguments) {
        this.commandArguments = commandArguments;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException {
        if (commandArguments.isEmpty()) {
            throw new DukeException("Invalid use of the 'todo' command.\n\n" + USAGE_MESSAGE);
        }
        ToDo toDo = new ToDo(commandArguments);
        ui.print(taskManager.addTask(toDo));
        storage.saveTasks(taskManager.toText());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
