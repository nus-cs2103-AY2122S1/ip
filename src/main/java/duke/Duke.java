package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Represent the Duke Bot.
 * Entry point to the programme.
 */
public class Duke {
    public List todoList;

    /**
     * Greeting message from Duke.
     */
    public static void greet() {
        String message = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(message);
    }

    /**
     * Message from Duke when the program ends.
     */
    public static void bye() {
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
        try {
            File myObj = new File("filename.txt");
            if (myObj.createNewFile()) {
                System.out.println("New File created: " + myObj.getName());
                duke.todoList = new List();
            } else {
                System.out.println("Data exists");
                Storage data = new Storage(myObj);
                duke.todoList = new List(data.load());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        greet();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String input;

        while (!(input = reader.readLine()).equals("bye")) {
            duke.todoList.addTask(input);
        }
        bye();
    }
}
