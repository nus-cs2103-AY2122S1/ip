package src.main.java.duke;

/**
 * Created by Shivam Tiwari
 * Chat bot to echo everything we input.
 * Features: Greetings, add tasks of type todo, event, deadline
 * delete tasks, mark as done, update time of tasks, view all tasks.
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
        this.ui = new UI(taskList, storage);
        this.parser = new Parser(ui);
    }

    /**
     * method to return the greeting message on bot start.
     *
     * @return greeting response of the bot.
     */
    String greet() {
        try {
            storage.readFile();
        } catch (DukeException error) {
            return "Format of date changed in database: 'YYYY-MM-dd'";
        }
        return ui.greet();
    }

    /**
     * method to get the response of the chat bot based on the String input.
     *
     * @param input String prompt from the user.
     * @return response of the chat bot.
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
