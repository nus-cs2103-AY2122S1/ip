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
     */
    public String execute() {
        if (input.length() == 5) {
            throw new DukeException(Ui.getEmptyDescriptionMsg("event"));
        }
        String[] infoArray = input.substring(6).split("/at ", 2);
        Event e = new Event(infoArray[0], infoArray[1]);
        return taskList.addTask(e);
    }
}
