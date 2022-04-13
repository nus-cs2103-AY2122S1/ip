package duke.command;

import java.time.format.DateTimeParseException;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.exception.DukeException;


public class AddDeadlineCommand extends AddTaskCommand {
    private String description;

    public AddDeadlineCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[] descriptionDatePair = description.split("/by", 2);
            return tasks.recordDeadline(descriptionDatePair[0].trim(), descriptionDatePair[1].trim());
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException("deadline should be in format:\ndeadline [DESCRIPTION] /by DD/MM/YYYY HHMM");
        }
    }
}
