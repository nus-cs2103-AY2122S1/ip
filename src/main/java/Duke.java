import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static void greet() {
        String message = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(message);
    }

    public static void echo(String input) {
        if (input != "bye") {
            System.out.println(input);
        } else {
            System.out.println("TEST");
            bye();
        }
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

        while (!(input = reader.readLine()).equals("bye")) {
            echo(input);
        }
        bye();
    }
}
