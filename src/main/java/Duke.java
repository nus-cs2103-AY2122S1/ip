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

        String[] commands = new String[100];
        int ctr = 0;
        commands[ctr] = sc.nextLine().toLowerCase();
        while (!commands[ctr].equals("bye")) {
            if (commands[ctr].equals("list")) {
                System.out.print(line);
                for (int i = 0; i < ctr; i++) {
                    System.out.printf("%d. %s\n", i + 1, commands[i]);
                }
                System.out.println(line);
            } else {
                System.out.printf("%sadded: %s\n%s\n", line, commands[ctr], line);
            }
            commands[++ctr] = sc.nextLine().toLowerCase();
        }

        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }
}
