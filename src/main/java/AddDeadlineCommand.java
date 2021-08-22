import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class AddDeadlineCommand extends AddCommand {

    private String desc;
    private LocalDate by;

    public AddDeadlineCommand(String desc, String by) {
        this.desc = desc;
        this.by = LocalDate.parse(by);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // tasks
        Deadline newDeadline = new Deadline(desc, by);
        tasks.add(newDeadline);
        // ui
        String response = respond(newDeadline, tasks.size());
        ui.showResponse(response);
        // storage
        storage.save(tasks);
    }
}
