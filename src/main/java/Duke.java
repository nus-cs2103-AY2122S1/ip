import java.util.Scanner;

public class Duke {
    private final static String EXIT_COMMAND = "bye";

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(new Reply("Hello! I'm Duke\nWhat can I do for you?"));
    }

    public static void main(String[] args) {
        greet();
        String readIn = new String();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            readIn = scanner.nextLine();
            if (readIn.equals(EXIT_COMMAND)) {
                break;
            }
            System.out.println(new Reply(readIn));
        }

        System.out.println(new Reply("Bye. Hope to see you again soon!"));
    }
}
