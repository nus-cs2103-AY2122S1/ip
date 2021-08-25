package duke;

import duke.command.Command;

import java.util.Scanner;

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
     * Start Duke chat bot.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
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

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
