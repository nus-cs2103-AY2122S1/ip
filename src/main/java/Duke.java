import java.util.Scanner;

public class Duke {
    public static void printLine() {
        System.out.println("    ------------------------------------------");
    }

    public static void printMessage(String message) {
        printLine();
        System.out.println(String.format("    %s", message));
        printLine();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);
        printMessage("Hello! I'm Duke\n    What can I do for you?");
        String message = scanner.nextLine();
        while(!message.equals("bye")) {
            printMessage(message);
            message = scanner.nextLine();
        };
        printMessage("Bye. Hope to see you again soon!");
    }
}
