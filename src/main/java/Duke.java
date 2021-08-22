import java.util.Scanner;

public class Duke {
    enum Command {
        LIST, DONE, TODO, DEADLINE, EVENT, DELETE
    }

    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    private final TaskList tasks;

    public Duke(String filePath) {
        TaskList tasks1;
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks1 = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks1 = new TaskList();
        }

        tasks = tasks1;
        parser = new Parser(tasks, ui);
        ui.sayHi();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (!(input = scanner.nextLine().trim()).equals("bye")) {
            try {
                parser.parse(input);
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }

        exit();
        scanner.close();
    }

    /**
     * Prints exit message upon exit.
     */
    private void exit() {
        try {
            storage.save(tasks);
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }
        ui.sayBye();
    }
}
