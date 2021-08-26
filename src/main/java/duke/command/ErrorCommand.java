package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

import java.io.IOException;

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
     * Executes Error Command to print error response
     *
     * @param taskList current list
     * @param ui current ui to access print responses
     * @param storage current storage
     *
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.emoticon != null) {
            ui.printError(this.error, this.emoticon);
        } else {
            ui.printError(this.error);
        }
    }
}
