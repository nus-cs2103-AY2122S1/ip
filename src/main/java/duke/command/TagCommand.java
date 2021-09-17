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
    public static final String COMMAND_WORD = "TAG";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + " : adds a tag to the selected task";
    public static final String COMMAND_FORMAT = "Format: tag <task number> <description> "
            + "(e.g tag 2 priority)\n";
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
     *
     * @return string that represents details of tagging this task.
     * @throws DukeException if task faces an error during execution.
     */
    public String execute() throws DukeException {
        int minCommandLength = 7;
        if (input.length() < minCommandLength) {
            throw new DukeException(Ui.getTagFormatMsg());
        }
        String[] infoArray = input.split("\\s+");
        return taskList.addTag(infoArray);
    }
}
