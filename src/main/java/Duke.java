import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
        try {
            Scanner scanner = new Scanner(System.in);
            boolean run = true;
            while (run) {
                String input = scanner.next();
                if (input.equals("bye")) {
                    input = "Bye. Hope to see you again soon!";
                    run = false;
                }
                printLine();
                System.out.println("    " + input);
                printLine();
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }
}
