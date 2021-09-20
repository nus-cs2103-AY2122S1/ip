package duke;
import java.util.Scanner;
import java.util.ArrayList;
import duke.Task.TaskList;


public class Duke {
    private static TaskList tasks = new TaskList();

    public static void run() {
        Storage storage = new Storage();
        storage.loadDataToTasks(tasks);
        String userInput = "";
        Ui.displayGreeting();
        Scanner scanner = new Scanner(System.in);
        userInput = Ui.takeInput();
        while(!userInput.equals("bye")) {
            Parser.handleInput(userInput, tasks);
            userInput = Ui.takeInput();

        }
        storage.saveTasksToStorage(tasks);
    }
    public static void main(String[] args) {
        Duke.run();
    }
}
