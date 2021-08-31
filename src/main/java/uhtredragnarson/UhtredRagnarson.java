package uhtredragnarson;

import java.io.IOException;
import java.util.Scanner;

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

    protected String getResponse(String userInput) {
        String result;
        try {
            result = Parser.parse(userInput, taskList, ui);
        } catch (UhtredRagnarsonException e) {
            result = e.getMessage();
        }
        return result;
    }

    /**
     * Runs the bot.
     */
    public void run() {
        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            try {
                Parser.parse(userInput, taskList, ui);
            } catch (UhtredRagnarsonException e) {
                e.printStackTrace();
            }
            userInput = scanner.nextLine();
        }
        try {
            storage.rewriteFile(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.showByeMessage();
    }
}
