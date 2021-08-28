package duke;

import duke.utils.Ui;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Parser;

import duke.exceptions.DukeException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    public static void main(String[] args) {
        Ui ui = new Ui();
        String home = System.getProperty("user.home");
        Path filePath = Paths.get(home, "tasks.txt");
        Storage storage = new Storage(filePath);
        TaskList taskList = new TaskList();
        ui.greetUser();
        try {
            taskList = storage.loadData();
            System.out.println("Welcome Back!\n");
            ui.printTasks(taskList);
        } catch (IOException e) {
            System.out.println("Your schedule is empty. What should I add to your schedule?");
        }

        while (true) {
            String input = ui.readInput();
            Parser parser = new Parser();
            if (input.equals("bye")) {
                ui.farewellToUser();
                break;
            } else {
                try {
                    parser.parseInput(taskList, input);
                    storage.saveData(taskList);
                } catch (DukeException | IOException e) {
                    ui.displayError(e);
                }

            }


        }
    }
}
