package duke;

import duke.command.Command;

import java.util.Scanner;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(), storage);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(storage);
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        while (!isExit) {
            String input = scanner.nextLine();
            try {
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
