import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke :)\nWhat can I do for you?");
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
                listTasks(tasks, count);
            } else {
                String[] x = input.split(" ");
                String cmd = x[0];
                if (cmd.equals("done")) {
                    int len = x.length;
                    if (len > 2) {
                        System.out.println("Too many arguments for this command.");
                        continue;
                    }
                    taskDone(x, tasks, count);
                } else if (cmd.equals("todo")) {
                    addToDo(input, tasks, count);
                    count++;
                } else if (cmd.equals("deadline")) {
                    addDeadline(input, tasks, count);
                    count++;
                } else if (cmd.equals("event")) {
                    addEvent(input, tasks, count);
                    count++;
                } else {
                    // unknown command received
                    System.out.println("☹︎wut☁︎☻");
                }
            }
        }
        in.close();
    }

    public static void listTasks(Task[] tasks, int count) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            Task t = tasks[i];
            System.out.println((i + 1) + "." + t.toString());
        }
    }

    public static void taskDone(String[] inputArray, Task[] tasks, int count) {
        int index = Integer.parseInt(inputArray[1]) - 1;
        if (index >= count) {
            System.out.println("No task of this number. Add new task or input a different number.");
        } else if (index < 0) {
            System.out.println("Input a task number from 1 - " + count);
        } else {
            Task t = tasks[index];
            t.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n[X] " + t.name);
        }
    }

    public static void addToDo(String input, Task[] tasks, int count) {
        String name = input.substring(input.indexOf(" ") + 1);
        ToDo t = new ToDo(name);
        tasks[count] = t;
        System.out.println("Okay! Task added:\n  " + t.toString());
        System.out.println("You now have " + (count + 1) + " task(s) in the list.");
    }

    public static void addDeadline(String input, Task[] tasks, int count) {
        String name = input.substring(input.indexOf(" ") + 1, input.lastIndexOf("/by") - 1);
        String by = input.substring(input.lastIndexOf("/by") + 4);
        Deadline d = new Deadline(name, by);
        tasks[count] = d;
        System.out.println("Okay! Task added:\n  " + d.toString());
        System.out.println("You now have " + (count + 1) + " task(s) in the list.");
    }

    public static void addEvent(String input, Task[] tasks, int count) {
        String name = input.substring(input.indexOf(" ") + 1, input.lastIndexOf("/at") - 1);
        String at = input.substring(input.lastIndexOf("/at") + 4);
        Event e = new Event(name, at);
        tasks[count] = e;
        System.out.println("Okay! Task added:\n  " + e.toString());
        System.out.println("You now have " + (count + 1) + " task(s) in the list.");
    }

}
