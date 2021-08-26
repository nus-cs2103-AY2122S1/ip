public class PrintCommand extends Command {
    
    private boolean isExit;
    
    public PrintCommand() {
        this.isExit = false;
    }
    
    @Override
    public boolean isExit() {
        return isExit;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.printList(list);
    }
}
