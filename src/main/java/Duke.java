import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "____________________________________________________________\n";
        System.out.println(line + "Hello I'm Duke\nWhat can I do for you?\n" + line);

        String command = sc.nextLine().toLowerCase();
        while (!command.equals("bye")) {
            System.out.println(line + command + "\n" + line);
            command = sc.nextLine().toLowerCase();
        }

        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }
}
