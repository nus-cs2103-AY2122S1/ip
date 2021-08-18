import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws DukeException {
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
            String[] cmds = cmd.split(" ", 2);

            try {
                switch (cmds[0]) {
                    case "list":
                        System.out.println(list(""));
                        System.out.println("\t____________________________________________________________\n");
                        break;
                    case "bye":
                        System.out.println("\tBye. Hope to see you again soon!");
                        System.out.println("\t____________________________________________________________\n");
                        break;
                    case "done":
                        System.out.println(toMarkAsDone(cmd));
                        System.out.println("\t____________________________________________________________\n");
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        System.out.println(addTasks(cmds));
                        System.out.println("\t____________________________________________________________\n");
                        break;
                    default:
                        throw new DukeException("Invalid command given!");
                }
            } catch (DukeException e) {
                System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("\t____________________________________________________________\n");
            }

            if (cmd.equals("bye")) {
                scan.close();
                break;
            }
        }
    }

    public static String list(String toPrint) {
        for (int i = 0; i < tasks.size(); i++) {
            toPrint += "\t" + (i + 1) + "." + tasks.get(i);
            if (i != tasks.size() - 1)
                toPrint += "\n";
        }
        return toPrint;
    }

    public static String toMarkAsDone(String cmd) throws DukeException {
        int toMark = Integer.parseInt(cmd.substring(5)) - 1;
        tasks.get(toMark).markAsDone();
        return "\tNice! I've marked this task as done: \n\t\t" + tasks.get(toMark);
    }

    public static String addTasks(String[] cmds) throws DukeException {
        switch (cmds[0]) {
            case "todo":
                try {
                    Task t = new Todo(cmds[1]);
                    tasks.add(t);
                    return printTask(t);
                } catch (ArrayIndexOutOfBoundsException e){
                    return "\t☹ OOPS!!! The description of a todo cannot be empty.";
                }
            case "deadline":
                try {
                    int slashIndex = cmds[1].indexOf("/");
                    Task t = new Deadline(cmds[1].substring(0, slashIndex), cmds[1].substring(slashIndex + 4));
                    tasks.add(t);
                    return printTask(t);
                } catch (ArrayIndexOutOfBoundsException e){
                    return "\t☹ OOPS!!! The description of a deadline cannot be empty.";
                }
            case "event":
                try {
                    int slashIndex = cmds[1].indexOf("/");
                    Task t = new Event(cmds[1].substring(0, slashIndex), cmds[1].substring(slashIndex + 4));
                    tasks.add(t);
                    return printTask(t);
                } catch (ArrayIndexOutOfBoundsException e){
                    return "\t☹ OOPS!!! The description of an event cannot be empty.";
                }
        }
        return "";
    }

    public static String printTask(Task task) {
        return "\tGot it. I've added this task: \n\t\t" +
                task +
                "\n\tNow you have " + tasks.size() + " tasks in the list.";
    }
}
