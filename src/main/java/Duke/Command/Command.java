package Duke.Command;

import Duke.Main.TaskList;

public abstract class Command {
    private String description;
    private TaskList taskList;

    public Command(String description, TaskList taskList) {
        this.description = description;
        this.taskList = taskList;
    }

    public abstract String reply();
}
