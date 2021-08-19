import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<Task>();

    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++)
        {
            System.out.println((i + 1) + ". " + list.get(i).toString());
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
                task.markAsDone();
                System.out.println(task);
                input = sc.nextLine();
                continue;
            }
            if (input.contains("todo")) {
                ToDo toDo = new ToDo(input.substring(5));
                list.add(toDo);
                System.out.println("Got it. I've added this task:\n" + toDo.toString());
                int count = list.size();
                System.out.println("Now you have " + count + " tasks in the list.");
                input = sc.nextLine();
                continue;
            }
            if (input.contains("deadline")) {
                System.out.println("Got it. I've added this task:\n[T][ ]" + input);
                input = sc.nextLine();
                continue;
            }
            if (input.contains("event")) {
                System.out.println("Got it. I've added this task:\n[T][ ]" + input);
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
