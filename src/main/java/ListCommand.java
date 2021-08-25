public class ListCommand extends Command {
    private ToDoList tdl;

    public ListCommand(ToDoList tdl) {
        this.tdl = tdl;
    }

    @Override
    public void execute() {
        this.tdl.displayList();
    }
}
