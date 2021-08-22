import java.util.Scanner;

public class Duke {
    private static TaskList taskList;
    private static Storage storage;
    private static Scanner sc;
    private static Ui ui;

    public Duke() {
        taskList = new TaskList();
        storage = new Storage();
        sc = new Scanner(System.in);
        ui = new Ui(sc, taskList, storage);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        // Obtain data from save file if it exists
        storage.copyFromFileToList(taskList);

        // Gets the user input
        ui.getInput();

        // Exits
        exit();
    }

    public static void exit() {
        System.out.println("Output: Goodbye! See you again!");
        ui.printDoubleDivider();
        sc.close();
    }
}

