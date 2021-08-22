package duke;

import duke.commands.Command;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String pathname, String dir) throws IOException {
        this.storage = new Storage(pathname, dir);
        this.tasks = new TaskList(storage.load());
        this.ui = new Ui(new Scanner(System.in));
    }

    public void run() {
        Parser parser = new Parser();
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String commandLine = ui.readCommand();
                Command c = parser.parse(commandLine, tasks);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println("***WARNING*** An error has occurred Master Wayne: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt", "data").run();
    }

}