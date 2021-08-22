package duke;

import java.util.Scanner;

/**
 * Implementation for Duke.
 *
 * @author Wong Yun Rui Chris
 */
public class Duke {
    private TaskList list;
    private Storage storage;
    private Ui ui;

    public Duke(String filepath) {
        storage = new Storage();
        list = storage.readData(filepath);
        ui = new Ui();
    }

    /**
     * Executes the Duke
     */
    public void run() {
        ui.showWelcomeMessage();

        Parser parser = new Parser();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            try {
                ui.showReply(parser.handleInput(this.list, input));
                storage.saveData(list);
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            }
            input = sc.nextLine();
        }

        ui.showByeMessage();
    }

    public static void main(String[] args) {
        new Duke("/data/duke.txt").run();
    }
}
