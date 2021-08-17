import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = "    ____    ____    ____    __  __   ____ \n" +
                "   / __ \\  / __ \\  / __ \\  / / / /  / __ \\\n" +
                "  / /_/ / / /_/ / / / / / / /_/ /  / /_/ /\n" +
                " / .___/  \\____/ /_/ /_/  \\__, /   \\____/ \n" +
                "/_/                      /____/           \n";

        Scanner scan = new Scanner(System.in);
        System.out.println(logo);
        System.out.println("Hello! I'm Ponyo.\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");

        while (true) {
            String cmd = scan.nextLine();
            System.out.println("\t____________________________________________________________\n");
            String toPrint = "";

            if (cmd.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    toPrint += "\t" + (i + 1) + "." + tasks.get(i);
                    if (i != tasks.size() - 1)
                        toPrint += "\n";
                }
            } else if (cmd.equals("blah")) {
                toPrint = "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            } else if (cmd.equals("bye")) {
                toPrint = "\tBye. Hope to see you again soon!";
            } else if (cmd.contains("done")) {
                int toMark = Integer.parseInt(cmd.substring(5)) - 1;
                tasks.get(toMark).markAsDone();
                toPrint = "\tNice! I've marked this task as done: \n\t\t" + tasks.get(toMark);
            } else if (cmd.contains("todo")) {
                Task t = new Todo(cmd.substring(5));
                tasks.add(t);
                toPrint = printTask(t);
            } else if (cmd.contains("deadline")) {
                int slashIndex = cmd.indexOf("/") + 4;
                Task t = new Deadline(cmd.substring(9, slashIndex), cmd.substring(slashIndex));
                tasks.add(t);
                toPrint = printTask(t);
            } else if (cmd.contains("event")) {
                int slashIndex = cmd.indexOf("/") + 4;
                Task t = new Event(cmd.substring(6, slashIndex), cmd.substring(slashIndex));
                tasks.add(t);
                toPrint = printTask(t);
            }

            System.out.println(toPrint);
            System.out.println("\t____________________________________________________________\n");
            if (cmd.equals("bye")) break;
        }
    }

    public static String printTask(Task task) {
        return "\tGot it. I've added this task: \n\t\t" +
                task +
                "\n\tNow you have " + tasks.size() + " tasks in the list.";
    }
}
