import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void run() {
        Storage storage = new Storage();
        storage.loadDataToTasks(tasks);
        String userInput = "";
        System.out.println("Hello, What Can I do for you ?\n -------------------------------");
        Scanner scanner = new Scanner(System.in);
        while(!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            Parser.handleInput(userInput, tasks);
        }
        storage.saveTasksToStorage(tasks);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.run();
    }
}
