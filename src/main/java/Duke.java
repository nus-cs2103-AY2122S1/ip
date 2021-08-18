import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String WELCOME_MSG = "Hello! I'm Mr House";
    private static final String EXIT_MSG = "Goodbye Courier!";
    private static final String TASK_MSG = "Here are your tasks:";

    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println(formatString(WELCOME_MSG));

        Scanner sc  = new Scanner(System.in);
        String input = sc.next();
        String description = sc.nextLine();

        while(!input.equals("bye")) {
            action(input, description);
            input = sc.next();
            description = sc.nextLine().trim();
        }

        System.out.println(formatString(EXIT_MSG));
    }

    private static void action(String action, String description) {
        if (action.equals("list")) {
            System.out.print(formatString(getTaskString()));
        } else if (action.equals("done")){
            Integer index = Integer.valueOf(description) - 1;
            System.out.println(formatString(tasks.get(index).markAsDone()));
        } else {
            Task newTask = new Task(action);
            tasks.add(newTask);
            System.out.println(formatString("added: " + action));
        }
    }

    private static String getTaskString() {
        StringBuilder taskString = new StringBuilder(TASK_MSG + "\n");
        int len = tasks.size();

        for (int i = 0; i < len - 1; i ++) {
            taskString.append(i + 1 + ". " + tasks.get(i).toString() + "\n");
        }

        taskString.append(len + ". " + tasks.get(len - 1).toString());

        return taskString.toString();
    }

    private static String formatString(String message) {
        return "\t____________________________\n" +
                "\t " + message.replace("\n", "\n\t ") + "\n" +
                "\t____________________________\n";
    }
}
