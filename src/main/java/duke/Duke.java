package duke;

import duke.Command.Command;

/**
 * Main class of the bot
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Constructs Duke object with Ui, Storage and Tasklist.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs bot program
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }


    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String output = c.execute(tasks, ui, storage);
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }


    }

}
