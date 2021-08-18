import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;

public class Duke {
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
    }

    private void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
