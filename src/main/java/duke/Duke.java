package duke;

import duke.command.Command;

/**
 * Duke is the main class that handles the overall execution of the program.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke constructor that takes in the file path for the save file.
     *
     * @param filePath the file path of the save file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.produceReadableString(), this.storage);
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * This method runs the program.
     *
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.receiveUserInput();
                ui.showLine();
                Command c = Parser.parseCommand(userInput);
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }


    public static void main(String[] args) {
        new Duke("saves/saves.txt").run();
    }

}
