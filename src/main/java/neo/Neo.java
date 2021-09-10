package neo;

/**
 *  This class is the entry point to all other classes,
 *  also includes GUI.
 *
 * @author Ryan Tian Jun.
 */
public class Neo {
    private Storage storage;
    private final Farewell farewell;

    /**
     * This constructor initializes the stored list (if any) and
     * loads the farewell to be used when the program ends.
     */
    public Neo() {
        this.farewell = new Farewell();
        loadStoredList();
    }


    /**
     * Loads list (if any) stored locally.
     */
    private void loadStoredList() {
        String filePath = "data/neo.txt";
        this.storage = new Storage(filePath);
    }

    /**
     * Retrieves response from Neo.
     *
     * @param input String input from the user GUI.
     * @return returns a string to be displayed on the GUI.
     */
    protected String getResponse(String input) {
        if (input.toLowerCase().equals("bye")) {
            TaskList.saveList();
            return farewell.printFarewell() + "\n" + " ";
        } else {
            Ui parseInput = new Ui(input);
            String inputResult = parseInput.getInputResult();
            assert !inputResult.equals("") : "the response from Neo should not be empty";
            return inputResult;
        }
    }
}
