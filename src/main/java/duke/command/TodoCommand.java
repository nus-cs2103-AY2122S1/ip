package duke.command;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.task.ToDo;
import duke.ui.Ui;

public class TodoCommand extends Command {
    static final String CMD = "todo";
    static final String USAGE = "add tasks to be done";
    static final String FORMAT = "todo {description}";

    private String name;

    public TodoCommand(String name) {
        this.name = name;
    }

    public TodoCommand() {}

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo toDo = new ToDo(name);
        tasks.addTask(toDo);
        storage.updateData(tasks);
        return ui.showTaskAdded(toDo, tasks.getSize());
    }

    public static String getCmd() {
        return CMD;
    }

    public static String getUsage() {
        return USAGE;
    }

    public static String getFormat() {
        return FORMAT;
    }
}
