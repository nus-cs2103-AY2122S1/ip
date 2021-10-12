package duke.command;

import duke.exception.DukeArgumentException;
import duke.exception.DukeCommandException;
import duke.task.TaskList;

public class CommandAddTag extends DukeCommand {
    private int taskId;
    private String tag;

    public CommandAddTag(int taskId, String tag) {
        this.taskId = taskId;
        this.tag = tag;
    }

    /**
     * Adds the provided tag to the task in task list.
     *
     * @param tl Task list for the user.
     */
    @Override
    public String execute(TaskList tl) {
        return tl.addTaskTag(taskId, tag);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Parses the user input into the right format for the command
     *
     * @param userArgs Arguments to the command as provided by the user.
     */
    public static DukeCommand parseCommand(String[] userArgs) throws DukeCommandException, DukeArgumentException {
        assert userArgs != null;
        assert userArgs.length != 0;
        assert userArgs[0].equals("addTag");

        if (userArgs.length < 2) {
            throw new DukeCommandException("addTag");
        }

        assert userArgs.length == 2;

        String[] taskIdAndTag = userArgs[1].split(" ", 2);

        if (taskIdAndTag.length < 2) {
            throw new DukeCommandException("addTag");
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
            throw new DukeArgumentException("Incorrect argument for command addTag, must be an integer");
        }

        return new CommandAddTag(taskId, tag);
    }
}
