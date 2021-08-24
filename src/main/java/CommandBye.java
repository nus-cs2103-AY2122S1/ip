public class CommandBye extends Command {

    CommandBye(String[] inputTokens) {
        super(inputTokens);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        storage.deposit(tasks);
        ui.sayGoodbye();
    }

    @Override
    public boolean shouldContinueProgram() {
        return false;
    }
}
