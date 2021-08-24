import duke.parser.DukeParser;
import duke.UI;
import duke.exception.DukeException;

import java.io.IOException;
import duke.task.TaskList;
import duke.Storage;

public class Duke {
    public static void main(String[] args) {
        try {
            Storage storage = new Storage("./data/list1.txt");
            TaskList list = storage.readList();
            DukeParser parser = new DukeParser(list, storage);

            UI.printGreeting();
            String input = UI.getUserInput();

            while (!input.equals("bye")) {
                try {
                    parser.parse(input);
                } catch (DukeException | IOException e) {
                    UI.printError(e);
                } finally {
                    input = UI.getUserInput();
                }
            }
        } catch (IOException e) {
            UI.printError(e);
        } finally {
            UI.printBye();
        }
    }
}
