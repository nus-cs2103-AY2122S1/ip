package duke.command;

import duke.exception.DukeArgumentException;
import duke.exception.DukeCommandException;
import duke.task.TaskList;

public class CommandDeleteTag extends DukeCommand {
    private int taskId;
    private String tag;

    public CommandDeleteTag(int taskId, String tag) {
        this.taskId = taskId;
        this.tag = tag;
    }

    @Override
    public String execute(TaskList tl) {
        return tl.deleteTaskTag(taskId, tag);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public static DukeCommand parseCommand(String[] userArgs) throws DukeCommandException, DukeArgumentException {
        assert userArgs != null;
        assert userArgs.length != 0;
        assert userArgs[0].equals("deleteTag");

        if (userArgs.length < 2) {
            throw new DukeCommandException("deleteTag");
        }

        assert userArgs.length == 2;

        String[] taskIdAndTag = userArgs[1].split(" ", 2);

        if (taskIdAndTag.length < 2) {
            throw new DukeCommandException("deleteTag");
        } else if (taskIdAndTag[0].equals("")) {
            throw new DukeArgumentException("Task ID cannot be empty");
        } else if (taskIdAndTag[1].equals("")) {
            throw new DukeArgumentException("Tag names cannot be empty");
        } else if (taskIdAndTag[1].contains(",")) {
            throw new DukeArgumentException("Tag names cannot contain \",\"");
        } else if (taskIdAndTag[1].contains("|")) {
            throw new DukeArgumentException("Tag names cannot contain \"|\"");
        }


        int taskId;
        String tag = taskIdAndTag[1];
        try {
            taskId = Integer.parseInt(taskIdAndTag[0]);
        } catch (NumberFormatException nfe) {
            throw new DukeArgumentException("Incorrect argument for command deleteTag, must be an integer");
        }

        return new CommandDeleteTag(taskId, tag);
    }
}
