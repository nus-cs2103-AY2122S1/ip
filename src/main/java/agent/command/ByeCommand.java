package agent.command;

import agent.messages.ByeMessage;
import agent.tasks.TaskList;

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
     * @param taskList handles task operations including adding, deleting, marking as done and retrieval.
     * @return response message by chat bot for bye command.
     */
    @Override
    public String execute(TaskList taskList) {
        return new ByeMessage().toString();
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
