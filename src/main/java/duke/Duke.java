package duke;

import duke.command.Command;

import java.util.ArrayList;

public class Duke {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.getFile());
        } catch (DukeException e) {
            ui.printMessage(e.getMessage());
            taskList = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }

}
