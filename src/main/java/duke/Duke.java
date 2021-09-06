package duke;

import duke.data.TaskFileStorage;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidTaskDataException;
import duke.exceptions.TaskFileIoException;
import duke.fulfillment.FulfillmentHandler;
import duke.tasks.TaskList;

/**
 * Main class representing the Duke helper chat bot.
 *
 * @author kevin9foong
 */
public class Duke {
    private final FulfillmentHandler fulfillmentHandler;

    /**
     * Constructs an instance of the <code>Duke</code> chat bot with the provided input and output handlers.
     *
     * @throws TaskFileIoException      thrown when unable to read the save task file.
     * @throws InvalidTaskDataException thrown when task file data is corrupted.
     */
    public Duke() throws TaskFileIoException, InvalidTaskDataException {
        TaskList taskList = new TaskList(new TaskFileStorage());
        this.fulfillmentHandler = new FulfillmentHandler(taskList);
    }

    /**
     * Responds to provided user input.
     *
     * @param userInput user input text to respond to.
     * @return chat bot response to user input.
     * @throws DukeException thrown when unable to handle user input.
     */
    public String respondToUserInput(String userInput) throws DukeException {
        return this.fulfillmentHandler.getResponseToUserInput(userInput);
    }

    /**
     * Returns a <code>String</code> which greets the user upon chat bot start up.
     *
     * @return greeting message.
     */
    public String greetUser() {
        return this.fulfillmentHandler.getGreetingMessage();
    }
}
