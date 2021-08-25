public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        String message = "Goodbye, hope to see you again soon!";
        System.out.println(message);
    }
}
