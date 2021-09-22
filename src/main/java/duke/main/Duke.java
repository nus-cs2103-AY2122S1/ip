package duke.main;

import duke.command.Command;
import duke.task.TaskList;

/**
 * Represents the duke chat bot, which has the ability to log and track and delete tasks.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class constructor.
     *
     * @param fileName name of the storage file.
     * @param filePath directory of the storage file.
     */
    public Duke(String filePath, String fileName) {
        ui = new Ui();
        assert fileName != null : "filepath cannot be empty";
        assert fileName != null : "filename cannot be empty";
        storage = new Storage(filePath, fileName);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke.Duke chat bot.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("data", "duke.txt").run();
    }

    /**
     * Executes the duke chat bot.
     */
    public void run() {
        ui.showWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine();
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//        }
    }
}
