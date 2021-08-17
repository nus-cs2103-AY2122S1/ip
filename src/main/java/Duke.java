import java.util.*;

public class Duke {
    private static final String SEPARATOR = "__________________________________________\n";
    private static final String GREETING = "Hello! I'm Talky McTalkFace\n"
            + "What can I do for you?\n";
    private static final String EXIT = "Bye. Hope to see you again soon!\n";
    private static final String PROMPT = "What would you like to do?";

    public static void main(String[] args) {
        String ExitCommand = "bye";
        Scanner sc = new Scanner(System.in);
        String input = "";

        System.out.print(SEPARATOR + GREETING + SEPARATOR);

        while (!input.equals(ExitCommand)) {
            System.out.println(PROMPT);
            input = sc.nextLine();
            if (input.equals(ExitCommand)) {
                System.out.print(SEPARATOR + EXIT + SEPARATOR);
            } else {
                System.out.print(SEPARATOR + input + "\n" + SEPARATOR);
            }
        }

    }
}
