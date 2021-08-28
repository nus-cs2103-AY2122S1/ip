package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.NoSuchTaskException;
import duke.tasks.TaskList;

/**
 * Encapsulates the information for a Duke object that contains a Storage, Parser and TaskList object.
 */
public class Duke {
    private TaskList items;
    private final Storage storage;
    private final Parser parser;

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
     * Initialises and runs the program.
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        Duke chatBot = new Duke("data/duke.txt");
        chatBot.start();
    }

    /**
     * Starts the chat bot by attempting to load the previously stored data.
     * Chat bot will receive, interpret, execute and save data from the recognisable commands from user.
     */
    public void start() {
        try {
            this.items = this.storage.loadTask();
        } catch (IOException | NoSuchTaskException e) {
            Ui.notifyError(e.getMessage());
        }

        Ui.greetUser();

        // scanner to take in user's input(s)
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        String exitTag = "bye";
        while (!input.equalsIgnoreCase(exitTag)) {
            try {
                Command action = this.parser.checkCommandTag(input);
                action.executeCommand(this.items);

                this.storage.saveTask(this.items);
            } catch (DukeException | IOException e) {
                Ui.notifyError(e.getMessage());
            } finally {
                if (scanner.hasNext()) {
                    input = scanner.nextLine().trim();
                } else {
                    break;
                }
            }
        }

        // close the scanner as the bot is terminated.
        scanner.close();
        Ui.exit();
    }
}
