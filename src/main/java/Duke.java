import java.util.Scanner;

public class Duke {
    //true indicates the conversation is ongoing
    public static boolean conversationState;

    public static void main(String[] args) {
        Scanner scan = new Scanner( System.in );
        DukeMessage msg = MessageFactory.createMessage(null);
        msg.display();
        while(conversationState) {
            String userString;

            userString = scan.nextLine().toString();
            msg = MessageFactory.createMessage(userString);

            msg.display();
        }
    }
}
