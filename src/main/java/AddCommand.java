//date should go here. Parser should be the part where i process
//date understanding code. Will fix it once i get the structure up and running

public class AddCommand extends Command{
    private String type;
    private String label;

    public AddCommand(String type, String label) {
        this.type = type;
        this.label = label;
    }

    public void execute(TaskList tasklist, Ui ui, Storage store) {
        if (type.equals("todo")) {
            tasklist.add(new Todo(label));
        } else if (type.equals("deadline")) {
            tasklist.add(new Deadline(label));
        } else {
            tasklist.add(new Event(label));
        }
        ui.notifySuccessfulAdd(tasklist);
        ui.readCommand();
    }
}
