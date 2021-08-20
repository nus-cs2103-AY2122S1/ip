import java.io.FileNotFoundException;
import java.util.List;

/**
 * This is chatbot (Aisu) class.
 * You can:
 * 1) Type "todo (task)" - Add tasks without any date/time attached to it
 * 2) Type "list" - Show list
 * 3) Type "done (taskNumber)" - Mark task as done
 * 4) Type "deadline (task) /by (date)" - Add tasks that need to be done before a specific date/time
 * 5) Type "event (task) /at (date)" - Add tasks that start at a specific time and ends at a specific time
 * 6) Type "bye" - Exit program
 */
public class Aisu {
    private final Storage storage;
    private Tasklist tasklist;
    private final Ui ui;

    public Aisu(String dirPath, String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(dirPath, fileName);
        try {
            List<Task> cachedData = this.storage.load();
            this.tasklist = new Tasklist(cachedData);
        } catch (AisuException e) {
            this.tasklist = new Tasklist();
        }
    }

    public void run() {
        ui.showWelcomeMessage();

        boolean isExit = false;
        while (!isExit) { // maybe turn into a command class to check if isExit();
            try {
                Command command = Parser.parse(ui.getInput());
                ui.showDivider();
                command.execute(this.tasklist, this.storage, this.ui);
                isExit = command.isExit();
            } catch (AisuException e) {
                ui.showDivider();
                ui.showError(e.getMessage());
            } finally {
                ui.showDivider();
            }
        }
        ui.showGoodbyeMessage();
    }

    public static void main(String[] args) {
        new Aisu("data", "test1.txt").run();
    }
}
