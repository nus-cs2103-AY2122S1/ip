package Duke.Main;

import java.io.File;

public class Duke {

    private TaskList taskList;
    private Ui ui;
    private File file;
    public Duke() {
        this.taskList = new TaskList();
        this.file = new File("taskFile/taskList.txt");
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
