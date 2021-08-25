package duke;

import duke.command.Command;

public class Duke {
    private FileManager filemanager;
    private Tasklist tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        filemanager = new FileManager(filePath);
        tasks = new Tasklist(filemanager.getTaskList());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, filemanager);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("taskList.txt").run();
    }
}