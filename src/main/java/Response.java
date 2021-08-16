/**
 * Response class contains the logic for processing the commands from Duke.
 * At level-2, it supports (i) the list command, (ii) the bye command,
 * (iii) as well as adding items to the list
 */

public class Response {
    private String[] lst = new String[100];
    private int itemCount = 0;

    /**
     * Simple function that handles the bye command.
     * @return A string to bids farewell to the user
     */
    String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Simple function that handles the list command.
     * @return A numbered list with the items that were added
     */
    String list() {
        String res = "";
        for (int i = 0; i < itemCount; i++) {
            res += (i + 1) + ". " + lst[i] + "\n";
        }
        return res;
    }

    /**
     * Handles the input commands from Duke.
     * @param string the command input from Duke
     * @return a numbered list, a farewell, or an added message 
     */
    String output(String string) {
        if (string.equals("list")) {
            return list();
        } else if (string.equals("bye")) {
            return bye();
        }
        lst[itemCount] = string;
        itemCount++;
        return "added: " + string;
    }
}
