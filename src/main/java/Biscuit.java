import java.util.ArrayList;
import java.util.Scanner;

/**
 * Biscuit is a Personal Assistant Chatbot that helps a person to keep track of various things
 */
public class Biscuit {
    private static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        String logo =
                "████████████████████████████████████████\n"+
                "█▄─▄─▀█▄─▄█─▄▄▄▄█─▄▄▄─█▄─██─▄█▄─▄█─▄─▄─█\n" +
                "██─▄─▀██─██▄▄▄▄─█─███▀██─██─███─████─███\n" +
                "▀▄▄▄▄▀▀▄▄▄▀▄▄▄▄▄▀▄▄▄▄▄▀▀▄▄▄▄▀▀▄▄▄▀▀▄▄▄▀▀\n\n" +

                "      ████████████████████\n" +
                "    ██░░░░░░░░░░░░░░░░░░░░██\n" +
                "  ██░░░░██░░░░░░░░░░░░██░░░░██\n" +
                "  ██░░░░██░░██░░░░██░░██░░░░██\n" +
                "  ██░░████░░░░░░░░░░░░████░░██\n" +
                "    ██  ██░░░░████░░░░██  ██\n" +
                "        ██░░░░░██░░░░░██                  ██\n" +
                "          ██░░░░░░░░██░░████████        ██░░██\n" +
                "        ██░░████████░░░░░░░░░░░░██      ██░░██\n" +
                "      ██░░░░░░░░░░░░░░░░░░░░░░░░░░██  ██░░░░██\n" +
                "      ██░░░░░░░░░░░░░░██░░░░░░░░░░░░██░░░░██\n" +
                "      ██░░░░░░░░░░░░░░██░░░░░░░░░░░░██░░░░██\n" +
                "      ██░░░░░░██░░░░░░██░░░░░░░░░░░░░░░░██\n" +
                "      ██░░░░░░██░░░░░░██░░░░░░░░░░░░░░██\n" +
                "      ██░░░░░░██░░░░░░██░░░░░░░░░░████\n" +
                "████████████████████████████████████████\n";

        String separator = "────────────────────────────────────────";
        System.out.println("Woof from\n" + logo);
        System.out.println("Woof! I'm Biscuit.\nWhat can I do for you?\n" + separator);
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;
        while (isContinue) {
            String input = scanner.nextLine();
            String[] processedInput = input.trim().split("\\s+", 2);
            System.out.println(separator);
            switch (processedInput[0]) {
                case "todo":
                    addTask(new ToDo(processedInput[1]));
                    break;
                case "deadline":
                    String[] deadlineData = processedInput[1].split("/by ");
                    addTask(new Deadline(deadlineData[0], deadlineData[1]));
                    break;
                case "event":
                    String[] eventData = processedInput[1].split("/at ");
                    addTask(new Event(eventData[0], eventData[1]));
                    break;
                case "done":
                    Task current = list.get(Integer.parseInt(processedInput[1]) - 1);
                    current.setDone(true);
                    System.out.println("Nice! I've marked this task as done, woof!\n\t" + current);
                    break;
                case "list":
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i));
                    }
                    break;
                case "bye":
                    System.out.println("Bye! Hope to see you again soon! 8==8");
                    isContinue = false;
                    break;
                default:
                    System.out.println("໒(◉ᴥ◉)७ OOPS!!! I'm sorry, but I don't know what that means...");
                    break;
            }
            System.out.println(separator);
        }
    }

    public static void addTask(Task task) {
        list.add(task);
        System.out.println("Got it. I've added this task:\n\t" + task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }
}
