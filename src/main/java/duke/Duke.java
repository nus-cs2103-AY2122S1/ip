package duke;

import java.io.IOException;

/**
 * The main class running Duke.
 */
public class Duke {
    //true indicates the conversation is ongoing
    public static boolean conversationState;
    private static UI dukeUI = new UI();

    public static void main(String[] args) throws IOException {

        dukeUI.initiate();
        boolean dataLoaded = TaskListCsvHandler.loadAll();
        if(!dataLoaded) {
            DukeMessage noDataMessage = new ErrorMessage("OOPS! Stored data couldn't be loaded!");
        }
        while(conversationState) {
            DukeMessage msg = MessageFactory.createMessage(dukeUI.readInput());
            msg.display();
        }
    }
}
