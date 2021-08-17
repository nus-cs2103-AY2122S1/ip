import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String OUTPUT_INDENT = "    ";
    public static String TEXT_INDENT = " ";
    public static String LINE = "____________________________________________________________";
    public static String LOGO = " ____        _        \n"
                              + "|  _ \\ _   _| | _____ \n"
                              + "| | | | | | | |/ / _ \\\n"
                              + "| |_| | |_| |   <  __/\n"
                              + "|____/ \\__,_|_|\\_\\___|\n";

    public static String[] welcomeMessages = {"Hello! I'm Duke", "What can I do for you?"};
    public static String[] goodbyeMessages = {"Bye. Hope to see you again soon!"};

    public static void main(String[] args) throws IOException {
        Duke.printMessages(welcomeMessages);

        while (true) {
            String input = Duke.getUserInput();
            if (input.equals("bye")) {
                Duke.printMessages(goodbyeMessages);
                break;
            }

            String[] defaultMessages = {input};
            Duke.printMessages(defaultMessages);
        }
    }

    /**
     * Prints an array of messages to the console.
     * @param messages The array of messages to be printed.
     */
    public static void printMessages(String[] messages) {
        System.out.println(Duke.OUTPUT_INDENT + Duke.LINE);
        for (String message : messages) {
            System.out.println(Duke.OUTPUT_INDENT + Duke.TEXT_INDENT + message);
        }
        System.out.println(Duke.OUTPUT_INDENT + Duke.LINE);
    }

    /**
     * Prompts the user for an input.
     * @return The input string
     * @throws IOException If an I/O error occurs
     */
    public static String getUserInput() throws IOException {
        System.out.println();
        return reader.readLine();
    }
}
