package duke;

import java.util.ArrayList;

/**
 * Represents a chat bot assistant to keep track of tasks.
 * @author Nikki
 * @version 0.1
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Creates Duke Chat Bot instance.
     */
    public Duke() {
        storage = new Storage();
        ArrayList readList = storage.fileReader();
        ui = new Ui(readList.size());
        taskList = new TaskList(readList, ui);
        parser = new Parser(ui, taskList, storage);
    }

    /**
     * Starts chat bot when the main method is called.
     * @throws InputError If user inputs are invalid or unrecognised.
     */
    public void run() throws InputError {
        boolean byeMessage = false;
        ui.startMessage();

        while(!byeMessage) {
            String userInput = ui.startInput();
            int caseNum = parser.caseChecker(userInput);

            if (caseNum == 1) {
                byeMessage = true;
            } else {
                parser.caseHandler(caseNum, userInput, taskList);
            }
        }
        ui.byeMessage();
    }

    /**
     * Starts the program with this method.
     * @param args The command line arguments which is empty.
     * @throws InputError If user inputs are invalid or unrecognised.
     */
    public static void main(String[] args) throws InputError {
        new Duke().run();
    }
}
