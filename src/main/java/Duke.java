import java.io.*;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printErrorMessage(e.getMessage());
            tasks = new TaskList();
        } catch (IOException e) {
            ui.printErrorMessage("File failed to load.");
        }
    }

    public void run() {
        try {
            Parser parser = new Parser(tasks, ui, storage);
            parser.readTask();
        } catch (IOException e) {
            ui.printErrorMessage("File fails to update");
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke(".\\src\\main\\level-7.txt");
        duke.run();
    }
}
