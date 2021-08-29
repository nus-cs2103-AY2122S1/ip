package duke;

import duke.commands.Command;

import java.util.Scanner;

/**
 * Class that contains Duke
 *
 */
public class Duke {

    /** List of tasks that is used in Duke */
    private TaskList taskList;

    /** The interactions used by Duke with the user */
    private Ui ui = new Ui();

    /** Storage for Duke to store data */
    private DukeStorage storage;

    /**
     * Constructor that initializes the tasklist, ui and storage of Duke
     *
     * @param path The destination of the stored data
     */
    public Duke(String path) {
        this.storage = new DukeStorage(path);
        try {
            this.taskList = this.storage.readTasks();
        } catch (DukeException e) {
            ui.loadErrorMessage();
            taskList = new TaskList();
        }
    }

    /**
     * Method that starts Duke
     *
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.startMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String strCommand = scanner.nextLine();
                ui.showLine();
                Command command = Parser.parse(strCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.errorMessage(e);
            }
        }
    }

    /**
     * Main method that starts Duke
     *
     * @param args Command line for arguments passed
     */
    public static void main(String[] args) {
        Duke duke = new Duke("tasklist.txt");
        duke.run();
    }
}