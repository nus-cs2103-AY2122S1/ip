package duke.commands;

import duke.tasks.ToDo;
import duke.Ui;
import duke.storage.Storage;

public class ToDoCommand extends Command {
    ToDo toDo;
    public ToDoCommand(ToDo toDo) {
        this.toDo = toDo;
    }

    @Override
    public boolean execute(Ui ui, Storage storage) {
        storage.addToList(toDo);
        ui.print("Got it! I've added this task to the list: \n"
                + "  " + toDo.toString() + '\n'
                + String.format("Now you have %d tasks in the list", storage.getSize()));
        storage.save();
        return false;
    }
}
