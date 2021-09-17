package duke.command;

import java.io.IOException;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;


public class DoneCommand extends Command {
    public static final String COMMAND = "done";
    private static final String COMMAND_TYPE = "Done Command";
    private static final String ERROR_MESSAGE = "Eh... No such task found. Cannot mark as done.";
    private static final String ERROR_EMOTICON = "(＃-w-)";
    private int taskNo;

    /**
     * Constructor for Done Command
     *
     * @param taskNo the index of task completed
     *
     */
    public DoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }


    /**
     * Executes Done Command to mark task as completed, stores updated list in file
     * and formats response.
     *
     * @param taskList Current list
     * @param rf Response Formatter
     * @param storage Current storage
     * @param history List of previous commands
     * @throws ArrayIndexOutOfBoundsException When the task number given is not within the length of the task list
     * @throws IOException If writeToFile has a file error
     *
     * @return Done message formatted
     */
    @Override
    public String execute(TaskList taskList, ResponseFormatter rf, Storage storage, History history) throws
            ArrayIndexOutOfBoundsException,
            IOException {
        try {
            if (taskNo == -1 || taskNo + 1 > taskList.getList().size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            String display = taskList.complete(this.taskNo);
            storage.writeToFile(taskList);
            history.addHistory(this);
            return rf.formatDone(display);
        } catch (ArrayIndexOutOfBoundsException e) {
            return rf.formatError(ERROR_MESSAGE, ERROR_EMOTICON);
        }
    }

    @Override
    public String undo(TaskList taskList, ResponseFormatter rf, Storage storage) throws IOException {
        taskList.uncheck(this.taskNo);
        storage.writeToFile(taskList);
        return rf.formatUndo(COMMAND_TYPE);
    }
}
