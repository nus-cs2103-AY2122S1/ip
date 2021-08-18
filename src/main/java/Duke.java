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
    public static String taskAddedMessage(Task t) {
        return formatMessage(
                "Got it, I've added this task:\n        " + t +
                "\n     " + numOfTasks());
    }
    public static String numOfTasks() {
        return "Now you have " + list.size() + " task" + (list.size() != 1 ? "s" : "") + " in the list";
    }
    public static String printList() {
        StringBuilder res = new StringBuilder();
        for (int counter = 1; counter<=list.size(); counter++) {
            res.append(counter).append(". ").append(list.get(counter - 1));
            if (counter != list.size()) {
                res.append("\n     ");
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(formatMessage("Hello! I'm Duke\n" + "     What can I do for you?"));
        while (true) {
            String input = scanner.nextLine();
            String[] params = input.split(" ", 2);
            String[] parts;
            String arg;
            if(params[0].equals("bye")) {
                break;
            }
            switch (params[0]) {
                case "list":
                    System.out.println(formatMessage(printList()));
                    break;
                case "done":
                    if (params.length == 1) {
                        System.out.println(formatMessage("Please enter a number after done"));
                        break;
                    }
                    arg = params[1];
                    try {
                        int index = Integer.parseInt(arg);
                        if (index > list.size()) {
                            System.out.println(formatMessage("There are only " + list.size() + " tasks"));
                            break;
                        } else if (index == 0) {
                            System.out.println(formatMessage("There is no task 0"));
                            break;
                        }
                        Task t = list.get(index-1);
                        t.completeTask();
                        System.out.println(formatMessage(
                                "Nice! I've marked this task as done:\n       " + t + "\n     " +
                                    numOfTasks()
                        ));

                    } catch (NumberFormatException e) {
                        System.out.println(formatMessage("Please enter a number after done"));
                    }
                    break;
                case "delete":
                    if (params.length == 1) {
                        System.out.println(formatMessage("Please enter a number after delete"));
                        break;
                    }
                    arg = params[1];
                    try {
                        int index = Integer.parseInt(arg);
                        if (index > list.size()) {
                            System.out.println(formatMessage("There are only " + list.size() + " tasks"));
                            break;
                        } else if (index == 0) {
                            System.out.println(formatMessage("There is no task 0"));
                            break;
                        }
                        Task t = list.get(index-1);
                        list.remove(index-1);
                        System.out.println(formatMessage(
                                "Noted. I've removed this task:\n       " + t + "\n     " +
                                    numOfTasks()
                        ));

                    } catch (NumberFormatException e) {
                        System.out.println(formatMessage("Please enter a number after done"));
                    }
                    break;
                case "todo":
                    if (params.length == 1) {
                        System.out.println(formatMessage("Please enter the name of the task after todo"));
                        break;
                    }
                    Todo t = new Todo(params[1]);
                    addToList(t);
                    System.out.println(taskAddedMessage(t));
                    break;
                case "deadline":
                    if (params.length == 1) {
                        System.out.println(formatMessage("Please enter the name of the task after deadline"));
                        break;
                    }
                    if (!params[1].contains("/by")) {
                        System.out.println(formatMessage("Please enter the deadline of the task after /by"));
                        break;
                    }
                    parts = params[1].split(" /by ");
                    Deadline d = new Deadline(parts[0], parts[1]);
                    addToList(d);
                    System.out.println(taskAddedMessage(d));
                    break;
                case "event":
                    if (params.length == 1) {
                        System.out.println(formatMessage("Please enter the name of the task after event"));
                        break;
                    }
                    if (!params[1].contains("/at")) {
                        System.out.println(formatMessage("Please enter the start date of the task after /at"));
                        break;
                    }
                    parts = params[1].split(" /at ");
                    Event e = new Event(parts[0], parts[1]);
                    addToList(e);
                    System.out.println(taskAddedMessage(e));
                    break;
                default:
                    System.out.println(formatMessage("That is not a recognised command"));
            }
        }
        System.out.println(formatMessage("Bye. Hope to see you again soon!"));
    }
}
