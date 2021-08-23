package duke.command;

import duke.DukeUi;
import duke.task.TaskList;
import duke.task.ToDo;

public class CommandTodo extends DukeCommand {
    ToDo task;

    public CommandTodo(ToDo t) {
        this.task = t;
    }

    @Override
    public void execute(TaskList tl) {
        DukeUi.printLine(tl.addTask(task));
    };

    @Override
    public boolean isExit() {
        return false;
    };
}
