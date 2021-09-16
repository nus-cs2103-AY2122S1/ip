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
     * @param error error message
     *
     */
    public ErrorCommand(String error) {
        this.error = error;
        this.emoticon = null;
    }

    /**
     * Constructor for Error command
     *
     * @param error error message
     * @param emoticon emoticon to express dissatisfaction for the error committed
     *
     */
    public ErrorCommand(String error, String emoticon) {
        this.error = error;
        this.emoticon = emoticon;
    }

    /**
     * Executes Error Command to returns error response
     *
     *  @param taskList current list
     * @param rf Response Formatter
     * @param storage current storage
     *
     * @return
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
