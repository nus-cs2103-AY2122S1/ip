package duke.command;

import java.io.IOException;

import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {
    public static final String COMMAND = "done";
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
     * and prints response
     *
     * @param taskList current list
     * @param ui current ui to access print responses
     * @param storage current storage
     * @throws ArrayIndexOutOfBoundsException
     * @throws IOException if writeToFile has a file error
     *
     * @return
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws
            ArrayIndexOutOfBoundsException,
            IOException {
        try {
            if (taskNo == -1 || taskNo + 1 > taskList.getList().size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            String display = taskList.complete(this.taskNo);
            storage.writeToFile(taskList);
            ui.printDone(display);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printError(
                    "Eh... No such task found. Cannot mark as done.",
                    "(＃￣ω￣)"
            );
        }
    }

    /**
     * Executes Done Command to mark task as completed, stores updated list in file
     * and formats response
     *
     * @param taskList current list
     * @param rf Response Formatter
     * @param storage current storage
     * @throws ArrayIndexOutOfBoundsException
     * @throws IOException if writeToFile has a file error
     *
     * @return
     */
    @Override
    public String execute(TaskList taskList, ResponseFormatter rf, Storage storage) throws
            ArrayIndexOutOfBoundsException,
            IOException {
        try {
            if (taskNo == -1 || taskNo + 1 > taskList.getList().size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            String display = taskList.complete(this.taskNo);
            storage.writeToFile(taskList);
            return rf.formatDone(display);
        } catch (ArrayIndexOutOfBoundsException e) {
            return rf.formatError(
                    "Eh... No such task found. Cannot mark as done.",
                    "(＃￣ω￣)"
            );
        }
    }
}
