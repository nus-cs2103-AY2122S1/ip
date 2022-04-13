package duke.command;

import java.util.Arrays;

import duke.DukeException;
import duke.TaskList;
import duke.tasks.taskType;
import duke.ui.Ui;

/**
 * A EventCommand class encapsulates the instructions for Duke to add a Event Task
 */
public class EventCommand extends Command {
    private String[] fields;

    public EventCommand(String[] fields) {
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
        taskList.addTask(taskType.EVENT, fields);
        return Ui.showAddedTask(taskList) + Ui.showTaskCount(taskList);
    }

    /**
     * returns the type of command
     *
     * @return event
     */
    @Override
    public String getType() {
        return "event";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EventCommand) {
            String[] objArgs = ((EventCommand) obj).fields;
            return Arrays.equals(objArgs, this.fields);
        } else {
            return false;
        }
    }
}

