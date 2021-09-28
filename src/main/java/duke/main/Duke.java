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
    private String filePath;
    private String fileName;
    /**
     * Class constructor.
     *
     * @param fileName name of the storage file.
     * @param filePath directory of the storage file.
     */
    public Duke(String filePath, String fileName) {
        ui = new Ui();
        this.filePath = filePath;
        this.fileName = fileName;
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
     * Initializes the duke chat bot.
     *
     * @return welcome message to the user.
     */
    public String run() {
        assert filePath != null : "filepath cannot be empty";
        assert fileName != null : "filename cannot be empty";
        storage = new Storage(filePath, fileName);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println(ui.showLoadingError());
            tasks = new TaskList();
        }
        return ui.showWelcome();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
