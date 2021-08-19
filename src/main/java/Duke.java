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
                    } else if (len == 1) {
                        System.out.println("Indicate a task number that you have completed ☻");
                    }
                    taskDone(x, tasks, count);
                } else if (cmd.equals("delete")) {
                    if (x.length == 1) {
                        System.out.println("Indicate a task number to delete ☻");
                    } else {
                        deleteTask(x, tasks, count);
                        count--;
                    }
                } else if (cmd.equals("todo")) {
                    if (x.length == 1) {
                        missingTaskName("todo");
                    } else {
                        addToDo(input, tasks, count);
                        count++;
                    }
                } else if (cmd.equals("deadline")) {
                    if (x.length == 1) {
                        missingTaskName("deadline");
                    } else {
                        addDeadline(input, tasks, count);
                        count++;
                    }
                } else if (cmd.equals("event")) {
                    if (x.length == 1) {
                        missingTaskName("event");
                    } else {
                        addEvent(input, tasks, count);
                        count++;
                    }
                } else {
                    // unknown command received
                    System.out.println("☹︎wut☁︎☻ unknown command");
                }
            }
        }
        in.close();
    }

    public static void listTasks(ArrayList<Task> tasks, int count) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            Task t = tasks.get(i);
            System.out.println((i + 1) + "." + t.toString());
        }
    }

    public static void taskDone(String[] inputArr, ArrayList<Task> tasks, int count) {
        int index = Integer.parseInt(inputArr[1]) - 1;
        if (index >= count) {
            System.out.println("No task of this number. Add new task or input a different number.");
        } else if (index < 0) {
            System.out.println("Input a task number from 1 - " + count);
        } else {
            Task t = tasks.get(index);
            t.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n  " + t.toString());
        }
    }

    public static void addToDo(String input, ArrayList<Task> tasks, int count) {
        String name = input.substring(input.indexOf(" ") + 1);
        ToDo t = new ToDo(name);
        tasks.add(t);
        System.out.println("Okay! Task added:\n  " + t.toString());
        System.out.println("You now have " + (count + 1) + " task(s) in the list.");
    }

    public static void addDeadline(String input, ArrayList<Task> tasks, int count) {
        String name = input.substring(input.indexOf(" ") + 1, input.lastIndexOf("/by") - 1);
        String by = input.substring(input.lastIndexOf("/by") + 4);
        Deadline d = new Deadline(name, by);
        tasks.add(d);
        System.out.println("Okay! Task added:\n  " + d.toString());
        System.out.println("You now have " + (count + 1) + " task(s) in the list.");
    }

    public static void addEvent(String input, ArrayList<Task> tasks, int count) {
        String name = input.substring(input.indexOf(" ") + 1, input.lastIndexOf("/at") - 1);
        String at = input.substring(input.lastIndexOf("/at") + 4);
        Event e = new Event(name, at);
        tasks.add(e);
        System.out.println("Okay! Task added:\n  " + e.toString());
        System.out.println("You now have " + (count + 1) + " task(s) in the list.");
    }

    public static void missingTaskName(String cmd) {
        String str = String.format("☹ OOPS!!! The description of a %s cannot be empty.", cmd);
        System.out.println(str);
    }

    public static void deleteTask(String[] inputArr, ArrayList<Task> tasks, int count) {
        int index = Integer.parseInt(inputArr[1]) - 1;
        if (index >= count) {
            System.out.println("No task of this number. Add new task or input a different number.");
        } else if (index < 0) {
            System.out.println("Input a task number from 1 - " + count);
        } else {
            Task t = tasks.get(index);
            tasks.remove(index);
            System.out.println("Ok! I've deleted this task:\n  " + t.toString());
            System.out.println("You now have " + (count - 1) + " task(s) in the list.");
        }
    }

}
