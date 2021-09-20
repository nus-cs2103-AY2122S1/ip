package duke.command;

import java.time.LocalDate;

import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class EventCommand extends Command {
    private final TaskList taskList;
    private final Ui textUi;
    private final String taskInfo;
    private final LocalDate taskTime;

    /**
     * Constructor of EventCommand class. Initialize a EventCommand instance
     * from a given TaskList, Ui, taskInfo, taskTime.
     *
     * @param taskList A list of tasks
     * @param ui A user interface
     * @param taskInfo The description of task
     * @param time The time when task happen
     */
    public EventCommand(TaskList taskList, Ui ui, String taskInfo, LocalDate time) {
        this.taskList = taskList;
        this.textUi = ui;
        this.taskInfo = taskInfo;
        this.taskTime = time;
    }

    @Override
    public String execute() {
        Task event = new Event(taskInfo, taskTime);
        taskList.add(event);
        return textUi.add(taskList, event);
    }

}

