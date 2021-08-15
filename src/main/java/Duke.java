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

            switch (userInput) {
            case "bye":
                System.out.println(div + "Bye. Hope to see you again soon!\n" + div);
                break;
            case "list":
                System.out.println(div);
                for (int i = 0; i < totalTasks; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(div);
                break;
            default:
                tasks[totalTasks] = new Task(userInput);
                totalTasks++;
                System.out.println(div + "added: " + userInput + "\n" + div);
            }
        }
    }


}
