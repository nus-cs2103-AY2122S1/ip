import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! This is Jarvis.");
        System.out.println("What can I do for you sir?");
        System.out.println("---------------------------------");
        Task [] tasks = new Task[100];
        int taskIndex = 0;
        Scanner sc = new Scanner(System.in);
        while (true) {
            String taskDescription = sc.nextLine();
            if (taskDescription.equals("bye")) {
                System.out.println("---------------------------------");
                System.out.println("Goodbye Sir. Hope you have a pleasant day sir.");
                System.out.println("---------------------------------");
                break;
            } else if (taskDescription.equals("list")) {
                System.out.println("---------------------------------");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskIndex; i++) {
                    Task task = tasks[i];
                    System.out.printf("%d.[%s] %s%n", i + 1, task.getStatusIcon(), task.getDescription());
                }
                System.out.println("---------------------------------");
            } else if (taskDescription.startsWith("done ")) {
                String [] splitText = taskDescription.split(" ");
                int serialNum = Integer.parseInt(splitText[1]);
                Task task = tasks[serialNum - 1];
                task.markAsDone();
                System.out.println("---------------------------------");
                System.out.println("I have marked this task as done");
                System.out.printf("[%s] %s%n", task.getStatusIcon(), task.getDescription());
                System.out.println("---------------------------------");
            } else {
                tasks[taskIndex] = new Task(taskDescription);
                taskIndex++;
                System.out.println("---------------------------------");
                System.out.println("added: " + taskDescription);
                System.out.println("---------------------------------");
            }
        }
        sc.close();
    }
}
