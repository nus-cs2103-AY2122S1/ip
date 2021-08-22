public class ExitCommand extends Command{
    public ExitCommand(String desc) {
        super(desc);
    }

    public boolean isExit() {
        return true;
    }

    public void execute(TaskList tasks, Storage storage) {

    }
}
