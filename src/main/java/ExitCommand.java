public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String closingMessage =  "Goodbye! Hope to see you again soon!\n";
        ui.printMessage(closingMessage);
    }
}
