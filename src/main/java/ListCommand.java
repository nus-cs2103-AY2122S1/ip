public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.displayText(taskList.printList());
    }

}
