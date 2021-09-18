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
        Storage storage = new Storage();
        Ui ui = new Ui();
        TaskList lst = new TaskList();
        
        // load from existing file
        try {
            storage.loadTasks(lst, PATH_NAME);
        } catch (KatheryneException e) {
            ui.showErrorMessage(e);
        }
    }

//    /**
//     * Main method for running Katheryne via CLI.
//     *
//     * @param args
//     */
//    public static void main(String[] args) {
//        Katheryne k = new Katheryne();
//        k.run();
//    }
//    
//    public void run() {
//        ui.greet(lst);
//
//        while (ui.getIsRunning()) {
//            try {
//                String userInput = ui.readCommand();
//                Command c = Parser.parse(userInput);
//                c.execute(lst, ui, storage);
//            } catch (UnknownCommandException e) {
//                System.out.println(e.getMessage());
//            } catch (KatheryneException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        try {
            String userInput = ui.readCommand();
            Command c = Parser.parse(userInput);
//            c.execute(lst, ui, storage);
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
        } catch (KatheryneException e) {
            System.out.println(e.getMessage());
        }
        return "Katheryne heard: " + input;
    }
}
