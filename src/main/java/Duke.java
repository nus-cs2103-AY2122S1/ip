import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
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

        List<Task> tasks = new ArrayList<>();

        while (true) {
            String cmd = scan.nextLine();
            System.out.println("\t____________________________________________________________\n");
            String toPrint = "";

            if (cmd.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    toPrint += "\t" + (i + 1) + ".[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).description;
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
                toPrint = "\tNice! I've marked this task as done: \n" +
                        "\t\t[" + tasks.get(toMark).getStatusIcon() + "] " + tasks.get(toMark).description;
            } else {
                Task t = new Task(cmd);
                tasks.add(t);
                toPrint = "\tadded: " + cmd;
            }

            System.out.println(toPrint);
            System.out.println("\t____________________________________________________________\n");

            if (cmd.equals("bye")) break;
        }
    }
}
