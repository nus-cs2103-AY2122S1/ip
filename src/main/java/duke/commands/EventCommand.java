package duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeStorage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Event;

/**
 * Class that contains the event command
 *
 */
public class EventCommand extends Command {

    /** The description of the event command */
    private String description;

    /** The date and time for the event command */
    private LocalDateTime atDateTime;

    /**
     * Constructor for the event command class
     *
     * @param description The description of the event command
     * @param atDateTime The date and time for the event command
     */
    public EventCommand(String description, LocalDateTime atDateTime) {
        this.description = description;
        this.atDateTime = atDateTime;
    }

    /**
     * Executes the event command
     *
     * @param taskList The list of tasks that is associated with the instance of Duke
     * @param ui The UI that is associated with the instance of Duke
     * @param storage The storage that is associated with the instance of Duke
     * @return Duke's String response
     */
    @Override
    public String execute(TaskList taskList, Ui ui, DukeStorage storage) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        Event event = new Event(description, atDateTime.format(formatter));
        taskList.add(event);
        return ui.addedMessage(taskList, event);
    }
}
