package duke;

import duke.parser.Parser;
import duke.storage.Storage;

import java.io.IOException;
import java.util.Scanner;

/**
 * This is a Duke application, which allows for user interaction.
 */
public class Duke {
    private static Parser parser;
    private static Storage storage;
    private static final String LINE = "-----------------------------------------";

    /**
     * Constructor for Duke.
     */
    public Duke() throws IOException {
        parser = new Parser();
        storage = new Storage("data/duke.txt");
        storage.readFromFile();
    }

    /**
     * Main method of the application.
     * @param args -
     * @throws IOException an IOException
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String task = sc.nextLine();
        storage.readFromFile();

        while (!task.equals("bye")) {
            try {
                System.out.println(parser.parseTask(task));
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            task = sc.nextLine();
        }
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
        sc.close();
    }

    /**
     * Gets the response from Duke tothe user input.
     */
    public String getResponse(String input) throws DukeException, IOException {
        try {
            return parser.parseTask(input);
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}
