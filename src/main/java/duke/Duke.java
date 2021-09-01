package duke;

import java.util.Scanner;

import duke.command.Command;

/**
 * Main driver for Duke chat bot.
 *
 * @author nzixuan
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor of Duke.
     *
     * @param filePath file path of to retrieve save file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(), storage);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(storage);
        }
    }

    /**
     * Starts Duke chat bot.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        // Create a Scanner object
        Scanner scanner = new Scanner(System.in);
        while (!isExit) {
            String input = scanner.nextLine();
            try {
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    /**
     * Main function for Duke.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
