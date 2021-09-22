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
    private TaskList tasks;
    private Storage storage;
    public Duke(){
        this.tasks = new TaskList();
        this.storage = new Storage();
        storage.loadDataToTasks(tasks);
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
}
