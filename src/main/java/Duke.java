import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.exception.DukeException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private final Ui ui;

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        //specify where the duke.txt file should be. In this case it is created in users' Desktop.
        Path path = Paths.get(home, "Desktop", "duke.txt");
        new Duke(path.toString()).run();
    }

    public Duke(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            this.taskList = new TaskList(this.storage.load());
        } catch (IOException exception) {
            ui.showLoadingError();
        } catch (DukeException exception) {
            ui.showError(exception.getMessage());
        }
    }

    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = this.ui.readCommand();
            ui.showTopLine();
            Command c = Parser.parse(fullCommand);
            c.execute(this.taskList, this.ui, this.storage);
            ui.showBottomLine();
            isExit = c.isExit();
        }
    }
}
