package pib;

import pib.pibexception.PibException;

/**
 * pib is a Personal Assistant Chat-bot that is able to keep track of tasks (CRUD) and deadlines
 */
public class Pib {
    public static final String DATA_FILE_PATH = "data/data.txt";

    private Ui ui;
    private TaskList list;
    private Storage storage;
    private Parser parser;

    /**
     * Public constructor to instantiate an instance of pib and start the program
     */
    public Pib() {
        this.ui = new Ui();
        this.list = new TaskList();
        this.storage = new Storage(DATA_FILE_PATH);
        try {
            storage.loadData(this.list);
        } catch (PibException e) {
            e.print();
        }
        this.parser = new Parser(this.list);
    }


    private void start() {
        ui.printWelcome();
        parser.readInput();
    }

    public static void main(String[] args) {
        Pib p = new Pib();
        p.start();
    }
}
