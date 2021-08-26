package duke;

import duke.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String FILE_PATH = "data/dukeData.txt";

    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        ui.startBot();
        Parser p = new Parser(tasks, storage);
        p.parse();
        ui.endBot();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
