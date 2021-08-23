package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
    private Task toAdd;

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Alright! I added this task: \n" + toAdd.toString() + "\n");
        tasks.add(toAdd);
        System.out.println(String.format("You have %d task(s) at the moment!\n", tasks.size()));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        storage.write(tasks);
    }
}
