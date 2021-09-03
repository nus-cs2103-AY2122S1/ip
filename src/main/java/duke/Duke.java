package duke;

import java.util.Scanner;

import duke.commands.Command;

/**
 * Class that encapsulates the Duke application
 */
public class Duke {

    private PersistentStorage storage;
    private Tasklist taskList;
    private UI ui;

    /**
     * Public constructor for a Duke object
     *
     * @param filePath A filepath to a text file that serves
     *    as persistent storage for Duke.
     */
    public Duke(String filePath) {
        ui = new UI(new Scanner(System.in));
        storage = new PersistentStorage(filePath);

        try {
            taskList = storage.loadTasks();
        } catch (DukeException e) {
            ui.showLoadError();
            taskList = new Tasklist();
        }
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        ui.showStartMsg();
        Boolean isExit = false;

        while (!isExit) {
            try {
                String rawCommand = ui.readCommand();
                Command command = Parser.parse(rawCommand);
                command.executeCommand(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showErrorMsg(e);
            }
        }
    }

    /**
     * Main function to be run.
     *
     * @param args Command line arguments to main.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("./data/taskdata.txt");
        duke.run();
    }
}
