package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

public class SnoozeCommand extends Command {
    private final TaskList taskList;
    private final Ui textUi;
    private final int taskIndex;
    private final LocalDate newTime;

    public SnoozeCommand(TaskList taskList, Ui textUi, int index, LocalDate time) {
        this.taskList = taskList;
        this.textUi = textUi;
        this.taskIndex = index;
        this.newTime = time;
    }

    @Override
    public String execute() {
        try {
            taskList.snooze(taskIndex - 1, newTime);
            return textUi.snooze(taskList, taskIndex - 1);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
