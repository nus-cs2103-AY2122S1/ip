import java.io.IOException;

public class TodoCommand extends Command {
    public static final String COMMAND = "todo";
    private String desc;

    public TodoCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public void execute(List list, Ui ui, Storage storage) throws IOException {
        if (this.desc.equals("")) {
            throw new IllegalArgumentException();
        }
        Todos newTodo = new Todos(this.desc);
        list.add(newTodo);
        storage.writeToFile(list);
        ui.printAdd(newTodo, list.getList().size());
    }
}
