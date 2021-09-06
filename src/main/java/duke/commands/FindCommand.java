package duke.commands;

import duke.Storage;
import duke.Tasklist;
import duke.Ui;
import duke.exceptions.NoSuchTaskException;
import duke.tasks.Task;

public class FindCommand extends Command {

    private String toFind;

    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws NoSuchTaskException {
        ui.showFindMessage();
        int counter = 1;
        for (int i = 1; i <= tasklist.getTasklistSize(); i++){
            Task temp = tasklist.getTask(i-1);
            if (temp.isMatch(toFind)) {
                System.out.println(String.valueOf(counter) + ". " + temp);
                counter++;
            }

        }
    }
}