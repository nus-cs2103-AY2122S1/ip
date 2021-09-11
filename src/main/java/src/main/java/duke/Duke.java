package src.main.java.duke;

/**
 * Created by Shivam Tiwari
 * Chat bot to echo everything we input.
 * Features: Greetings, Echo and Exit on command
 */

public class Duke {

    private Parser parser;
    private UI ui;
    private Storage storage;
    private TaskList taskList;

    Duke() {
        super();
        this.taskList = new TaskList();
        this.storage = new Storage("./data", "duke.txt", taskList);
        storage.readFile();
        this.ui = new UI(taskList, storage);
        this.parser = new Parser(ui);
    }

    String greet() {
        return ui.greet();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        String response;
        try {
            response = this.parser.parse(input);
        } catch (DukeException err) {
            response = err.getMessage();
        }
        assert response != null : "Failed to generate response";
        return response;
    }

}
