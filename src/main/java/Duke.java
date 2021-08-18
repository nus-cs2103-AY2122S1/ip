import fulfillment.FulfillmentHandler;

import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException {
        FulfillmentHandler fulfillmentHandler = new FulfillmentHandler();
        fulfillmentHandler.initializeChatbot();
    }
}
