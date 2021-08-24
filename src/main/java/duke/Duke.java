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

    /**
     * Public constructor to initialise Duke.
     *
     * @param filepath The String representing the filepath to the Duke.txt file containing
     *                 the previous saved data
     */
    public Duke(String filepath) {
        storage = new Storage(filepath);
        list = storage.readData();
        ui = new Ui();
    }

    /**
     * Executes the Duke to start up for use.
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
