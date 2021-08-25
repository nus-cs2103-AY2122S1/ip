import java.util.Scanner;

/**
 * This program Duke is a chatbot.
 *
 * @author: Toh Bing Cheng
 * @version: CS2103T AY21 Semester 1
 */
public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (InitialisationError e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }

    }

    public static void main(String[] args) {
        new Duke("data/dukeData.json").runProgram();
    }

    /**
     * This method runs the program indefinitely till user types in "bye".
     */
    public void runProgram() {
        ui.showWelcome();
        boolean run = true;

        while (run) {
            try {
                // wait to read in the user's input
                String input = ui.readCommand();
                Command userCommand = Parser.parse(input);
                run = userCommand.execute(taskList, ui, storage);
            } catch (IncompleteCommandException e) {
                run = true;
                ui.printErrorMessage(e.getMessage());
            } catch (Exception e) {
                run = false;
                e.printStackTrace();
            }
            if (run) {
                ui.showLoopWelcome();
            }

        }
    }





}
