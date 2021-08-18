import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke :) \nWhat can I do for you?");
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.println();

        Task[] tasks = new Task[100];
        int count = 0;

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon! :)");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < count; i++) {
                    Task t = tasks[i];
                    System.out.println((i + 1) + "." + t.toString());
                }
            } else {
                String[] x = input.split(" ");
                String cmd = x[0];
                // marking tasks as done
                if (cmd.equals("done")) {
                    int len = x.length;
                    if (len > 2) {
                        System.out.println("Too many arguments for this command.");
                        continue;
                    }
                    int index = Integer.parseInt(x[1]) - 1;
                    if (index >= count) {
                        System.out.println("No task of this number. Add new task or input a different number.");
                        continue;
                    } else if (index < 0) {
                        System.out.println("Input a task number from 1 - " + count);
                        continue;
                    }
                    Task t = tasks[index];
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done: \n[X] " + t.name);
                } else if (cmd.equals("todo")) { // adding different types of tasks (else if statements??)
                    ToDo t = new ToDo(input);
                    tasks[count] = t;
                    System.out.println("Okay! Task added:\n  " + t.toString());
                    count++;
                    System.out.println("You now have " + count + " task(s) in the list.");
                } else if (cmd.equals("deadline")) {
                    String name = input.substring(input.indexOf(" ") + 1, input.lastIndexOf("/by") - 1);
                    String by = input.substring(input.lastIndexOf("/by") + 4);
                    Deadline d = new Deadline(name, by);
                    tasks[count] = d;
                    System.out.println("Okay! Task added:\n  " + d.toString());
                    count++;
                    System.out.println("You now have " + count + " task(s) in the list.");
                } else if (cmd.equals("event")) {
                    String name = input.substring(input.indexOf(" ") + 1, input.lastIndexOf("/at") - 1);
                    String at = input.substring(input.lastIndexOf("/at") + 4);
                    Event e = new Event(name, at);
                    tasks[count] = e;
                    System.out.println("Okay! Task added:\n  " + e.toString());
                    count++;
                    System.out.println("You now have " + count + " task(s) in the list.");
                } else {
                    // unknown command received
                    System.out.println("☹︎wut☁︎☻");
                }
            }
        }
        in.close();
    }
}
