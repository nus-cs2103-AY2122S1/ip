package duke.command;

import java.time.LocalDate;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeadlineCommand extends Command {
    private final TaskList taskList;
    private final Ui textUi;
    private final String taskInfo;
    private final LocalDate taskTime;

    /**
     * Constructor of DeadlineCommand class. Initialize a DeadlineCommand instance
     * from a given TaskList, Ui, taskInfo, taskTime.
     *
     * @param taskList A list of tasks
     * @param ui A user interface
     * @param taskInfo The description of task
     * @param time The time when task happen
     */
    public DeadlineCommand(TaskList taskList, Ui ui, String taskInfo, LocalDate time) {
        this.taskList = taskList;
        this.textUi = ui;
        this.taskInfo = taskInfo;
        this.taskTime = time;
    }

    @Override
    public String execute() {
        Task deadline = new Deadline(taskInfo, taskTime);
        taskList.add(deadline);
        return textUi.add(taskList, deadline);
    }

}

