package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.NoSuchTaskException;
import duke.tasks.TaskList;

import java.io.IOException;
import java.util.Scanner;

/**
 * Encapsulates the information for a Duke object that contains a Storage, Parser and TaskList object.
 */
public class Duke {
    private TaskList items;
    private final Storage storage;
    private final Parser parser;


    public static void main(String[] args) {
        Duke chatBot = new Duke("data/duke.txt");
        chatBot.start();
    }

    /**
     * Constructs a Duke object with the specified file path for the data file.
     *
     * @param filePath A String representing the file path of the data file.
     */
    public Duke(String filePath) {
        this.items = new TaskList();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
    }

    /**
     * Starts the chat bot by attempting to load the previously stored data.
     * Chat bot will receive, interpret, execute and save data from the recognisable commands from user.
     */
    public void start() {
        try {
            items = storage.loadTask();
        } catch (IOException | NoSuchTaskException e) {
            Ui.notifyError(e.getMessage());
        }

        Ui.greetUser();

        // scanner to take in user's input(s)
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        String exitTag = "bye";
        while(!input.equalsIgnoreCase(exitTag)) {
            try {
                Command action = parser.checkCommandTag(input);
                action.executeCommand(items);
                storage.saveTask(items);
            } catch (DukeException | IOException e) {
                Ui.notifyError(e.getMessage());
            } finally {
                input = scanner.nextLine().trim();
            }
        }

        // close the scanner as the bot is terminated.
        scanner.close();
        Ui.exit();
    }
}
