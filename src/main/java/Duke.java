import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author Chng Zi Hao
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = new TaskList(storage.retrieveData(), storage);
        } catch (DukeException | FileNotFoundException e) {
            ui.formatPrint(e.getMessage());
            taskList = new TaskList(new ArrayList<Task>(), storage);
        }
    }

    public void run() {
        ui.printWelcomeMessage();
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.printPrompt();
                String input = sc.nextLine();
                Command c = Parser.parse(input);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.formatPrint(e.getMessage());
            } finally {

            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
