import java.util.Scanner;

public class Duke {
    private static final String LINE = "____________________________________________________________\n";
    private static final String WELCOME = "Hello, I'm Duke\nWhat can I do for you?\n";
    private static final String BYE = "Bye. Hope to see you again soon!\n";

    /**
     * Echoes the user input by printing user input between two lines.
     * @param input String that user inputs.
     */
    public static void echo(String input) {
        System.out.println(LINE + input + "\n" + LINE);
    }

    public static void main(String[] args) {
        System.out.println(LINE + WELCOME + LINE);
        Scanner sc = new Scanner(System.in);
        while(true) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                System.out.println(LINE + BYE + LINE);
                break;
            } else {
                echo(s);
            }
        }
    }
}
