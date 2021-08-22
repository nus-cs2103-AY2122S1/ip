public class ExitCommand implements Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye! See you next time!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
