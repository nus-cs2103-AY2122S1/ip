public class UnknownCommand extends Command {
    public UnknownCommand(String desc) {
        super(desc);
    }

    @Override
    boolean isExit() {
        return false;
    }

    @Override
    void execute(TaskList tasks, Storage storage) {
        System.out.println("*** Apologies, Master Wayne. But I don't know what that means ***");
    }
}
