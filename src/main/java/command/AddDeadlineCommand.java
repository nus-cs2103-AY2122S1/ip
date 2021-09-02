package command;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import task.*;
import duke.*;

public class AddDeadlineCommand extends AddCommand {

    private String desc;
    private LocalDate by;

    public AddDeadlineCommand(String desc, String by) {
        this.desc = desc;
        this.by = LocalDate.parse(by);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // tasks
        Deadline newDeadline = new Deadline(desc, by);
        tasks.add(newDeadline);
        // ui
        String response = respond(newDeadline, tasks.size());
        String result = ui.showResponse(response);
        // storage
        storage.save(tasks);

        return result;
    }
}
