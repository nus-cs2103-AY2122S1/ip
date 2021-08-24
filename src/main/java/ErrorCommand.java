public class ErrorCommand extends Command {
    @Override
    public void execute(Tasklist tasks, Storage storage, Ui ui) {
        System.out.println("Command error occurred, no matches.");
    }
}
