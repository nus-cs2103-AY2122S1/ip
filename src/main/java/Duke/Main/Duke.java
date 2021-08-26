package Duke.Main;

import Duke.DukeException.DukeException;

import java.io.File;

public class Duke {

    private final TaskList taskList;
    private final Ui ui;
    private final File file;

    /**
     * Constructor for Duke - initializing task list from
     * preprocessed file and initializing UI component
     */
    public Duke(String fileName) {
        this.taskList = new TaskList();
        this.file = new File(fileName);
        Storage.loadData(file, taskList);
        this.ui = new Ui();
    }

    /**
     * Run Duke. UI is first started, then any command is processed
     * through the Parser class to retrieve a reply, with task list
     * and storage being constantly updated after each cycle
     */
    public void run() {
        ui.start();
        String command = ui.readCommand();
        while (!Parser.isExit(command)) {
            try {
                ui.respondWith(Parser.parse(command, taskList).reply());
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
