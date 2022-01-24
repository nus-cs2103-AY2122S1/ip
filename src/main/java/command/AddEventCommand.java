package command;

import duke.Storage;
import duke.Ui;
import task.Event;
import task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents a command to add an event.
 */
public class AddEventCommand extends AddCommand {

    private String desc;
    private LocalDate at;

    /**
     * Constructs an AddEventCommand object.
     *
     * @param desc description of the event.
     * @param at date of the event.
     */
    public AddEventCommand(String desc, String at) {
        this.desc = desc;
        this.at = LocalDate.parse(at);
    }

    /**
     * Executes the AddEventCommand.
     *
     * @param tasks Current TaskList.
     * @param ui Ui object.
     * @param storage Storage object.
     * @return response from Duke.
     */
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
