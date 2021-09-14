package agent;

import agent.command.Command;
import agent.data.TaskFileStorage;
import agent.exceptions.DukeException;
import agent.exceptions.InvalidTaskDataException;
import agent.exceptions.TaskFileIoException;
import agent.messages.GreetingMessage;
import agent.parser.Parser;
import agent.tasks.TaskList;

/**
 * Represents the chat bot agent.
 *
 * @author kevin9foong
 */
public class Agent {
    private final TaskList taskList;

    /**
     * Indicates if the agent is to be exited.
     */
    private boolean isExited;

    /**
     * Constructs an instance of the <code>Duke</code> chat bot with the provided input and output handlers.
     *
     * @throws TaskFileIoException      thrown when unable to read the save task file.
     * @throws InvalidTaskDataException thrown when task file data is corrupted.
     */
    public Agent() throws TaskFileIoException, InvalidTaskDataException {
        this.taskList = new TaskList(new TaskFileStorage());
    }

    /**
     * Responds to provided user input.
     *
     * @param userInput user input text to respond to.
     * @return chat bot response to user input.
     * @throws DukeException thrown when unable to handle user input.
     */
    public String respondToUserInput(String userInput) throws DukeException {
        Command userCommand = Parser.parse(userInput);
        setExited(userCommand.isExit());
        return userCommand.execute(taskList);
    }

    /**
     * Returns a <code>String</code> which greets the user upon chat bot start up.
     *
     * @return greeting message.
     */
    public String greetUser() {
        return new GreetingMessage().toString();
    }

    public boolean isExited() {
        return isExited;
    }

    public void setExited(boolean exited) {
        isExited = exited;
    }
}
