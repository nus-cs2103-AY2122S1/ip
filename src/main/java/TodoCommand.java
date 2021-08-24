import java.io.IOException;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private String userCommand;

    public static final String MESSAGE_USAGE = COMMAND_WORD + " <description> - add a todo item\n" +
            "   Example: " + COMMAND_WORD + " read book";

    public TodoCommand(String userCommand) {
        super();
        this.userCommand = userCommand;

    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (userCommand.strip().length() < 5) {
                throw new IllegalArgumentException("Please add a description for your todo!");
            }

            Todo newTodo = new Todo(userCommand.substring(5));
            tasks.addTask(newTodo);
            storage.save(tasks.getItems());

            ui.printTaskAdded(newTodo, tasks.getSize());
        } catch (IOException | IllegalArgumentException e) {
            ui.printError(e.getMessage());
        }

    }
}
