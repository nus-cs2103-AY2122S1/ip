import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private  static Scanner sc;
    private static ArrayList<Task> userTasks = new ArrayList<>();
    private static String bye_input = "bye";
    private static String bye_output = "Bye. Hope to see you again soon!";
    private static String list_input = "list";
    private static String done_input = "done";
    private static String indent = "    ";
    private static String div_line = "    ____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        greet();
        sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String next_input = sc.nextLine();
            String[] arr = next_input.split(" ");

            if (next_input.equals(bye_input)) {
                exit();
                break;
            } else if (next_input.equals(list_input)){
                printAllTasks();
            } else if (arr[0].equals(done_input)) {
                int i = Integer.parseInt(arr[1]) - 1;
                userTasks.get(i).markDone();
                echo("Nice! I have marked this task as done:\n" + indent + userTasks.get(i));
            } else {
                Task t = new Task(next_input);
                userTasks.add(t);
                echo("added : " + next_input);
            }
        }
    }

    private static void printAllTasks() {
        System.out.println(div_line);
        System.out.println(indent + "Here are the tasks in your list:");
        for (int i = 1; i < userTasks.size() + 1; i++) {
            System.out.println(indent + i + " " + userTasks.get(i-1));
        }
        System.out.println(div_line);
    }

    private static void exit() {
        echo(bye_output);
    }

    private static void echo(String next_input) {
        System.out.println(div_line + "\n" + indent + next_input + "\n" + div_line);
    }

    private static void greet() {
        String line1 = "Hello! I'm Duke\n";
        String line2 = indent + "What can I do for you?";
        echo(line1 + line2);
    }
}
