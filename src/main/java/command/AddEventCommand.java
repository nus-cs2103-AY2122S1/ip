package command;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import task.*;
import duke.*;

public class AddEventCommand extends AddCommand {

    private String desc;
    private LocalDate at;

    public AddEventCommand(String desc, String at) {
        this.desc = desc;
        this.at = LocalDate.parse(at);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // tasks
        Event newEvent = new Event(desc, at);
        tasks.add(newEvent);
        // ui
        String response = respond(newEvent, tasks.size());
        String result = ui.showResponse(response);
        // storage
        storage.save(tasks);

        return result;
    }
}
