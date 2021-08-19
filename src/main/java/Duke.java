import java.util.ArrayList;
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

        ArrayList<Task> tasks = new ArrayList<Task>();

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String input = in.nextLine().strip();
            String[] x = input.split(" ");
            String cmd = x[0];
            if (x.length == 1) {
                if ("bye".equals(cmd)) {
                    System.out.println("Bye. Hope to see you again soon! :)");
                    break;
                } else if ("list".equals(cmd)) {
                    Task.listAllTasks();
                } else if ("todo".equals(cmd) || "deadline".equals(cmd) || "event".equals(cmd)) {
                    missingTaskName(cmd);
                } else if ("done".equals(cmd) || "delete".equals(cmd)) {
                    System.out.println("Indicate a task number beside this command ☻");
                } else {
                    System.out.println("☹︎wut☁︎☻ unknown command");
                }
            } else {
                if (cmd.equals("done")) {
                    if (x.length > 2) {
                        System.out.println("Too many arguments for this command.");
                        continue;
                    }
                    Task.taskDone(getTaskNumber(x));
                } else if (cmd.equals("delete")) {
                    if (x.length > 2) {
                        System.out.println("Too many arguments for this command.");
                        continue;
                    }
                    Task.deleteTask(getTaskNumber(x));
                } else if (cmd.equals("todo")) {
                    addToDo(input);
                } else if (cmd.equals("deadline")) {
                    addDeadline(input);
                } else if (cmd.equals("event")) {
                    addEvent(input);
                } else {
                    // unknown command received
                    System.out.println("☹︎wut☁︎☻ unknown command");
                }
            }
        }
        in.close();
    }

    public static void addToDo(String input) {
        String name = input.substring(input.indexOf(" ") + 1);
        ToDo t = new ToDo(name);
        Task.addTask(t);
    }

    public static void addDeadline(String input) {
        String name = input.substring(input.indexOf(" ") + 1, input.lastIndexOf("/by") - 1);
        String by = input.substring(input.lastIndexOf("/by") + 4);
        Deadline d = new Deadline(name, by);
        Task.addTask(d);
    }

    public static void addEvent(String input) {
        String name = input.substring(input.indexOf(" ") + 1, input.lastIndexOf("/at") - 1);
        String at = input.substring(input.lastIndexOf("/at") + 4);
        Event e = new Event(name, at);
        Task.addTask(e);
    }

    public static void missingTaskName(String cmd) {
        String str = String.format("☹ OOPS!!! The description of a %s cannot be empty.", cmd);
        System.out.println(str);
    }

    public static int getTaskNumber(String[] inputArr) {
        return Integer.parseInt(inputArr[1]) - 1;
    }
}
