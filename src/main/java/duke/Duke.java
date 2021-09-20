package duke;
import java.util.Scanner;
import java.util.ArrayList;
import duke.Task.TaskList;

/**
 * Main class of the chatbot, provide start of the application
 */
public class Duke {
    /** Task list for an instance of duke*/
    private TaskList tasks = new TaskList();

    /**
     * Start the application and the chatbot
     *
     */
    public void run() {
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
        new Duke().run();
    }
}
