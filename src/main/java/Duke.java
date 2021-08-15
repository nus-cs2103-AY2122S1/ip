package main.java;
import java.util.Scanner;

/**
 * Duke is a Personal Assistant Chatbot that helps a person
 * keep track of various things.
 */
public class Duke {
    //Standard replies and horizontal line
    private static final String line = "\t____________________________________________________________\n";
    private static final String intro = "Hello! I'm Duke\n\t What can I do for you?";
    private static final String bye = "Bye. Hope to see you again soon!";

    /**
     * Provides horizontal lines with indentation.
     *
     * @param str
     * @return formatted reply
     */
    private static String reply(String str) {
        return line + "\t " + str + "\n" + line;
    }

    public static void main(String[] args) {
        System.out.println(reply(intro));
        Scanner sc = new Scanner(System.in);
        boolean on = true;
        while (on) {
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                    on = false;
                    System.out.println(reply(bye));
                    sc.close();
                    break;
                default: System.out.println(reply(input));
            }
        }
    }
}
