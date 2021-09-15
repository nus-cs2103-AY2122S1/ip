package duke.command;

import java.util.Arrays;

import duke.DukeException;
import duke.TaskList;
import duke.tasks.taskType;
import duke.ui.Ui;

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
    public String execute(TaskList taskList) {
        taskList.addTask(taskType.DEADLINE, fields);
        String output = Ui.showAddedTask(taskList) + Ui.showTaskCount(taskList);
        return output;
    }

    @Override
    public String getType() {
        return "deadline";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeadlineCommand) {
            String[] objArgs = ((DeadlineCommand) obj).fields;
            return Arrays.equals(objArgs, this.fields);
        } else {
            return false;
        }
    }
}
