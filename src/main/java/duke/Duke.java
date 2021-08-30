package duke;

import duke.command.Command;

import java.io.IOException;

public class Duke {
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList taskList;

    public Duke(String pathName, String fileName) {
        ui = new Ui();
        parser = new Parser();

        try {
            storage = new Storage(pathName, fileName);
            taskList = storage.loadTasksFromFile();
        } catch (DukeException | IOException e) {
            ui.displayError(e.getMessage());
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data", "tasks.txt").run();
    }

    public void run() {
        ui.displayWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String commandLine = ui.readCommand();
                Command command = parser.parse(commandLine);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.displayError(e.getMessage());
            } finally {
                ui.displayLine();
            }
        }
    }
}
