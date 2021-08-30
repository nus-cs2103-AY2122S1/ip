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
     * Public constructor to instantiate an instance of pib
     */
    public Pib() {
        this.ui = new Ui();
        this.list = new TaskList();
    }

    public String loadData() {
        String response = "";
        try {
            storage = new Storage(DATA_FILE_PATH);
            response = storage.loadData(this.list);
        } catch (PibException e) {
            e.print();
        }
        this.parser = new Parser(this.list);
        return response;
    }

    private void start() {
        ui.printWelcome();
        parser.readInput();
    }

    public String getResponse(String input) {
        return parser.readInput(input);
    }

    public static void main(String[] args) {
        Pib p = new Pib();
        p.loadData();
        p.start();
    }
}
