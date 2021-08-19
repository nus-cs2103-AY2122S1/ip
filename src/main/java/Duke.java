import java.util.Scanner;

/**
 * Allows for the main initialization of the Duke Program
 *
 * @author: Wei Yangken
 */
public class Duke {

    public static String breakline = "____________________________________________________________";

    /**
     * Provides the initialization message for the Duke Program
     */
    public static void start() {
        System.out.println(breakline);
        String greetingMsg = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetingMsg);
        System.out.println(breakline);
    }

    /**
     * Provides the exit message for the Duke Program
     */
    public static void exit() {
        String byeMsg = "Bye. Hope to see you again soon!";
        System.out.println(byeMsg);
        System.out.println(breakline);
    }

    public static void main(String[] args) {
        String cmd;
        Scanner scanner = new Scanner(System.in);
        Tasklist tasklist = new Tasklist();

        start();
        while (true) {
            cmd = scanner.next();
            String input = scanner.nextLine();
            Task task;

            switch (cmd) {
                case "bye":
                    exit();
                    return;
                case "list":
                    tasklist.list();
                    break;
                case "done":
                    int idx = Integer.parseInt(input.trim()) - 1;
                    tasklist.getTask(idx).setToCompleted();
                    break;
                case "todo":
                    task = Task.parseStringIntoTask(input, "", cmd);
                    tasklist.add(task);
                    break;
                case "deadline":
                    task = Task.parseStringIntoTask(input, "/by ", cmd);
                    tasklist.add(task);
                    break;
                case "event":
                    task = Task.parseStringIntoTask(input, "/at ", cmd);
                    tasklist.add(task);
                    break;
                case "delete":
                    int removedIdx = Integer.parseInt(input.trim());
                    tasklist.delete(removedIdx);
                    break;
                default:
                    try {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        System.out.println(breakline);
                    }
            }
        }
    }
}

