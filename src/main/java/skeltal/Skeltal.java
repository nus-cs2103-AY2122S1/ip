package skeltal;

import java.util.Scanner;

/**
 * Represents the main class of the Skeltal chatbot app.
 */
public class Skeltal {

    /**
     * A method that scans for user input and prints a response.
     */
    public static void run() {
        TaskList.loadTaskList(Storage.loadFile());
        Ui.introduction();
        Scanner sc = new Scanner(System.in);
        boolean shutdown = false;
        while (!shutdown) {
            String userReply = sc.nextLine();
            if (userReply.equals("bye")) {
                shutdown = Ui.bye();
                sc.close();
                continue;
            } else {
                Parser.response(userReply);
            }
        }
    }
}
