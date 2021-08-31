package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ExitCommand extends Command {

    public ExitCommand() {}

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

<<<<<<< Updated upstream
    public Boolean isExit() {
=======
    /**
     * Check if user is ending the chatbot.
     * @return True if user is ending the chatbot.
     */
    public boolean isExit() {
>>>>>>> Stashed changes
        return true;
    }

}
