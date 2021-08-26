package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.ui.Ui;
import duke.tasks.taskType;

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
    public void execute(TaskList taskList) {
        taskList.addTask(taskType.EVENT, fields);

        Ui.showAddedTask(taskList);
        Ui.showTaskCount(taskList);
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
}

