package Duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * An UI that recieve's user's input to run Duke
 * and prints out the welcome message to the user.
 * UI also carries the parser created.
 */
public class Recieve {
    private String input;
    private Parser parser;

    /**
     * A public constructor to create Recieve.
     * @param parser The parser created when user starts session with Duke.
     */
    public Recieve(Parser parser) {
        this.parser = parser;
    }

    /**
     * Runs the Duke Application.
     */
    public void run() {
        System.out.println("Hello! I'm Duke\n" +
                "To add a Duke.Todo, type -> todo <Description> \n" +
                "To add a Duke.Deadline, type -> deadline <Description> /by <deadline>\n" +
                "To add an Duke.Event, type -> event <Description> /at <details>\n" +
                "To mark as done, type -> done <task list index>\n" +
                "To see all of your tasks, type -> list\n" +
                "To end session, type -> bye\n" +
                "What can I do for you today?");

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                parser.getStorage().load();
                input = sc.nextLine();

                if (input.equals("bye")) {
                    parser.endSession();
                    break;
                } else if (input.startsWith("done ") || input.startsWith("done")) {
                    parser.done(input);
                } else if (input.equals("list")) {
                    parser.list();
                } else if (input.startsWith("delete ") || input.startsWith("delete")) {
                    parser.delete(input);
                } else {
                    parser.add(input);
                }
            } catch (DukeException | InvalidTaskIndexException | InvalidFormatException | IOException e) {
                System.out.println(e.toString());
            }
        }
        sc.close();
    }


}
