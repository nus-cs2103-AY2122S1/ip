package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.NoSuchTaskException;
import duke.tasks.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
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
            this.items = this.storage.loadTask();
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
                Command action = this.parser.checkCommandTag(input);
                action.executeCommand(this.items);

                this.storage.saveTask(this.items);
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
