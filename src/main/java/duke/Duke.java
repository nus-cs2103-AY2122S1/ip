package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Represent the Duke Bot.
 * Entry point to the programme.
 */
public class Duke {
    private List todoList;

    /**
     * Greeting message from Duke.
     */
    public void greet() {
        String message = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(message);
    }

    /**
     * Message from Duke when the program ends.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Starts the Duke program.
     *
     * @param args instructions for Duke
     * @throws IOException If the File cannot be read/found.
     */
    public static void main(String[] args) throws IOException {
        Duke duke = new Duke();
        Parser parser = new Parser();
        try {
            File myObj = new File("filename.txt");
            Storage data = new Storage(myObj);
            if (myObj.createNewFile()) {
                System.out.println("New File created: " + myObj.getName());
                duke.todoList = new List();
            } else {
                System.out.println("Data exists");
                duke.todoList = new List(data.load());
            }
            duke.greet();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            String input;

            while (!(input = reader.readLine()).equals("bye")) {
                duke.todoList.addTask(input, parser, data);
            }
            duke.bye();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
