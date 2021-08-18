import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String WELCOME_MSG = "Hello! I'm Mr House";
    private static final String EXIT_MSG = "Goodbye Courier!";

    private static final ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println(formatString(WELCOME_MSG));

        Scanner sc  = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            action(input);
            input = sc.nextLine();
        }

        System.out.println(formatString(EXIT_MSG));
    }

    private static void action(String action) {
        if (action.equals("list")) {
            System.out.print(formatString(getTaskString()));
        } else {
            tasks.add(action);
            System.out.println(formatString("added: " + action));
        }
    }

    private static String getTaskString() {
        StringBuilder taskString = new StringBuilder();
        int len = tasks.size();

        taskString.append("1. " + tasks.get(0) + "\n");

        for (int i = 1; i < len - 1; i ++) {
            taskString.append(i + 1 + ". " + tasks.get(i) + "\n");
        }

        taskString.append(len + ". " + tasks.get(len - 1));

        return taskString.toString();
    }

    private static String formatString(String message) {
        return "\t____________________________\n" +
                "\t " + message.replace("\n", "\n\t ") + "\n" +
                "\t____________________________\n";
    }
}
