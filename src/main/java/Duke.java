import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Main file for chatbot.
 *
 * @author marcuspeh
 * @version Level-8
 * @since 20 Aug 2021
 */

public class Duke {
    /** For the chatboard to read the user input. */
    private Scanner sc;
    /** Stores all the task. */
    private TaskList taskList;
    /** Get the ui to interact with the user */
    private Ui ui;
    /** Deaks with loading of task */
    private Storage storage;

    /**
     * Constructor for Duke.
     */
    Duke() {
        sc = new Scanner(System.in);
        ui = new Ui();
        storage = new Storage(ui);
        taskList = new TaskList(storage.importTask(), ui, storage);
    }

    /**
     * Start the chatbot and get it to chat with the user.
     */
    private void chat() {
        ui.greetMessage();
        String message;
        Keyword keyword;
        boolean isRunning = true;
        while (isRunning) {
            message = sc.nextLine().strip();
            try {
                keyword = Parser.parseChat(message);

                switch (keyword) {
                case EXIT:
                    isRunning = false;
                    break;
                case LIST:
                    ui.listTask(taskList.getTaskList());
                    break;
                case DONE:
                    taskList.markDone(message);
                    break;
                case DEADLINE:
                    taskList.addDeadline(message);
                    break;
                case EVENTS:
                    taskList.addEvent(message);
                    break;
                case TODOS:
                    taskList.addTodo(message);
                    break;
                case DELETE:
                    taskList.deleteTask(message);
                    break;
                }
            } catch (DukeException e) {
                ui.chatErrorMessage();
            }
        }
        ui.exitMessage();
    }

    /**
     * Main function to run the chatbot.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.chat();
    }
}
