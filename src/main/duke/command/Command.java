package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

abstract public class Command {

    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

<<<<<<< Updated upstream
    abstract public Boolean isExit();
=======
    /**
     * Check if user is ending the chatbot.
     * @return True if user is ending the chatbot.
     */
    abstract public boolean isExit();
>>>>>>> Stashed changes

}
