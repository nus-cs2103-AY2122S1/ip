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

        List<String> tasks = new ArrayList<>();

        while (true) {
            String cmd = scan.nextLine();
            System.out.println("\t____________________________________________________________\n");
            String toPrint = "";

            switch (cmd) {
                case "list":
                    for (int i = 0; i < tasks.size(); i++) {
                        toPrint += "\t" + (i + 1) + ". " + tasks.get(i);
                        if (i != tasks.size() - 1)
                            toPrint += "\n";
                    }
                    break;
                case "bye":
                    toPrint = "\tBye. Hope to see you again soon!";
                    break;
                case "blah":
                    toPrint = "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
                    break;
                default:
                    tasks.add(cmd);
                    toPrint = "\tadded: " + cmd;
                    break;
            }
            System.out.println(toPrint);
            System.out.println("\t____________________________________________________________\n");

            if (cmd.equals("bye")) break;
        }
    }
}
