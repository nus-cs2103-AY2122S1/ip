import brobot.Storage;
import brobot.UI;
import brobot.exception.BroException;
import brobot.parser.BroParser;
import brobot.task.TaskList;


/**
 * Represents the main Brobot Program as a Cli for terminal.
 */
public class BrobotCli {

    /**
     * Main Brobot program.
     * @param args Arguments for main program
     */
    public static void main(String[] args) {

        Storage storage = new Storage("./data/list1.txt");
        TaskList list = storage.readList();
        BroParser parser = new BroParser(list, storage);

        UI.printToTerm(UI.getGreetingText());
        String input = UI.getUserInput();

        while (!input.equals("bye")) {
            try {
                String message = parser.parse(input);
                UI.printToTerm(message);
            } catch (BroException e) {
                UI.printToTerm(UI.getErrorText(e));
            } finally {
                input = UI.getUserInput();
            }
        }
        UI.printToTerm(UI.getByeText());
    }
}
