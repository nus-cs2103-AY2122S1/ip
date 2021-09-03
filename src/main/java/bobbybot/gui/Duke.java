package bobbybot.gui;

import bobbybot.util.Storage;
import bobbybot.util.TaskList;

import java.io.FileNotFoundException;


public class Duke {

    //private final Parser parser;

    /**
     * Initialise Duke object with filepath of storage
     * @param filePath storage .txt file path
     */
    public Duke(String filePath) {
        Storage database = new Storage(filePath);
        TaskList tasks = null;
        try {
            tasks = new TaskList(database.load());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //parser = new Parser(tasks, dataManager);
    }

    /**
     * Get response of BobbyBot from user input
     */
    String getResponse(String input) {
        //Command command = parser.parse(input);

        return "Duke heard: " + input;
    }



}