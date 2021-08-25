package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private Ui ui;
//    private static Path saveFile = Paths.get("data", "duke.txt");

    public Duke(Path saveFile) {
        ui = new Ui();
        storage = new Storage(saveFile, ui);
        tasks = new TaskList(storage, ui);
        parser = new Parser(tasks, ui);
    }

    public void run() {
        ui.firstWelcome();
        boolean cont = true;
        while(cont) {
            String c = ui.readCommand();
            cont = parser.parse(c);
        }
    }

    public static void main(String[] args) {
        new Duke(Paths.get("data", "duke.txt")).run();
    }


}
