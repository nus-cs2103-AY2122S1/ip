package duke;

import duke.command.Command;
import duke.controller.MainWindow;

/**
 * The Duke bot.
 *
 * @author Luo Zhijie
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private MainWindow main;
    private Parser parser;


    /**
     * Constructor for Duke bot
     *
     * @param filePath Path to duke file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = storage.convertFileToTaskList();
        ui = new Ui();
        parser = new Parser();
    }

    private void initializeReminder() {
        ui.setMainWindow(main);
        ui.addReminder(tasks);
    }

    public void setMainWindow(MainWindow main) {
        this.main = main;
        main.greetUser(ui.greet());
        initializeReminder();
    }

    /**
     * Gets response from Duke.
     *
     * @param input Input string from user.
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Shows whether duke need to exit.
     *
     * @return Value indicating whether duke need to exit.
     */
    public boolean isExit() {
        return ui.getExitStatus();
    }
}
