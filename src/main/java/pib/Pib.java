package pib;

import pib.pibexception.PibException;
import pib.utility.Parser;
import pib.utility.Storage;
import pib.utility.TaskList;
import pib.utility.Ui;

/**
 * pib is a Personal Assistant Chat-bot that is able to keep track of tasks (CRUD) and deadlines
 */
public class Pib {

    /**
     * String to locate data file to load/save data
     */
    public static final String DATA_FILE_PATH = "data/data.txt";

    private Ui ui;
    private TaskList list;
    private Storage storage;
    private Parser parser;

    /**
     * Constructs an instance of pib
     */
    public Pib() {
        ui = new Ui();
        list = new TaskList();
    }

    /**
     * Loads the saved data from a file and if no saved data is found, creates the folder and the file to store data
     *
     * @return String response on whether any data was successfully loaded
     */
    public String loadData() {
        assert list != null;
        String response = "";
        try {
            storage = new Storage(DATA_FILE_PATH);
            response = storage.loadData(list);
        } catch (PibException e) {
            e.print();
        }
        parser = new Parser(list);
        assert ui != null;
        assert storage != null;
        return response;
    }

    private void start() {
        ui.printWelcome();
        parser.readInput();
    }

    /**
     * Gets the response depending on the user inputted command
     *
     * @param input user inputted command
     * @return String response to be displayed to the user
     */
    public String getResponse(String input) {
        return parser.readInput(input);
    }

    /**
     * Starts the whole Pib application
     *
     * @param args
     */
    public static void main(String[] args) {
        Pib p = new Pib();
        p.loadData();
        p.start();
    }
}
