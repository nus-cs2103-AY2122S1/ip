package duke.command;

import duke.exception.DukeArgumentException;
import duke.exception.DukeCommandException;
import duke.task.TaskList;

public class CommandListTag extends DukeCommand {
    private int taskId;

    public CommandListTag(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String execute(TaskList tl) {
        return tl.getTaskTags(taskId);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public static DukeCommand parseCommand(String[] userArgs) throws DukeCommandException, DukeArgumentException {
        assert userArgs != null;
        assert userArgs.length != 0;
        assert userArgs[0].equals("listTag");

        if (userArgs.length < 2) {
            throw new DukeCommandException("addTag");
        }

        assert userArgs.length == 2;

        int taskId;
        try {
            taskId = Integer.parseInt(userArgs[1]);
        } catch (NumberFormatException nfe) {
            throw new DukeArgumentException("Incorrect argument for command listTag, must be an integer");
        }

        return new CommandListTag(taskId);
    }
}
