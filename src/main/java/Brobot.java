import brobot.Storage;
import brobot.UI;
import brobot.exception.BroException;
import brobot.parser.BroParser;
import brobot.task.TaskList;

/**
 * Represents the main Duke Program, a task manager.
 */
public class Brobot {
    /**
     * Main java program
     * @param args Arguments for main program
     */
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
