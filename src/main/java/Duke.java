import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        String horizontalLines = "---------------------------------";
        System.out.println(horizontalLines);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(horizontalLines);

        boolean end = false;
        int i = 0;
        Scanner sc = new Scanner(System.in);

        while (!end) {
            System.out.println(horizontalLines);
            System.out.print("Enter a text: ");

            String str = sc.nextLine();
            str = str.trim();
            try {
                // command "bye"
                if (str.equals("bye")) {
                    end = true;
                    System.out.println(horizontalLines);
                    System.out.println("Bye. Hope to see you again soon!");
                    sc.close();
                }

                //command done
                else if (str.contains("done")) {
                    System.out.println(horizontalLines);
                    System.out.println(markDone(str));
                }

                //command list
                else if (str.equals("list")) {
                    System.out.println(horizontalLines);
                    System.out.println(list(""));
                }

                //command to do
                else if (str.contains("todo")) {

                    System.out.println(horizontalLines);
                    System.out.println(todoTask(str));
                }

                //command deadline
                else if (str.contains("deadline")) {
                    System.out.println(horizontalLines);
                    System.out.println(deadlineTask(str));
                }

                else if (str.contains("event")) {
                    System.out.println(horizontalLines);
                    System.out.println(eventsTask(str));
                }

                else {
                    throw new DukeException("Command is not valid!");
                }
            } catch(DukeException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        }
        System.out.println(horizontalLines);

    }
    public static String list(String str) {
        for (int i = 0; i < tasks.size(); i++) {
            str += (i+1) + ". " + tasks.get(i);
            if (i != tasks.size() - 1) {
                str += "\n";
            }
        }
        return str;
    }

    public static String markDone(String str) {
        int a = Integer.parseInt(str.substring(5)) - 1;
        tasks.get(a).taskDone();
        return "Nice! I've marked this task as done: \n" + tasks.get(a);
    }

    public static String todoTask(String str) throws DukeException {
        try {
            str = str.substring(5);
            Task task = new Todo(str);
            tasks.add(task);
            return "Got it. I've added this task: \n"
                    + task
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (StringIndexOutOfBoundsException e) {
            return "☹ OOPS!!! The description of a todo cannot be empty.";
        }

    }

    public static String deadlineTask(String str) {
        try {
            int i = str.indexOf("/");
            Task t = new Deadline(str.substring(0, i), str.substring(i + 4));
            tasks.add(t);
            return "Got it. I've added this task: \n"
                    + t
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (StringIndexOutOfBoundsException e) {
            return "☹ OOPS!!! The description of a todo cannot be empty.";
        }
    }

    public static String eventsTask(String str) {
        try {
            int i = str.indexOf("/");
            Task t = new Events(str.substring(0, i), str.substring(i + 4));
            tasks.add(t);
            return "Got it. I've added this task: \n"
                    + t
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (StringIndexOutOfBoundsException e) {
            return "☹ OOPS!!! The description of a todo cannot be empty.";
        }
    }
}

