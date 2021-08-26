package duke.command;

import duke.TaskList;
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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        try{
            if (taskNo == -1 || taskNo + 1 > taskList.getList().size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            String display = taskList.delete(this.taskNo);
            storage.writeToFile(taskList);
            ui.printDelete(display, taskList.getList().size());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printError("Eh... No such task found. Cannot delete.", "(＃￣ω￣)");
        }
    }
}
