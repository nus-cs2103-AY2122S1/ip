import java.util.Scanner;

public class Duke {
    public static void printLine() {
        System.out.println("--------------------------------------------------");
    }

    public static void printBigIcon() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
    }

    public static void printHello() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printLine();
    }

    public static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void main(String[] args) {
        printLine();
        printBigIcon();
        printHello();
        printExit();
//        Scanner myScanner = new Scanner(System.in);
//
//        String userCommand = myScanner.nextLine();  // Read user input
//        System.out.println(userCommand);
    }
}
