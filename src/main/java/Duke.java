import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String div = "____________________________________________________________\n";
        System.out.println(div + "Hello! I'm Bobby\nWhat can I do for you?\n" + div);
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int totalTasks = 0;
        while (true) {
            String userInput = sc.nextLine();
            String[] userInputList = userInput.split(" ");
            if (userInput.equals("bye")) {
                System.out.println(div + "Bye. Hope to see you again soon!\n" + div);
            } else if (userInput.equals("list")) {
                System.out.println(div + "Here are the tasks in your list:");
                for (int i = 0; i < totalTasks; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(div);
            } else if (userInputList[0].equals("done")) {
                Task taskCompleted = tasks[Integer.parseInt(userInputList[1])-1];
                taskCompleted.markAsDone();
                System.out.println(div + "Nice! I've marked this task as done:");
                System.out.println("  " + taskCompleted + "\n" + div);
            } else {
                // if not a specified command, add new task to list
                tasks[totalTasks] = new Task(userInput);
                totalTasks++;
                System.out.println(div + "added: " + userInput + "\n" + div);
            }
        }
    }


}
