package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Event;
import duke.ui.Ui;

/**
 * Class that encapsulates the "Event" Command.
 *
 * @author Wang Hong Yong
 */
public class EventCommand extends Command {
    private String input;

    /**
     * Constructor for EventCommand.
     *
     * @param tasklist Task Handler that handles the operation.
     * @param input command string to be executed.
     */
    public EventCommand(TaskList tasklist, String input) {
        super(tasklist);
        this.input = input;
    }

    /**
     * Executes the "Event" Command.
     * @return string that represents details of adding this Event task.
     */
    @Override
    public String execute() throws DukeException {
        int minCommandLength = 5;
        if (input.length() == minCommandLength) {
            throw new DukeException(Ui.getEmptyDescriptionMsg("event"));
        }
        try {
            String[] infoArray = input.substring(minCommandLength + 1).split("/at ", 2);
            Event e = new Event(infoArray[0], infoArray[1], new String[0]);
            return taskList.addTask(e);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
