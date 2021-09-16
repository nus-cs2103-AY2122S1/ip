package duke;
import java.io.IOException;
import java.util.Scanner;

import duke.command.Command;
import duke.task.TaskList;

/**
 * Main Duke class.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() { }

    /**
     * Constructor for Duke.
     *
     * @param filePath Path of the .txt file the tasks are stored in.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            System.out.println(storage.load());
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);

        while (!isExit) {
            try {
                Command c = Parser.parse(new Input(scanner.nextLine().trim()));
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * Main argument for Duke.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(new Input(input));
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            //not OOP!
            return e.getMessage();
        }
    }

}
