public class ListCommand extends Command{
    String command;
    public ListCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.respondToList(tasks.getTasks());
        //System.out.println("hi");;
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
