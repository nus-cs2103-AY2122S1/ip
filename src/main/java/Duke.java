import java.io.IOException;

import java.time.format.DateTimeParseException;

import command.Command;
import exception.DukeException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = TaskList.deserialize(storage.load());
            ui.sayLoadingSuccess();
        } catch (DateTimeParseException | IllegalArgumentException | IOException e) {
            ui.sayLoadingFail();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.sayGreet();
        ui.sayHelp();

        String response = "";
        boolean isExit = false;

        while (!isExit) {
            try {
                response = ui.getUserCommand();
                Command c = Parser.parse(response);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                ui.sayError(e.getMessage());
            } catch (DukeException e) {
                ui.sayError(e.getMessage());
            }
        }

        ui.sayGoodBye();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");

        duke.run();
    }
}
