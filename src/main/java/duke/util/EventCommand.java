package duke.util;

import duke.exception.DukeException;
import duke.exception.NoDescriptionAndTimeException;

import duke.task.Event;
import duke.task.TaskList;

import java.time.LocalDateTime;

/**
 * Encapsulates the event command class.
 */
public class EventCommand implements Command{

    private String[] userInput;

    /**
     * Constructor for EventCommand.
     *
     * @param userInput Array of user input for event command.
     */
    public EventCommand(String[] userInput) {
        this.userInput = userInput;
    }

    /**
     * Returns string response when user enters an event command.
     *
     * @param tasks List of tasks.
     * @param ui Ui that prints message to users.
     * @param storage Storage to save and load data.
     * @return String representation of duke's response for event command.
     * @throws DukeException If no description or time entered for event command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (userInput.length == 1) {
            throw new NoDescriptionAndTimeException("event");
        }

        String[] eventDetail = Parser.parseDescriptionAndTime(userInput, "/at", "event");
        assert eventDetail.length == 2 : "eventDetail should have length of 2";

        LocalDateTime localDateTime = Parser.parseDateTime(eventDetail[1].trim());
        String description = eventDetail[0].trim();
        Event event = new Event(description, localDateTime);

        return tasks.addTaskToList(event);
    }
}
