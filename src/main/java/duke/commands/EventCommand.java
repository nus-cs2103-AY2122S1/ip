package duke.commands;

import duke.DukeException;
import duke.DukeStorage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventCommand extends Command{

    private String description;

    private LocalDateTime atDateTime;

    public EventCommand(String description, LocalDateTime atDateTime) {
        this.description = description;
        this.atDateTime = atDateTime;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage storage) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        Event event = new Event(description, atDateTime.format(formatter));
        taskList.add(event);
        ui.addedMessage(taskList, event);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
