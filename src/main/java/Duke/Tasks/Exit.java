package Duke.Tasks;

import Duke.Ui.Ui;
import Duke.Tool.Storage;
import Duke.Tool.TaskList;

import javafx.application.Platform;

/**
 * Represents the Exit task class
 */
public class Exit extends Task {

    /**
     * Constructs Exit class
     */
    public Exit() {
        super("exit", false);
        this.isExit = true;
    }

    /**
     * Executes input exit task
     *
     * @param tasks
     * @param ui
     * @param storage
     * @return String null
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
        offApplication();
        return null;
    }

    /**
     * Off the application and exit
     */
    public void offApplication() {
        Platform.exit();
        System.exit(0);
    }
}
