import java.util.*;

public class Duke {
    static final String lineSeparator = "_____________________________________________________________";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(formatResponse("Hello! I'm Duke\nWhat can I do for you?"));
        String echoInput = sc.nextLine();
        while (!echoInput.equals("bye")) {
            System.out.println(formatResponse(echoInput));
            echoInput = sc.nextLine();
        }
        System.out.println(formatResponse("Bye. Hope to see you again soon!"));
    }

    private static String formatResponse(String response) {
        return String.format("%s\n    %s\n%s", lineSeparator, response, lineSeparator);
    }
}
