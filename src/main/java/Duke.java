import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printInitialGreeting();
        String input = sc.nextLine();
        while(!input.equals("bye")) {
            echo(input);
            input = sc.nextLine();
        }
        printExitMessage();
    }

    public static void echo(String input) {
        System.out.println(input);
    }

    public static void printInitialGreeting() {
        System.out.println("Hello I'm Duke\n" +
                "What can I do for you?");
    }

    public static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
