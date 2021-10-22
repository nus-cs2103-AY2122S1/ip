package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.tasks.Deadlines;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Creates and adds a Deadline task.
 */
public class CreateDeadlineCommand extends Command {
    private final String name;
    private final LocalDate date;

    /**
     * @param userInput The raw input string from the user
     */
    public CreateDeadlineCommand(String userInput) {
        assert userInput.split(" /by ").length > 1;
        this.name = userInput.split(" /by ", 2)[0].substring(9);
        try {
            this.date = LocalDate.parse(userInput.split(" /by ", 2)[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("Use the following date format: yyyy-mm-dd");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMsg(tasks.addToList(new Deadlines(this.name, this.date)));
        storage.write(tasks.getSaveData());
    }
}
