package duke;

import java.util.Scanner;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {
    public static Storage storage;
    public static TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Personal Assistant Chatbot.
     */
    public void run() {
        ui.greetUser();
        Scanner sc = new Scanner(System.in);
        String command;
        boolean isExit = false; 

        while (!isExit) {
            try {
                System.out.println("What's your next command?\n");
                command = sc.nextLine();  
                isExit = Parser.parse(command);
            } catch (DukeException e) {
                Ui.printFormattedMessage(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}