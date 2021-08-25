package Duke.Main;

import java.io.File;

public class Duke {

    private final TaskList taskList;
    private final Ui ui;
    private final File file;
    public Duke(String fileName) {
        this.taskList = new TaskList();
        this.file = new File(fileName);
        Storage.loadData(file, taskList);
        this.ui = new Ui();
    }
    public void run() {
        ui.start();
        String command = ui.readCommand();
        while (!Parser.isExit(command)) {
            try {
                ui.respondWith(Parser.parse(command, taskList));
            } catch (DukeException e) {
                ui.respondWith(e.getMessage());
            } finally {
                Storage.saveData(file, taskList);
                command = ui.readCommand();
            }
        }
        ui.exit();
    }
}
