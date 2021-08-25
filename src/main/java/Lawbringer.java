import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.IOException;

public class Lawbringer {

    private final Storage storage;
    private final Ui ui;
    private List<Task> tasks;

    public Lawbringer(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = storage.createTaskList();
        } catch (IOException e) {
            e.printStackTrace();
            tasks = new ArrayList<>();
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
                Parser.parse(userInput, tasks, ui);
            } catch (LawbringerException e) {
                e.printStackTrace();
            }
            userInput = scanner.nextLine();
        }
        try {
            storage.rewriteFile(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.showByeMessage();
    }
}
