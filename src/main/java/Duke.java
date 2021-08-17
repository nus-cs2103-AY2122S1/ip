import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        start();
    }

    private static void start() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                exit();
                break;
            }
            if (s.equals("list")) {
                list(list);
                continue;
            }
            if (s.contains("done") && s.contains(" ") && s.length() > 4) {
                int num = getNum(s);
                if (num != -1) {
                    done(list, num);
                    continue;
                }
            }
            if (s.contains("todo")) {
                // add to list & getName :")
                    todo(s, list);
                    continue;
            }


            if (s.contains("deadline") && s.contains("/by")) {
               deadline(s, list);
               continue;
            }

            if (s.contains("event") && s.contains("/at")) {
                event(s, list);
            }
        }
        sc.close();
    }


//    private static void echo(String s) {
//        System.out.println("added: " + s);
//    }

    private static void todo(String s, ArrayList<Task> list) {
        String name = getName(s);
        if (!name.equals("")) {
            ToDo todo = new ToDo(name);
            list.add(todo);
            echo(todo, list.size());
        }
    }

    private static void deadline(String s, ArrayList<Task> list) {
        String[] parts = s.split("/by");
        if (parts.length == 2) {
            String name = getName(parts[0]);
            if (!name.equals("")) {
                String by = parts[1];
                Deadline deadline = new Deadline(name, by);
                list.add(deadline);
                echo(deadline, list.size());
            }
        }
    }

    private static void event(String s, ArrayList<Task> list) {
        String[] parts = s.split("/at");
        if (parts.length == 2) {
            String name = getName(parts[0]);
            if (!name.equals("")) {
                String at = parts[1];
                Event event = new Event(name, at);
                list.add(event);
                echo(event, list.size());
            }
        }
    }

    private static void echo(Task task, int size) {
        String t = size == 1 ? "task" : "tasks";
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d %s in the list.\n",
                task.toString(), size, t);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void list(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task s = list.get(i);
            System.out.printf("%d.%s%n", i + 1, s);
        }
    }

    private static void done(ArrayList<Task> list, int num) {
        Task task = list.get(num - 1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    private static int getNum(String s) {
        String[] parts = s.split(" ");
        if (parts[0].equals("done") && parts.length == 2) {
            try {
                int num = Integer.parseInt(parts[1]);
                return num > 0 ? num : -1;
            } catch (NumberFormatException nfe) {
                return -1;
            }
        }
        return -1;
    }

    private static String getName(String s) {
        String[] parts = s.split(" ", 2);
        if (parts[0].equals("todo") || parts[0].equals("deadline") || parts[0].equals("event")) {
            return parts[1];
        }
        return "";
    }
}
