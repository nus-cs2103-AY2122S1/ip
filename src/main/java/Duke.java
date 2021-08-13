import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                   + "|  _ \\ _   _| | _____ \n"
                   + "| | | | | | | |/ / _ \\\n"
                   + "| |_| | |_| |   <  __/\n"
                   + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        lineSeparator();
        System.out.println("I'm Duke");
        System.out.println("What can I do for you?");
        lineSeparator();
        while (true) {
            String userInput = sc.next();
            if (userInput.equals("bye")) {
                lineSeparator();
                System.out.println("Bye. Hope to see you soon!");
                lineSeparator();
                break;
            } else {
                lineSeparator();
                System.out.println(userInput);
                lineSeparator();
            }

        }
    }

    private static void lineSeparator() {
        System.out.println("____________________________________________________________");
    }
}
