public class ToDoCommand extends Command {
    private ToDoList tdl;
    private String item;

    public ToDoCommand(ToDoList tdl, String item) {
        this.tdl = tdl;
        this.item = item;
    }

    @Override
    public void execute() {
        this.tdl.addToDo(this.item);
    }
}
