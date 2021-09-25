package duke;

import java.util.Scanner;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of
 * various things.
 */
public class Duke {
    private static Storage storage;
    private static TaskList tasks;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        try {
            storage = new Storage("data/duke.txt");
            tasks = new TaskList(getStorage().loadTasks());
        } catch (DukeException e) {
            tasks = new TaskList();
            assert (tasks.getNumTasks() == 0);
        }
    }

    public static Storage getStorage() {
        return Duke.storage;
    }

    /**
     * Returns the response from Duke given the user input to be shown on the GUI.
     *
     * @param input the command the user typed
     */
    public String getResponse(String input) {
        String response;
        try {
            response = Parser.parse(input);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Starts the Personal Assistant Chatbot for text-based UI.
     */
    public void run() {
        Ui ui = new Ui();
        ui.printUserGreeting();
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            try {
                System.out.println("What's your next command?\n");
                String command = sc.nextLine();
                String response = Parser.parse(command);
                ui.printFormattedMessage(response);
                if (response.contains("Bye")) {
                    isExit = true;
                }
            } catch (DukeException e) {
                ui.printFormattedMessage(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
