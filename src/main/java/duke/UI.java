package duke;

import java.util.Scanner;

public class UI {
    public void initiate() {
        DukeMessage msg = MessageFactory.createMessage(null);
        msg.display();
    }

    public String readInput() {
        Scanner scan = new Scanner( System.in );
        String userString = scan.nextLine().toString();
        return userString;
    }

}
