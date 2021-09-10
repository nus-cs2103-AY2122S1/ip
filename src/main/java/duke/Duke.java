package duke;

/**
 * Implementation for Duke.
 *
 * @author Wong Yun Rui Chris
 */
public class Duke {
    private final TaskList list;
    private final Storage storage;

    /**
     * Public constructor to initialise Duke.
     *
     */
    public Duke() {
        storage = new Storage("/data/duke.txt");
        list = storage.readData();
    }


    /**
     * Handles the saving of the TaskList's data by calling the saveData
     */
    public void saveDataToFile() {
        this.storage.saveData(this.list);
    }

    /**
     * Returns the User's string input after it was parsed by the parser
     *
     * @param input the String that is to be parsed
     * @return The Duke's String response of the user's input
     * @throws DukeException Exceptions specific to Duke's input
     */
    public String handleInput(String input) throws DukeException {
        assert !input.isEmpty() : "String is empty!!";
        return Parser.parseInput(this.list, input);
    }
}
