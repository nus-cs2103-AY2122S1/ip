import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
                /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo); */

        String greetingMsg = "Hello! I'm Duke\nWhat can I do for you?";
        String breakline = "____________________________________________________________";
        String exitCmd = "bye";
        String cmd;
        String byeMsg = "Bye. Hope to see you again soon!";
        Scanner scanner = new Scanner(System.in);

        System.out.println(breakline);
        System.out.println(greetingMsg);
        System.out.println(breakline);

        do {
            cmd = scanner.nextLine();
            System.out.println(cmd);
            System.out.println(breakline);
        } while (!cmd.equals(exitCmd));

        System.out.println(byeMsg);
        System.out.println(breakline);
    }
}
