package duke;

import java.io.FileNotFoundException;

/**
 * Represents a chatbot that can be run with functionality of a to-do list keeper.
 * Each <code>Duke</code> object has a <code>Storage</code> to load and save a .txt list,
 * <code>TaskList</code> for storing the <code>Task</code>s, and a <code>Ui</code> for interacting
 * with user inputs.
 */
public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Returns a Duke object.
     */
    public Duke() {
        this.ui = new Ui(this);
        this.storage = new Storage("data/list.txt", this);
        this.tasks = new TaskList(this);
    }

    public TaskList getTasks() {
        return this.tasks;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public Ui getUi() {
        return this.ui;
    }

    protected String loadListGui() {
        try {
            return this.getStorage().loadFileToList();
        } catch (FileNotFoundException e) {
            return this.getUi().showLoadingError();
        }
    }

    protected String parseGui(String command, Duke currDuke) {
        Parser parser = new Parser(currDuke);
        String response = parser.handleInput(command);
        assert !response.equals("") : "there should be a response to the command";
        return response;
    }

    /**
     * Runs the duke chatbot by loading the TaskList and calling the Ui and Parser
     */
    private void run() {
        System.out.println(ui.showWelcomeMessage());
        System.out.println(Ui.LOGO);
        //noinspection finally
        try {
            System.out.println(storage.loadFileToList());
        } catch (FileNotFoundException e) {
            System.out.println(ui.showLoadingError());
        } finally {
            //noinspection InfiniteLoopStatement
            while (true) {
                String command = ui.getUserCommand();
                Parser parser = new Parser(this);
                String response = parser.handleInput(command);
                assert !response.equals("") : "there should be a response to the command";
                System.out.println(response);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
