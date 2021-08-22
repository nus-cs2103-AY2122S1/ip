package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.NoSuchTaskException;
import duke.tasks.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    // list used to store text entered by user
    private TaskList items;

    private final Storage storage;
    private final Parser parser;


    public static void main(String[] args) {
        Duke chatBot = new Duke("data/duke.txt");
        chatBot.start();
    }

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.parser = new Parser();
    }

    /**
     * Starts the chat bot.
     * Chat bot starts receiving commands from user and echo back the command until terminated.
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

        // duke.commands.Command Tags for the chat bot
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
