import java.util.Scanner;

public class Duke {
    private static TaskList tasklist = new TaskList();
    private static Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
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
        ui.closing();
    }

    public static void main(String[] args) {
        new Duke("frosty.txt").run();
    }
}
