package duke.command;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.task.ToDo;
import duke.ui.Ui;

public class TodoCommand extends Command {
    final static String cmd = "list";
    final static String usage = "marks specified task as done";
    final static String format =  "done {task number}";

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
        return cmd;
    }

    public static String getUsage() {
        return usage;
    }

    public static String getFormat() {
        return format;
    }
}
