package duke.command;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;


public class ErrorCommand extends Command {
    private String error;
    private String emoticon;

    /**
     * Constructor for Error Command
     * @param error Error message
     *
     */
    public ErrorCommand(String error) {
        this.error = error;
        this.emoticon = null;
    }

    /**
     * Constructor for Error command
     *
     * @param error Error message
     * @param emoticon Emoticon to express dissatisfaction for the error committed
     *
     */
    public ErrorCommand(String error, String emoticon) {
        this.error = error;
        this.emoticon = emoticon;
    }

    /**
     * Executes Error Command to returns error response.
     *
     * @param taskList Current list
     * @param rf Response Formatter
     * @param storage Current storage
     * @param history List of previous commands
     *
     * @return Error message formatted
     */
    @Override
    public String execute(TaskList taskList, ResponseFormatter rf, Storage storage, History history) {
        if (this.emoticon != null) {
            return rf.formatError(this.error, this.emoticon);
        } else {
            return rf.formatError(this.error);
        }
    }
}
