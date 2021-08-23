package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand implements Command{
    private String indexString;

    public DoneCommand(String indexString){
        this.indexString = indexString;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(indexString) - 1;
            if (index >= t.getSize() || index <= -1) {
                ui.errorFrame(" That task does not exist!");
            } else {
                t.get(index).setDone();
                ui.textFrame("Good job, I have marked the task as done!" + "\n" +
                        t.get(index).toString());
            }
        } catch (NumberFormatException e) {
            ui.errorFrame("That is not a valid index!");
        }
    }
}
