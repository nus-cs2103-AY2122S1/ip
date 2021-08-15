import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> taskList = new ArrayList<>();

        // Hello message
        System.out.println(
            "Lollipop: Hello! I am your personal chatbot, Lollipop! " +
            "What would you like to do today?");
        System.out.print("You: ");
        String command = scanner.nextLine();

        // Commands
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println("Lollipop: Here are your tasks");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
            } else {
                taskList.add(command);
                System.out.println("Lollipop: " + command + " has been added.");
            }

            System.out.print("You: ");
            command = scanner.nextLine();
        }

        // Goodbye message
        System.out.println("Lollipop: See you again soon!");
    }
}
