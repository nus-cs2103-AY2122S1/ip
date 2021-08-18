import java.time.LocalDate;

public class AddCommand extends Command{
    private final String type;
    private final String label;
    private LocalDate date;

    public AddCommand(String type, String label) {
        this.type = type;
        this.label = label;
    }

    public AddCommand(String type, String label, LocalDate date) {
        this.type = type;
        this.label = label;
        this.date = date;
    }

    public void execute(TaskList tasklist, Ui ui, Storage store) {
        if (type.equals("todo")) {
            tasklist.add(new Todo(label));
        } else if (type.equals("deadline")) {
            tasklist.add(new Deadline(label, date));
        } else {
            tasklist.add(new Event(label, date));
        }
        ui.notifySuccessfulAdd(tasklist);
    }
}
