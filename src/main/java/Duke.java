import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = this.storage.load();

    }

    public void run() {
        Ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Ui.showLine();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, storage, ui);
            isExit = c.isExit();
            Ui.showLine();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}


