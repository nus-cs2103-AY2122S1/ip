import java.util.Scanner;

public class Duke {
    private static final String sep =
            "-------------------------------------------------------";

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        printMsg("Hello! I'm Duke\n    Talk to me!");

        String input;

        while (true) {
            System.out.print(">>> ");
            input = s.nextLine();

            if (input.equals("bye")) {
                printMsg("Bye! Hope to see you again soon!");
                break;
            } else {
                printMsg("::: " + input);
            }
        }

        s.close();
    }

    private static void printMsg(String msg) {
        System.out.println(sep);
        System.out.println(msg);
        System.out.println(sep);
    }
}
