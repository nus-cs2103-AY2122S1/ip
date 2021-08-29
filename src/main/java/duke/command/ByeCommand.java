package duke.command;

import duke.io.UserOutputHandler;
import duke.messages.ByeMessage;
import duke.tasks.TaskList;

/**
 * Represents user command to terminate current running instance of Duke.
 *
 * @author kevin9foong
 */
public class ByeCommand extends Command {

    /**
     * Constructs instance of <code>ByeCommand</code> which when executed says bye to user and
     * terminates current running Duke instance.
     *
     * @param userInputBody not being utilised.
     */
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
