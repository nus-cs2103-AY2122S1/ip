import java.util.Scanner;

public class Duke {
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static String divider = "____________________________________________________________";

    static void greeting() {
        System.out.println(logo);
        System.out.println(divider);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        System.out.println(divider);
    }

    static void close() {
        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println(divider);
    }

    public static void main(String[] args) {
        greeting();
        Scanner userInput = new Scanner(System.in);

        while (true) {

            String input = userInput.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println(divider);
                System.out.println(input);
                System.out.println(divider);
            }
        }

        close();
    }
}
