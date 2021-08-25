import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);

        while (!isExit) {
            try {
                Command c = Parser.parse(scanner.nextLine().trim());
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
