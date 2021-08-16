import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static List todoList;
    public static void greet() {
        String message = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(message);
    }

    public static void echo(String input) {
        System.out.println(input);
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args)
            throws IOException
    {
        greet();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String input;
        Duke.todoList = new List();

        while (!(input = reader.readLine()).equals("bye")) {
            Duke.todoList.addTask(input);
        }
        bye();
    }
}
