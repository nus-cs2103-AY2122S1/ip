package uhtredragnarson;

import java.io.IOException;

/**
 * UhtredRagnarson is a chat bot that help users to manage their to-dos, deadlines and events.
 */
public class UhtredRagnarson {

    private final Storage storage;
    private final Ui ui;
    private TaskList taskList;

    /**
     * The constructor for UhtredRagnarson.
     */
    public UhtredRagnarson() {
        String filePath = "src/main/java/data/UhtredRagnarson.txt";
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            taskList = new TaskList(storage.createTaskList());
        } catch (IOException e) {
            e.printStackTrace();
            taskList = new TaskList();
        }
    }

    protected String showWelcomeMessage() {
        return ui.showWelcomeMessage();
    }

    /**
     * Returns a message based on the user's input command.
     * @param userInput The command text that the user inputs.
     * @return The message to be printed to the user.
     */
    protected String getResponse(String userInput) {
        String result;
        try {
            result = Parser.parse(userInput, taskList, ui, storage);
        } catch (UhtredRagnarsonException | IOException e) {
            result = e.getMessage();
        }
        return result;
    }
}
