package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.exception.InvalidTaskNumException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TimeTask;

public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";
    public static final String NAME_KEYWORD = " /n ";
    public static final String TIME_KEYWORD = " /t ";
    public static final String SPLIT_REGEX = NAME_KEYWORD + "|" + TIME_KEYWORD;
    public static final String SUCCESS_MSG = "You have successfully updated the following task:\n";

    private int taskNum;
    private String newName;
    private String newTime;

    /**
     * Creates new UpdateCommand that updates both the name and time of a given task.
     *
     * @param taskNum the index of the specified task
     * @param newName the given new name of the task
     * @param newTime the given new time of the task
     */
    public UpdateCommand(int taskNum, String newName, String newTime) {
        this.taskNum = taskNum;
        this.newName = newName;
        this.newTime = newTime;
    }

    /**
     * Creates new UpdateCommand that updates either the name or time of a given task.
     *
     * @param taskNum the index of the specified task
     * @param newField the given new name or time of the task
     * @param isName true if name is updated, false if time is updated.
     */
    public UpdateCommand(int taskNum, String newField, boolean isName) {
        this.taskNum = taskNum;
        if (isName) {
            this.newName = newField;
        } else {
            this.newTime = newField;
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (taskNum >= tasks.numTasks()) {
            throw new InvalidTaskNumException(taskNum + 1);
        }

        Task task = tasks.getTask(taskNum);

        if (newName != null) {
            task.editName(newName);
        }

        if (newTime != null) {
            if (task instanceof TimeTask) {
                TimeTask timeTask = (TimeTask) task;
                timeTask.editTime(newTime);
            } else {
                throw new DukeException("you cannot edit the time of this task!");
            }
        }

        return SUCCESS_MSG + task.toString();
    }
}
