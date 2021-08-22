import java.util.Scanner;

public class Ui {
    private static final Scanner scanner = new Scanner(System.in);
    public static void say(String message) {
        System.out.printf("Iris: %s%n", message);
    }

    public static void say(String message, boolean isFirst) {
        String name = isFirst ? "Iris:": "     ";
        System.out.printf("%s %s%n", name, message);
    }

    public static void sayError(IrisException exception) {
        say(exception.getMessage());
    }

    public static void sayTaskAdded() {
        int count = TaskList.getCount();
        Ui.say("Got it. I've added this task:");
        Ui.say(TaskList.get(count - 1).toString(), false);
        Ui.say(String.format("Now you have %d %s in the list.",
                count, count == 1 ? "task" : "tasks"), false);
    }

    public static void listTasks() {
        for (int i = 0; i < TaskList.getCount(); i++) {
            Ui.say(String.format("%d. %s", i + 1, TaskList.get(i)), i == 0);
        }
    }

    public static String prompt() {
        System.out.print("me: ");
        return scanner.nextLine();
    }
}
