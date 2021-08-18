import java.util.Scanner;

//automated testing script closes super fast; still have to manually run the final FC command in terminal
//managed to fix the issue with FC not being found however. Git push issues also fixed.

//most obvious uncaught exceptions are incorrect delete/done commands. fix in the future.
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
    }

    public static void main(String[] args) {
        new Duke("frosty.txt").run();
    }
}
