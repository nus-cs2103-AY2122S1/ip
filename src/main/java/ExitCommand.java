public class ExitCommand extends Command {

    private final boolean EXIT = true;

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public boolean isExit() {
        return EXIT;
    }
}
