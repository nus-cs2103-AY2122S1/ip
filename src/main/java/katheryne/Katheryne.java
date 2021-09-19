package katheryne;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import katheryne.command.Command;

@JsonTypeInfo(use = NAME, include = PROPERTY)

/**
 * Chat bot Katheryne, used for simple todo lists.
 */
public class Katheryne {

    final static String PATH_NAME = "tasks.json";
    Storage storage;
    Ui ui;
    TaskList lst;
    
    public Katheryne() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.lst = new TaskList();
        
        // load from existing file
        try {
            storage.loadTasks(lst, PATH_NAME);
        } catch (KatheryneException e) {
            ui.showErrorMessage(e);
        }
    }

    /**
     * Generates a response to user input so that it can be rendered via the GUI
     */
    protected String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.getResponse(lst, storage);
        } catch (UnknownCommandException e) {
            return e.getMessage();
        } catch (KatheryneException e) {
            return (e.getMessage());
        }
    }

    /**
     * Runs Katheryne via CLI. Is deprecated.
     *
     * @param args
     */
    public static void main(String[] args) {
        Katheryne k = new Katheryne();
        k.run();
    }

    /**
     * Runs Katheryne, for use with CLI, by continuously reading userInputs and responding.
     * Is deprecated.
     */
    public void run() {
        ui.greet(lst);

        while (ui.getIsRunning()) {
            try {
                String userInput = ui.readCommand();
                Command c = Parser.parse(userInput);
                c.execute(lst, ui, storage);
            } catch (UnknownCommandException e) {
                System.out.println(e.getMessage());
            } catch (KatheryneException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
