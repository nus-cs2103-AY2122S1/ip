import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Task[] tasks = new Task[100];
        int taskCount = 0;

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        // check userInput
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < 100; i++) {
                    Task task = tasks[i];
                    if (task == null) {
                        break;
                    } else {
                        System.out.println((i + 1) + ". [" + task.getStatusIcon() + "] " + task.description);
                    }
                }
            } else if (userInput.startsWith("done ")) {
                try {
                    int taskIndex = Integer.parseInt(userInput.split("done ")[1]) - 1;
                    tasks[taskIndex].setDone();
                    System.out.println("Nice! I've marked this task as done:\n" +
                            " [X] " + tasks[taskIndex].description);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid task number");
                }
            } else { // userInput is not "bye", not "list", and does not start with "done "
                if (taskCount >= 100) {
                    System.out.println("Error: Task list full");
                }
                tasks[taskCount] = new Task(userInput);
                System.out.println("added: " + userInput);
                taskCount++;
            }
            userInput = sc.nextLine(); // get new userInput
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

}
