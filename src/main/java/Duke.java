import exceptions.DukeException;

import java.io.IOException;

public class Duke {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private String filePath = "src/main/java/data/duke.txt";
    private String folderPath = "src/main/java/data";

    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath, folderPath);
        taskList = new TaskList();
        try {
            storage.readTasks(taskList);
        } catch (DukeException | IOException ex) {
            ui.displayLoadingError(ex);
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.init();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.read();
                ui.displayLine();
                Command c = Parser.parse(fullCommand, taskList);
                c.execute(taskList, ui);
                storage.saveTasks(taskList);
                isExit = c.isExit();
            } catch (DukeException ex) {
                ui.displayError(ex.getMessage());
            } finally {
                ui.displayLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}