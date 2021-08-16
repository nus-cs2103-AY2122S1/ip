import java.util.*;

public class Duke {

    public static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void echo(String str) {
        System.out.println(str);
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        Duke.greet();

        Scanner sc = new Scanner(System.in);
        String str;
        while (!(str = sc.nextLine()).equals("bye")) {
            Duke.echo(str);
        }

        Duke.exit();

    }
}
