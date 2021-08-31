package duke;

import duke.commands.Command;
import duke.gui.Main;
import javafx.application.Application;

/**
 * Encapsulates the main Duke class which contains the main function to run the chat bot
 */
public class Duke {
    private final UI ui;
    private final Storage storage;
    private final DateTimeHandler dth;
    private final TaskList taskList;
    private final Parser parser;

    public Duke() {
        ui = new UI();
        taskList = new TaskList();
        storage = new Storage();
        dth = new DateTimeHandler();
        parser = new Parser();
        initialize();
    }

    /**
     * Loads the output file if it exists, and reads the tasks from it.
     */
    public void initialize() {
        try {
            storage.loadFile();
            storage.readFromFile(taskList);
        } catch (Exception e) {
            System.out.println("The file could not be created");
        }
    }

    /**
     * Returns a String to be displayed to the user after processing the given input as a command.
     *
     * @param input The given String input to be processed as a command.
     * @return The String to be displayed to the user.
     */
    public String getResponse(String input) {
        Command command = parser.parse(input);
        if (command == null) {
            return ui.unrecognisedCommand();
        }
        String returnString = command.execute(taskList, storage, ui, dth);
        try {
            storage.writeToFile(taskList);
        } catch (Exception e) {
            System.out.println("Write error");
        }
        if (command.isExit()) {
            System.exit(0);
        }
        return returnString;
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
