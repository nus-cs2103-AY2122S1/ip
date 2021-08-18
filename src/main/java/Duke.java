import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static final TaskList list = new TaskList();

    private static String[] parseInput(String[] arr) {
        String desc = "";
        String time = "";
        boolean slash = false;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].charAt(0) == '/') {
                slash = true;
                continue;
            }
            if (slash) {
                time += arr[i] + " ";
            } else {
                desc += arr[i] + " ";
            }
        }
        if (slash) {
            return new String[] {desc, time};
        }
        return new String[] {desc};
    }

    private static boolean isAddingNewTask(String str) {
        return str.startsWith("todo") || str.startsWith("deadline") || str.startsWith("event");
    }

    private static void addTask(String command, String[] args) {
        switch (command) {
            case "todo":
                list.add(new Todo(args[0]));
                break;
            case "deadline":
                list.add(new Deadline(args[0], args[1]));
                break;
            case "event":
                list.add(new Event(args[0], args[1]));
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine().strip();

            String[] split = input.split(" ");

            if (input.equals("bye")) {
                //exit the program
                break;
            } else if (input.equals("list")) {
                //print list of tasks
                System.out.println(list.toString());
            } else if (input.startsWith("done")) {
                //mark task as done
                list.done(split[1]);
            } else if (isAddingNewTask(input)) {
                //user is adding a new task
                String command = split[0];
                String[] taskArgs = parseInput(split);

                addTask(command, taskArgs);
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :(");
            }
        }
        sc.close();
        System.out.println("Byebye");
    }
}
