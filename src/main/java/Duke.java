import java.util.Scanner;

//delete OOB index not handled.
//error handling is a mess
//ui handover to Ui class is still incomplete

public class Duke {
    private static TaskList tasklist;
    private static Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasklist = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.notifyLoadingError();
            tasklist = new TaskList();
        }
    }

    public void run() {
        ui.init();
        boolean isExit = false;
        while (!isExit) {
            try {
                String in = ui.readCommand();
                Command c = Parser.parse(in);
                c.execute(tasklist, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                e.printStackTrace();
            }
        }
        storage.save(tasklist);
        ui.closing();
    }

    public static void main(String[] args) {
        new Duke("frosty.txt").run();
    }
}
