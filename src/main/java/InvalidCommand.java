public class InvalidCommand implements Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Sorry, I don't understand what you are saying!";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
