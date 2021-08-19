import java.util.InputMismatchException;
import java.util.MissingFormatArgumentException;
import java.util.MissingResourceException;
import java.util.Scanner;

public class Ligma {

    private static final String PARTITION = "______________________";
    private enum TaskType {
        TODO, EVENT, DEADLINE
    }
    private static Task[] tasks;
    private static int noOfTasks;

    private static boolean response(String command) {
        //returns true if bot should exit, i.e. command is bye
        try {
            if (command.equals("bye")) {
                System.out.println(PARTITION
                        + "\n Bye. I love Imagine Dragons."
                        + "\n\n\n Imagine Dragon Deez Nuts Cross Your Face.");
                System.out.print(PARTITION);
                return true;
            }
            if (command.equals("list")) {
                list();
            } else {
                int splitInd = command.indexOf(' ');
                String action = splitInd == -1
                        ? command
                        : command.substring(0, splitInd);
                String description = splitInd == -1
                        ? null
                        : command.substring(splitInd + 1);

                switch (action) {
                    case "todo":
                        addTask(TaskType.TODO, description);
                        break;
                    case "event":
                        addTask(TaskType.EVENT, description);
                        break;
                    case "deadline":
                        addTask(TaskType.DEADLINE, description);
                        break;
                    case "done":
                        markTaskDone(Character
                                .getNumericValue(command.charAt(splitInd + 1)) - 1);
                        break;
                    default:
                        throw new NoSuchMethodException();
                }
            }
        } catch (NoSuchMethodException e1) {
            System.out.println(PARTITION
                    + "\n Sorry, command does not exist.\n" + PARTITION);
        } catch (InputMismatchException e2) {
            System.out.println(PARTITION + '\n' + e2.getMessage() + '\n' + PARTITION);
        } finally {
            return false;
        }
    }

    private static void list() {
        System.out.println(PARTITION);
        for (int i = 0; i <= 100 && tasks[i] != null; i++) {
            System.out.println(String.format(" %d. ", i + 1) + tasks[i]);
        }
        System.out.println(PARTITION);
    }

    private static void addTask(TaskType t, String desc)
            throws InputMismatchException {
        if (desc == null) throw new InputMismatchException(
                String.format(" The description of %s cannot be empty.", t));
        try {
            Task task = t == TaskType.TODO
                    ? new Todo(desc)
                    : t == TaskType.DEADLINE
                    ? Deadline.createDeadline(desc)
                    : Event.createEvent(desc);

            tasks[noOfTasks] = task;
            noOfTasks++;

            System.out.println(PARTITION
                    + "\n Added: " + task
                    + String.format("\n You now have %d task(s).\n", noOfTasks)
                    + PARTITION);
        } catch (InputMismatchException e) {
            //this is due to absence of /at or /by in either deadline or event task creation
            String print = t == TaskType.DEADLINE
                            ? "for deadlines using '/by'"
                            : "for events using '/at'";
            System.out.println(PARTITION
                    + String.format("\n Time must be stipulated %s\n", print)
                    + PARTITION);
        }
    }

    private static void markTaskDone(int taskNo) {
        tasks[taskNo].markAsDone();
        System.out.println(PARTITION + "\n Successfully marked as done:\n "
                + tasks[taskNo] + "\n" + PARTITION);
    }

    public static void main(String[] args) {
        System.out.println(PARTITION
                + "\n Hello! I'm Ligma, Ligma Balls."
                + "\n What can I do for you?\n" + PARTITION);

        tasks = new Task[100];

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            if (response(sc.nextLine())) break;
        }
        sc.close();
    }
}
