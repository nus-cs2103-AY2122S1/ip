package duke.command;

import java.io.IOException;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;


public class DeleteCommand extends Command {
    public static final String COMMAND = "delete";
    private int taskNo;
    private Task deleted;

    /**
     * Constructor for Delete Command
     *
     * @param taskNo the index of task deleted
     *
     */
    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Executes Delete Command to delete task, stores updated list in file
     * and prints response
     *
     * @param taskList current list
     * @param ui current ui to access print responses
     * @param storage current storage
     * @throws IOException if writeToFile has a file error
     *
     * @return
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, History history) throws IOException {
        try {
            if (taskNo == -1 || taskNo + 1 > taskList.getList().size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            String display = taskList.delete(this.taskNo);
            storage.writeToFile(taskList);
            ui.printDelete(display, taskList.getList().size());
            history.addHistory(this);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printError("Eh... No such task found. Cannot delete.", "(＃￣ω￣)");
        }
    }

    @Override
    public String execute(TaskList taskList, ResponseFormatter rf,
                          Storage storage, History history) throws IOException {
        try {
            if (taskNo == -1 || taskNo + 1 > taskList.getList().size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            deleted = taskList.getList().get(this.taskNo);
            String display = taskList.delete(this.taskNo);
            storage.writeToFile(taskList);
            history.addHistory(this);
            return rf.formatDelete(display, taskList.getList().size());
        } catch (ArrayIndexOutOfBoundsException e) {
            return rf.formatError("Eh... No such task found. Cannot delete.", "(＃￣ω￣)");
        }
    }

    @Override
    public String undo(TaskList taskList, ResponseFormatter rf, Storage storage) throws IOException {
        System.out.println("undoing delete");
        taskList.add(deleted);
        storage.writeToFile(taskList);
        return rf.formatUndo("Delete Command");
    }
}
