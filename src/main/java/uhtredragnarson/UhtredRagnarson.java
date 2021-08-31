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
     *
     * @param filePath The file path of the .txt file to read from.
     */
    public UhtredRagnarson(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            taskList = new TaskList(storage.createTaskList());
        } catch (IOException e) {
            e.printStackTrace();
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new UhtredRagnarson("src/main/java/data/UhtredRagnarson.txt").run();
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
