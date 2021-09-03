package duke;

import java.io.IOException;

import duke.command.Command;

/**
 * Encapsulates the Duke chatbot.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = storage.loadTasksFromFile();
        } catch (IOException e) {
            System.out.println(Ui.format(e.toString()));
            taskList = new TaskList();
        }
    }

    /**
     * Launches Duke.
     */
    void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.displayErrorMsg(e);
            }
        }
        //exit
        storage.writeTasksToFile(taskList.getTasks());
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
