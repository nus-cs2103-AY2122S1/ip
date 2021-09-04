package duke.command;


import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Deadline;
import duke.ui.Ui;

/**
 * Class that encapsulates the "Deadline" Command.
 *
 * @author Wang Hong Yong
 */
public class DeadlineCommand extends Command {
    private String input;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param tasklist Task Handler that handles the operation.
     * @param input command string to be executed.
     */
    public DeadlineCommand(TaskList tasklist, String input) {
        super(tasklist);
        this.input = input;
    }

    /**
     * Executes the "Deadline" Command.
     * @return string that represents details of adding this deadline task.
     */
    public String execute() {
        if (input.length() == 8) {
            throw new DukeException(Ui.getEmptyDescriptionMsg("deadline"));
        }
        try {
            String[] infoArray = input.substring(9).split("/by ", 2);
            Deadline d = new Deadline(infoArray[0], infoArray[1]);
            return super.taskList.addTask(d);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }

    }

}
