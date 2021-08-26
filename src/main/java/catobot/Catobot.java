package catobot;

import catobot.command.Command;
import catobot.command.Parser;
import catobot.exception.BotException;
import catobot.exception.LoadingException;
import catobot.item.TaskList;

/**
 * Represents a Catobot.
 */
public class Catobot {
    public static final String NAME = "Catobot";

    /** Storage of the tasks. */
    private final Storage storage;

    /** List of tasks for the Catobot. */
    private TaskList tasks;

    /** User interface of the Catobot. */
    private final Ui ui;

    /**
     * Constructor for Catobot.
     *
     * @param filePath Location of the local memory.
     */
    private Catobot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (LoadingException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Catobot.
     */
    private void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BotException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Catobot("./data/Catobot.txt").run();
    }

}
