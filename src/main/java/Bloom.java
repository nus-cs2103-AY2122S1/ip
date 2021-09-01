import bloom.app.Storage;
import bloom.app.TaskList;
import bloom.app.Ui;

/**
 * Represents the task manager-bot Bloom.
 */

public class Bloom {

    /** The storage. */
    private Storage storage = new Storage();

    /** The list of tasks. */
    private TaskList tasks = new TaskList();

    /** The user interface. */
    private Ui ui = new Ui();

    public static void main(String[] args) {
        new Bloom().ui.start();
    }
}
