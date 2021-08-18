import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static List<Task> list = new ArrayList<>();
    public static String formatMessage(String s) {
        return "    ____________________________________________________________\n     " +
                s + "\n" +
                "    ____________________________________________________________";
    }
    public static void addToList(Task t) {
        list.add(t);
    }
    public static String printList() {
        String res = "";
        for (int counter = 1; counter<=list.size(); counter++) {
            res = res + counter + ". " + list.get(counter-1);
            if (counter != list.size()) {
                res = res + "\n     ";
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(formatMessage("Hello! I'm Duke\n" + "     What can I do for you?"));
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(formatMessage(printList()));
            } else if (input.startsWith("done")) {
                String arg = input.substring(5);
                try {
                    int index = Integer.parseInt(arg);
                    if (index > list.size()) {
                        System.out.println(formatMessage("There are only " + list.size() + " tasks"));
                        continue;
                    } else if (index == 0) {
                        System.out.println(formatMessage("There is no task 0"));
                        continue;
                    }
                    Task t = list.get(index-1);
                    t.completeTask();
                    System.out.println(formatMessage("Nice! I've marked this task as done\n       " + t));

                } catch (NumberFormatException e) {
                    System.out.println(formatMessage("Please enter a number after done"));
                }
            } else if (input.startsWith("todo")) {
                Todo t = new Todo(input.substring(5));
                addToList(t);
                System.out.println(formatMessage(
                        "Got it, I've added this task:\n        " + t +
                        "\n     Now you have " + list.size() + " task" + (list.size() != 1 ? "s" : "") +
                        " in the list"));
            } else {
                System.out.println(formatMessage("That is not a recognised command"));
            }
        }
        System.out.println(formatMessage("Bye. Hope to see you again soon!"));
    }
}
