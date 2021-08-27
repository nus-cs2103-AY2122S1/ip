package duke;

import java.util.ArrayList;

/**
 * The Duke bot.
 *
 * @author Luo Zhijie
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
    }

    /**
     * Runs the duke bot.
     */
    public void run() {
        try {
            tasks = storage.convertFileToTaskList();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }
        ui.greet();
        boolean isExit = false;
        Parser parser = new Parser(" ");
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("dukeData/tasks.txt").run();
    }
}
