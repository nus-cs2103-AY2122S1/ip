public class ByeCommand implements ICommand {

    public void execute(TaskManager tm, Ui ui, Storage storage) {
        ui.printByeMessage();
        System.exit(0);
    }

}