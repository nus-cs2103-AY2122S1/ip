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

        Task[] tasks = new Task[100];
        int ctr = 0;
        String command = sc.nextLine().toLowerCase();

        while (!command.equals("bye")) {
            if (command.contains("done")) {
                int idx = Integer.parseInt(command.split(" ")[1]) - 1;
                System.out.printf("%sNice! I've marked this task as done:\n[X] %s\n%s\n",line, tasks[idx].description, line);
                tasks[idx].updateStatus();
            } else if (command.equals("list")) {
                System.out.println(line+ "Here are the tasks in your list:");
                for (int i = 0; i < ctr; i++) {
                    System.out.printf("%d.[%s] %s\n", i + 1, tasks[i].getStatusIcon(), tasks[i].description);
                }
                System.out.println(line);
            } else {
                System.out.printf("%sadded: %s\n%s\n", line, command, line);
                tasks[ctr++] = new Task(command);
            }
            command = sc.nextLine().toLowerCase();
        }

        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }
}
