package duke.command;

import java.io.IOException;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;


public class DeleteCommand extends Command {
    public static final String COMMAND = "delete";
    private static final String COMMAND_TYPE = "Delete Command";
    private static final String ERROR_MESSAGE = "Eh... No such task found. Cannot delete.";
    private static final String ERROR_EMOTICON = "('_')";
    private int taskNo;
    private Task deleted;

    /**
     * Constructor for Delete Command
     *
     * @param taskNo Index of task deleted
     *
     */
    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Executes delete command to delete the specific task.
     *
     * @param taskList Current list
     * @param rf Response formatter
     * @param storage Current storage
     * @param history History of the list of previous commands
     * @return Delete message formatted
     * @throws IOException When storing to storage failed
     */
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
            return rf.formatError(ERROR_MESSAGE, ERROR_EMOTICON);
        }
    }

    @Override
    public String undo(TaskList taskList, ResponseFormatter rf, Storage storage) throws IOException {
        taskList.add(deleted);
        storage.writeToFile(taskList);
        return rf.formatUndo(COMMAND_TYPE);
    }
}
