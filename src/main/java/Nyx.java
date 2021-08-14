import java.util.Scanner;

public class Nyx {
    static final String INDENTATION = "    ";
    static final String LINE = INDENTATION + "________________________________________________________________________";
    public static void main(String[] args) {
        String logo = "     __      _\n"
                + "    |   \\   | |__    __ __     __\n"
                + "    | |\\ \\  | |\\ \\  / / \\ \\   / /\n"
                + "    | | \\ \\ | | \\ \\/ /   \\ \\ / / \n"
                + "    | |  \\ \\| |  \\  /    /    /\n"
                + "    | |   \\   |  / /    / / \\ \\\n"
                + "    |_|    \\__| /_/    /_/   \\_\\\n";
        System.out.println(LINE);
        System.out.println(INDENTATION + "Hello! This is\n" + logo);
        System.out.println(INDENTATION + "What can I do for you?");
        System.out.println(LINE + "\n");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.next();
            System.out.println(LINE);
            if (!command.equals("bye")) {
                System.out.println(INDENTATION + command);
                System.out.println(LINE + "\n");
            } else {
                System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
                System.out.println(LINE + "\n");
                break;
            }
        }
    }
}