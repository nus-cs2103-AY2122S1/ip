import brobot.UI;
import brobot.exception.BroException;
import brobot.parser.BroParser;
import brobot.storage.Storage;
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
                UI.printToTermFormatted(message);
            } catch (BroException e) {
                UI.printToTermFormatted(UI.getErrorText(e));
            } finally {
                input = UI.getUserInput();
            }
        }
        UI.printToTerm(UI.getByeText());
    }
}
