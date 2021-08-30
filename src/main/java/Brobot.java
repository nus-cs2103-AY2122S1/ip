import brobot.parser.BroParser;
import brobot.UI;
import brobot.exception.BroException;

import brobot.task.TaskList;
import brobot.Storage;

/**
 * Represents the main Duke Program, a task manager.
 */
public class Brobot {
    public static void main(String[] args) {

            Storage storage = new Storage("./data/list1.txt");
            TaskList list = storage.readList();
            BroParser parser = new BroParser(list, storage);

            UI.printGreeting();
            String input = UI.getUserInput();

            while (!input.equals("bye")) {
                try {
                    parser.parse(input);
                } catch (BroException e) {
                    UI.printError(e);
                } finally {
                    input = UI.getUserInput();
                }
            }
            UI.printBye();
    }
}
