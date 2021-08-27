package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.command.Command;

/**
 * This class represents a {@code Duke} object. {@code Duke} responds to
 * predefined user inputs and outputs various messages in response to the user
 * input.
 * 
 * @author Elizabeth Chow
 */
public class Duke {
    private TaskList tasks;
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructs a {@code Duke} object.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Shows a welcome message when {@code Duke} starts running. Continuously takes
     * in user inputs until "bye" is entered.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);

        while (!isExit) {
            try {
                Command c = Parser.parse(scanner.nextLine().trim());
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }
}
