package duke;

import java.io.*;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingErrorMessage();
            tasks = new TaskList();
        }
    }


    public static void main(String[] args) {
        Duke duke = new Duke("data/tasks.txt");
        duke.run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showIntroMessage();
        while (!storage.isExit() && sc.hasNextLine()) {
            try {
                Parser p = new Parser(sc.nextLine(), ui, storage, tasks);
                p.parseCommand();
            } catch (DeleteException | DukeException | IOException | FindException e) {
                System.out.format(e.getMessage());
            }
        }
    }
}
