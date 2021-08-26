package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.task.Todos;
import duke.Ui;

import java.io.IOException;

public class TodoCommand extends Command {
    public static final String COMMAND = "todo";
    private String desc;

    public TodoCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        if (this.desc.equals("")) {
            throw new IllegalArgumentException();
        }
        Todos newTodo = new Todos(this.desc);
        taskList.add(newTodo);
        storage.writeToFile(taskList);
        ui.printAdd(newTodo, taskList.getList().size());
    }
}
