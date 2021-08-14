import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        chat("Hello I'm\n" + logo + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.next();
            if (input.equals("bye")) {
                chat(" Bye. Hope to see you again soon!");
                break;
            } else {
                chat(input);
            }
        }
    }

    private static void chat(String content) {
        System.out.println(
                "____________________________________________________________\n"
                + content
                + "\n____________________________________________________________\n"
        );
    }
}
