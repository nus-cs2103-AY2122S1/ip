package duke.commands;

import duke.DukeException;
import duke.DukeStorage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineCommand extends Command {

    private String description;

    private LocalDateTime byDateTime;

    public DeadlineCommand(String description, LocalDateTime byDateTime) {
        this.description = description;
        this.byDateTime = byDateTime;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage storage) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        Deadline deadline = new Deadline(description, byDateTime.format(formatter));
        taskList.add(deadline);
        ui.addedMessage(taskList, deadline);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
