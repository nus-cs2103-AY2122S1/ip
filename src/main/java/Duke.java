import fulfillment.FulfillmentHandler;
import io.UserInputHandler;
import io.UserOutputHandler;
import tasks.TaskList;

import java.io.IOException;

public class Duke {
    private UserInputHandler userInputHandler;
    private UserOutputHandler userOutputHandler;
    private FulfillmentHandler fulfillmentHandler;
    private TaskList taskList;

    public Duke() throws IOException {
        this.userInputHandler = new UserInputHandler();
        this.userOutputHandler = new UserOutputHandler();
        this.taskList = new TaskList();
        this.fulfillmentHandler = new FulfillmentHandler(userInputHandler,
                userOutputHandler, taskList);
    }

    public static void main(String[] args) throws IOException {
        new Duke().fulfillmentHandler.runChatbot();
    }
}
