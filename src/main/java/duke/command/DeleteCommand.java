package duke.command;

import duke.List;
import duke.Storage;
import duke.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    public static final String COMMAND = "delete";
    private int taskNo;

    public DeleteCommand(int taskNo) {
          this.taskNo = taskNo;
    }

    @Override
    public void execute(List list, Ui ui, Storage storage) throws IOException {
        try{
            if (taskNo == -1 || taskNo + 1 > list.getList().size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            String display = list.delete(this.taskNo);
            storage.writeToFile(list);
            ui.printDelete(display, list.getList().size());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printError("Eh... No such task found. Cannot delete.", "(＃￣ω￣)");
        }
    }
}
