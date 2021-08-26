import java.text.spi.NumberFormatProvider;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private final static ArrayList<Task> list = new ArrayList<>();
    private final static Scanner scanner = new Scanner(System.in);
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    public Duke() {
        storage = new Storage("data/TaskList.txt");
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadTask());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            isExit = Parser.parse(fullCommand, ui, storage, tasks);
            ui.showLine();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
