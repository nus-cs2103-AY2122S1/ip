package duke;

import duke.commands.Command;

/**
 * This class represents the Duke chatbot. It is the main class from which the chatbot is run.
 */
public class Duke {
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a Duke object.
     * 
     * @param filePath Path of file where list of tasks is stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    
    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }

    /**
     * Starts the Duke chatbot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullUserCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullUserCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    


}
