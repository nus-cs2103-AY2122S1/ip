import java.util.Scanner;

public class Duke {
    private static int taskCount = 0;

    public static void main(String[] args) {
        int MAX = 100;
        Task[] tasks = new Task[MAX];
        System.out.println("Hello...\nWhat do you want?\n");
        Scanner myScanner = new Scanner(System.in);
        String answer = myScanner.nextLine();

        while (!answer.equals("bye")) {
            String[] parts = answer.split(" ");
            String taskType = parts[0];
            if (taskType.equals("done")) {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                tasks[taskIndex].markAsDone();
                System.out.println("I've marked this task as done: ");
                System.out.println("\t" + tasks[taskIndex]);
            } else if (taskType.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                tasks[taskCount] = new Task(answer);
                taskCount += 1;
                System.out.println("added: " + answer);
            }
            answer = myScanner.nextLine();
        }
        System.out.println("Whatever...");
    }
}
