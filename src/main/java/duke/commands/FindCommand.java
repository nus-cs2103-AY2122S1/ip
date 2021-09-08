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
    public String execute(Tasklist tasklist, Ui ui, Storage storage) throws NoSuchTaskException {
        String result = ui.showFindMessage();
        int counter = 1;
        for (int i = 1; i <= tasklist.getTasklistSize(); i++) {
            Task temp = tasklist.getTask(i - 1);
            if (temp.isMatch(toFind)) {
                result += "\n";
                result += (String.valueOf(counter) + ". " + temp);
                counter++;
            }

        }
        return result;
    }
}
