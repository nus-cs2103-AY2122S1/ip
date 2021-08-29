package duke;

import duke.command.Command;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Parser parser = new Parser();
        UI ui = new UI(new Scanner(System.in));
        Storage storage = new Storage("./data/tasks.txt");
        TaskList tasks;

        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showException(e);
            tasks = new TaskList(new ArrayList<>());
        }

        ui.showWelcome();

        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.getUserInput();
                Command c = parser.parse(userInput);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showException(e);
            }
        }
    }
}
