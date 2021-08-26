package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * This program is a chatbot that helps keep track of various tasks.
 *
 * @author Lethicia Renissa Santoso (G12)
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.showErrorMessage(e);
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * runs Duke.
     */
    public void run() {
        Ui.showWelcomeMessage();
        Scanner scan = new Scanner(System.in);
        while(scan.hasNextLine()) {
            try {
                String input = scan.nextLine();
                Command cmd = Parser.getCommand(input);
                cmd.execute(tasks);
                storage.write(tasks);
                if (cmd.getType().equals("exit")) {
                    break;
                }
            } catch (DukeException e) {
                Ui.showErrorMessage(e);
            }
        }
        scan.close();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
