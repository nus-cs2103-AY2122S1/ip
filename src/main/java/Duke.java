import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String introMessage = "Hello! I'm Lawbringer!\n" +
                "What can i do for you?";
        System.out.println(introMessage);
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                int counter = 1;
                for (Task task : tasks) {
                    System.out.println(counter + ".[" + task.getStatusIcon() + "] " + task.title);
                    counter++;
                }
            } else if (userInput.startsWith("done")) {
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                tasks.get(index).maskAsDone();
                Task task = tasks.get(index);
                String doneMessage = "Nice! I've marked this task as done:\n" +
                        "  [" + task.getStatusIcon() + "] " + task.title;
                System.out.println(doneMessage);
            } else {
                tasks.add(new Task(userInput));
                System.out.println("added: " + userInput);
            }
        }
    }
}
