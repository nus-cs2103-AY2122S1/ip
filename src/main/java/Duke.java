import java.util.Scanner;

public class Duke {
    //true indicates the conversation is ongoing
    public static boolean conversationState;
    private static MessageFactory factory = new MessageFactory();

    public static void main(String[] args) {
        DukeMessage msg = factory.createMessage(null);
        msg.display();
        while(conversationState) {
            String userString;
            Scanner scan = new Scanner( System.in );

            userString = scan.nextLine().toString();
            msg = factory.createMessage(userString);

            msg.display();
        }
    }
}
