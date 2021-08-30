package duke;

import java.util.Scanner;

public class UI {
    public void initiate() {
        DukeMessage msg = MessageFactory.createMessage(null);
        msg.display();
    }
    /**
     * Passes the user input to Duke as a String.
     *
     * @return String Returns the string typed in by the user.
     */
    public String readInput() {
        Scanner scan = new Scanner( System.in );
        String userString = scan.nextLine().toString();
        return userString;
    }

}
