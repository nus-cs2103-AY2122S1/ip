package duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    //true indicates the conversation is ongoing
    public static boolean conversationState;

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner( System.in );
        DukeMessage msg = MessageFactory.createMessage(null);
        msg.display();
        boolean dataLoaded = TaskListCsvHandler.loadAll();
        if(!dataLoaded) {
            DukeMessage noDataMessage = new ErrorMessage("OOPS! Stored data couldn't be loaded!");
        }
        while(conversationState) {
            String userString;

            userString = scan.nextLine().toString();
            msg = MessageFactory.createMessage(userString);

            msg.display();
        }
    }
}
