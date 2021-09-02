import java.io.FileNotFoundException;
import java.io.IOException;

import duke.Parser;
import duke.Storage;
import duke.TaskList;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    /**
     * Initialises Duke program.
     */
    public Duke() {
        this.taskList = new TaskList();
        this.storage = new Storage("data/tasks.txt", taskList);
        this.parser = new Parser(taskList);
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {

        if (input.matches("bye")) {
            try {
                storage.writeToFile(taskList.tasks);
                return "Bye! Hope to see you again! :D";
            } catch (IOException e) {
                System.out.println("No tasks saved.");
            }
        } else {
            String result = parser.parseCommand(input);
            return result;
        }

        if (input.matches("bye")) {
            return "Thanks for using this! :)";
        }
        return "Duke heard: " + input;
    }

    public String getTasks() {
        try {
            return storage.printFileContents(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return "You don't have any saved tasks";
    }
}
