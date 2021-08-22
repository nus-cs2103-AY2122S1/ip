package duke.command;

import duke.io.UserOutputHandler;
import duke.messages.ByeMessage;
import duke.tasks.TaskList;
/**
 * Represents user command to add a <code>ToDo</code> to persisted Tasks.
 *
 * @author kevin9foong
 */
public class ByeCommand extends Command {
    public ByeCommand(String userInputBody) {
        super(userInputBody);
    }

    /**
     * Writes to the user to say bye.
     *
     * @param userOutputHandler handles outputting messages to the output destination.
     * @param taskList          handles task operations including adding, deleting, marking as done and retrieval.
     */
    @Override
    public void execute(UserOutputHandler userOutputHandler, TaskList taskList) {
        userOutputHandler.writeMessage(new ByeMessage());
    }

    /**
     * Returns true to indicate program should terminate after command is executed.
     *
     * @return true to indicate program should terminate after command is executed.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
