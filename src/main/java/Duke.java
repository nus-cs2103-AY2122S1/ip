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
            Task task = new Task(s);
            list.add(task);
            echo(s);
        }
        sc.close();
    }

    private static void echo(String s) {
        System.out.println("added: " + s);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void list(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task s = list.get(i);
            System.out.println(String.format("%d.[%s] %s", i + 1, s.getStatusIcon(), s));
        }
    }

    private static void done(ArrayList<Task> list, int num) {
        Task task = list.get(num - 1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" +
                            "[X] " + task.name);
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
}
