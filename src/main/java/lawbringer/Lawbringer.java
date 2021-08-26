package lawbringer;

import java.util.Scanner;

import java.io.IOException;

public class Lawbringer {

    private final Storage storage;
    private final Ui ui;
    private TaskList taskList;

    public Lawbringer(String filePath) {
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
        new Lawbringer("src/main/java/data/Lawbringer.txt").run();
    }

    public void run() {
        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            try {
                Parser.parse(userInput, taskList, ui);
            } catch (LawbringerException e) {
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
