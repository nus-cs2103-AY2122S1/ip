import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<Task>();

    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++)
        {
            String done = list.get(i).getStatusIcon();
            System.out.println((i + 1) + ".[" + done + "] " + list.get(i).description);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Jacky\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList();
                input = sc.nextLine();
                continue;
            }
            if (input.contains("done")) {
                System.out.println("Nice! I've marked this task as done: ");
                int taskNo = Integer.parseInt(input.substring(5, 6));
                Task task = list.get(taskNo - 1);
                System.out.println("[X] " + task.description);
                task.markAsDone();
                input = sc.nextLine();
                continue;
            }
            Task t = new Task(input);
            list.add(t);
            System.out.println("added: " + input);
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
