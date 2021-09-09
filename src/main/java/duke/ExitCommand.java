package duke;

import javafx.application.Platform;

/**
 * Represents an exit command that will end the application run.
 */
public class ExitCommand extends Command {

    /**
     * Displays goodbye message on UI.
     *
     * @param tasks Task list component.
     * @param storage Storage component.
     * @param ui UI component.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showGoodbye();
        Platform.exit();
    }
}
