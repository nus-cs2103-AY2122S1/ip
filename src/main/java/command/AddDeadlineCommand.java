package command;

import duke.Storage;
import duke.Ui;
import task.Deadline;
import task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents a command to add a deadline.
 */
public class AddDeadlineCommand extends AddCommand {

    private String desc;
    private LocalDate by;

    /**
     * Constructs an AddDeadlineCommand object.
     *
     * @param desc description of the deadline.
     * @param by date of the deadline.
     */
    public AddDeadlineCommand(String desc, String by) {
        this.desc = desc;
        this.by = LocalDate.parse(by);
    }

    /**
     * Executes the AddDeadlineCommand.
     *
     * @param tasks Current TaskList.
     * @param ui Ui object.
     * @param storage Storage object.
     * @return response from Duke.
     */
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
