package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    public static final String COMMAND = "delete";
    private int taskNo;

    /**
     * Constructor for Delete Command
     * @param taskNo the index of task deleted
     *
     */
    public DeleteCommand(int taskNo) {
          this.taskNo = taskNo;
    }

    /**
     * Executes Delete Command to delete task, stores updated list in file
     * and prints response
     * @param taskList current list
     * @param ui current ui to access print responses
     * @param storage current storage
     * @throws IOException if writeToFile has a file error
     *
     */
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
