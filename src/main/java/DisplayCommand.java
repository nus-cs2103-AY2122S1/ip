public class DisplayCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public DisplayCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println("display");
        tasks.display();
    }
}