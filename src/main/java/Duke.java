import duke.parser.DukeParser;
import duke.UI;
import duke.exception.DukeException;

import duke.task.TaskList;
import duke.Storage;

/**
 * Represents the main Duke Program, a task manager.
 */
public class Duke {
    public static void main(String[] args) {

            Storage storage = new Storage("./data/list1.txt");
            TaskList list = storage.readList();
            DukeParser parser = new DukeParser(list, storage);

            UI.printGreeting();
            String input = UI.getUserInput();

            while (!input.equals("bye")) {
                try {
                    parser.parse(input);
                } catch (DukeException e) {
                    UI.printError(e);
                } finally {
                    input = UI.getUserInput();
                }
            }
            UI.printBye();
    }
}
