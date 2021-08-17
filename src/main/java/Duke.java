import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents a personal assistant chatbot that responds to command line inputs.
 *
 * @author felix-ong
 */
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, I am Duke!(≧◡≦)\n" + "How may I help you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            String[] parts = input.split(" ");

            switch (parts[0]) {
            case "list":
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, tasks.get(i));
                }
                break;
            case "done":
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                Task doneTask = tasks.get(taskIndex);
                doneTask.markAsDone();
                System.out.printf("Good job! d(≧◡≦)b I have marked the following task as done:%n %s%n", doneTask);
                break;
            default:
                tasks.add(new Task(input));
                System.out.println("Added task: " + input);
                break;
            }
            input = sc.nextLine();
        }
        System.out.println("Bye! Feel free to ask me for help again anytime! (≧▽≦)/");
        sc.close();
    }
}
