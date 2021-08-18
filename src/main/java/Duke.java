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
                continue;
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
                    continue;

                } catch (NumberFormatException e) {
                    System.out.println(formatMessage("Please enter a number after done"));
                    continue;
                }
            }

            addToList(new Task(input));
            System.out.println(formatMessage("added: " + input));
        }
        System.out.println(formatMessage("Bye. Hope to see you again soon!"));
    }
}
