package duke;

/**
 *  This class is the entry point to all other classes,
 *  also includes GUI.
 *
 * @author Ryan Tian Jun.
 */
public class Duke {
    private Storage storage;
    private final Farewell farewell;

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
     * Retrieves response from Duke.
     *
     * @param input String input from the user GUI.
     * @return returns a string to be displayed on the GUI.
     */
    protected String getResponse(String input) {
        if (input.toLowerCase().equals("bye")) {
            TaskList.saveList();
            return farewell.printFarewell() + "\n" + " ";
        } else {
            Ui parseInput = new Feature(input);
            String parsedInputResult = parseInput.getInputResult();
            return parsedInputResult;
        }
    }
}
