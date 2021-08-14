import java.util.Scanner;

public class Duke {

    static String logo = "      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";
    static String divider = "    ____________________________________________________________";
    static String space = "     ";

    public static void main(String[] args) {
        greet();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            displayText(input);
            input = scanner.nextLine();
        }

        displayText("Bye. Hope to see you again soon!");
    }

    public static void greet() {
        System.out.println(divider);
        System.out.println(logo);
        System.out.println(space + "Hello! I'm Duke\n" + space + "What can I do for you?");
        System.out.println(divider + "\n");
    }

    public static void displayText(String text) {
        System.out.println(divider);
        System.out.println(space + text);
        System.out.println(divider + "\n");
    }
}
