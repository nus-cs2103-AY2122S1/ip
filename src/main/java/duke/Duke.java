package duke;

/**
 *  This class is the entry point to all other classes,
 *  also includes GUI.
 *
 * @author Ryan Tian Jun.
 */
public class Duke {
    private Storage storage;
    private Farewell farewell;

    public Duke() {
        this.farewell = new Farewell();
        loadStoredList();
    }


    /**
     * Loads list (if any) stored locally.
     */
    private void loadStoredList() {
        String filePath = "data/duke.txt";
        this.storage = new Storage(filePath);
    }

    /**
     * You should have your own function to generate a response to user input.
     */
    protected String getResponse(String input) {
        if (input.toLowerCase().equals("bye")) {
            TaskList.saveList();
            return farewell.printFarewell() + "\n" + " ";
        } else {
            Ui parseInput = new Feature(input);
            String inputResult = parseInput.getCommandResult();
            return inputResult;
        }
    }
}
