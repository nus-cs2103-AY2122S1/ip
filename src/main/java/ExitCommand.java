public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("\tBye (*´▽｀)ノシ. Have a nice day!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
