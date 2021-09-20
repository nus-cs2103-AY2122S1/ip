package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private final TaskList taskList;
    private final Ui textUi;
    private final String taskInfo;
    private final LocalDate taskTime;


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

