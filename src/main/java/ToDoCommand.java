public class ToDoCommand extends Command {
    
    public String arguments;

    public ToDoCommand(String arguments) {
        super("todo");
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException(String.format("The description of a %s cannot be left empty. "
                    + "Please try again.", command));
        }
        Todo newTask = new Todo(arguments);
        tasks.add(newTask);
        ui.println("Got it. I've added this task:");
        ui.println("  " + newTask);
        ui.println("Now you have " + tasks.size() +
                (tasks.size() == 1 ? " task" : " tasks")
                + " in your list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
