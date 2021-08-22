import fulfillment.FulfillmentHandler;
import io.FileIO;
import io.InputHandler;
import io.OutputHandler;
import tasks.Task;

import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException {
        InputHandler inputHandler = new InputHandler();
        OutputHandler outputHandler = new OutputHandler();

        Task.loadTasksFromStorage();
        FulfillmentHandler fulfillmentHandler = new FulfillmentHandler(inputHandler, outputHandler);
        fulfillmentHandler.initializeChatbot();
    }
}
