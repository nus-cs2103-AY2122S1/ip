package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.ui.Ui;


/**
 * Class that encapsulates the "Tag" Command.
 *
 * @author Wang Hong Yong
 */
public class TagCommand extends Command {
    private String input;

    /**
     * Constructor for TagCommand.
     *
     * @param tasklist Task Handler that handles the operation.
     * @param input command string to be executed.
     */
    public TagCommand(TaskList tasklist, String input) {
        super(tasklist);
        this.input = input;
    }

    /**
     * Executes the "Tag" Command.
     * @return string that represents details of tagging this task.
     */
    public String execute() {
        if (input.length() == 3 || input.length() == 4) {
            throw new DukeException(Ui.getEmptyDescriptionMsg("done"));
            // change error msg
        }
        try {
            String[] infoArray = input.split("\\s+");
            return taskList.addTag(Integer.parseInt(infoArray[1]), infoArray[2]);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
