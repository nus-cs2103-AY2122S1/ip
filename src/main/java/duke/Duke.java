package duke;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printResponse(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printResponse("Hello! I'm duke.Duke", "What can I do for you?");
        Parser parser = new Parser();
        Scanner scan = new Scanner(System.in);
        boolean active = true;
        while (active) {
            String command = scan.nextLine();
            try {
                active = parser.parse(command, tasks, ui);
            } catch (DukeException e) {
                ui.printResponse(e.getMessage());
            }
            try {
                storage.rewriteData(tasks.convertToSaveFormat());
            } catch (DukeException e) {
                ui.printResponse(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("dukedata.txt").run();
    }
}
