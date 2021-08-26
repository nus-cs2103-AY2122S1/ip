package duke.command;

import duke.TaskList;
import duke.ui.Ui;
import duke.tasks.taskType;

public class EventCommand extends Command {
    private String[] fields;

    public EventCommand(String[] fields) {
        this.fields = fields;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.addTask(taskType.EVENT, fields);
        Ui.showAddedTask(taskList);
        Ui.showTaskCount(taskList);
    }

    @Override
    public String getType() {
        return "event";
    }
}

