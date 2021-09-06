package duke.command;

import java.time.format.DateTimeParseException;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.exception.DukeException;

public class AddEventCommand extends AddCommand {
    private String description;

    public AddEventCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[] descriptionDatePair = description.split("/at", 2);
            return tasks.recordEvent(descriptionDatePair[0].trim(), descriptionDatePair[1].trim());
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException("event should be in format: [DESCRIPTION] /at [DATE]!\n"
                     + "Only accepted [DATE] format is: date/month/year HHMM (24h time)");
        }
    }
}
