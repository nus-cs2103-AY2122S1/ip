package duke;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;
import duke.Task.TaskList;

/**
 * Main class of the chatbot, provide start of the application
 */
public class Duke {
    /** Task list for an instance of duke*/
    private TaskList tasks = new TaskList();
    private Storage storage = new Storage();
    public Duke(){
        storage.loadDataToTasks(tasks);
    }
    /**
     * Start the application and the chatbot in the commandline.
     *
     */
    public void run() {

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

    public String getResponse(String userInput){
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        Parser.handleInput(userInput, tasks);

        String response = outputStreamCaptor.toString();
        System.setOut(standardOut);
        storage.saveTasksToStorage(tasks);
        return response;

    }
    public static void main(String[] args) {
        new Duke().run();
    }
}
