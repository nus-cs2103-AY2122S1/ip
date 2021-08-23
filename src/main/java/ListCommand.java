public class ListCommand extends Command{

    public ListCommand(String arguments) {
        super("");
    }

    @Override
    public void execute(TaskList tl, Storage s, UI ui) {
        ui.print(tl.printList());
    }

    @Override
    public String startsWith() {
        return "list";
    }

    @Override
    public Command of(String arguments) {
        return new ListCommand(arguments);
    }
}
