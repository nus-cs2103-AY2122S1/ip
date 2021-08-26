package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.ui.Ui;
import duke.tasks.taskType;

/**
 * A DeadlineCommand class encapsulates the instructions for Duke to add a Deadline Task
 */
public class DeadlineCommand extends Command {
    private String[] fields;

    public DeadlineCommand(String[] fields) {
        this.fields = fields;
    }

    /**
     * executes the command on the specified tasklist
     *
     * @param taskList tasklist to be operated on
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.addTask(taskType.DEADLINE, fields);
        Ui.showAddedTask(taskList);
        Ui.showTaskCount(taskList);
    }

    @Override
    public String getType() {
        return "deadline";
    }

}
