import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private static String input = "";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();

        Scanner sc = new Scanner(System.in);
        while (!(input = sc.nextLine().toLowerCase()).equals("bye")) {
            echo(input);
        }
        exit();
    }

    public static void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void echo(String cmd) {
        System.out.println(cmd);
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
