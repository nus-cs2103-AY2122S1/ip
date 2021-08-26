package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;
import duke.task.ToDo;

public class ToDoCommand extends Command{
    String task;

    public ToDoCommand(String task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ToDo item = new ToDo(this.task);
        tasks.add(item, true);
        String saveFileString = tasks.save();
        storage.save(saveFileString);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
