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
     * The constructor for Exit task
     */
    public Exit() {
        super("exit", false);
        this.isExit = true;
    }

    /**
     * Execute the task.  
     * @param task
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList task, Ui ui, Storage storage) {
        ui.exit();
        applicationOff();
        return null;
    }

    public void applicationOff() {
        Platform.exit();
        System.exit(0);
    }
}
