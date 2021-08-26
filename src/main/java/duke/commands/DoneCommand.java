package duke.commands;

import duke.DukeExceptions;
import duke.Ui;
import duke.storage.Storage;

public class DoneCommand extends Command {
    int index;

    public DoneCommand(int input) {
        this.index = input - 1;
    }


    @Override
    public boolean execute(Ui ui, Storage storage) {
        try {
            storage.markAsFinished(index);
            ui.print("Well done! I have marked the following as finished: \n" + storage.getTask(index));
            storage.save();
        } catch (IndexOutOfBoundsException e) {
            ui.print("Oops, the list is not that big!");
        } catch (DukeExceptions e) {
            ui.printException(e);
        }
        return false;
    }
}
