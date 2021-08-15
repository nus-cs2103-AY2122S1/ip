import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main class for the bot.
 */
public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        String greetings = "Hello from\n" + logo + "\nWhat can I do for you?";
        new Response(greetings).print();

        echoInput(new BufferedReader(new InputStreamReader(System.in)));
    }

    private static void echoInput(BufferedReader reader) {
        String input = "";
        try {
            input = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (input.equals("bye")) {
            terminate();
        } else {
            new Response(input).print();
            echoInput(reader);
        }
    }

    private static void terminate() {
        new Response("Bye. Hope to see you again soon!").print();
    }
}
