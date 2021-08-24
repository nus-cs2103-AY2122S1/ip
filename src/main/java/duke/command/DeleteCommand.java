public class DeleteCommand extends Command {

    public String arguments;

    public DeleteCommand(String arguments) {
        super("delete");
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("No index was keyed in. Please try again.");
        }
        int index = Integer.parseInt(arguments);
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("The index you entered is invalid. Please try again.");
        }
            Task taskDeleted = tasks.remove(index - 1);
            ui.println("Noted! I've removed this task:");
            ui.println("  " + taskDeleted);
            ui.println("Now you have " + tasks.size() +
                    (tasks.size() == 1 ? " task" : " tasks")
                    + " in your list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
