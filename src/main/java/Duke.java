import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Greet
        System.out.println("Hello! I'm Duke!\nWhat can I do for you?\n");

        ArrayList<Task> task = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                if (task.isEmpty()) {
                    System.out.println("There are no tasks!");
                } else {
                    for (int i = 1; i < task.size() + 1; i++) {
                        String str = String.format("%d.[%s] %s", i, task.get(i - 1).getStatusIcon(), task.get(i - 1).getDescription());
                        System.out.println(str);
                    }
                }
            } else if (input.substring(0, 4).equals("done")) {
                int taskNo = Integer.parseInt(input.substring(input.length() - 1));
                task.get(taskNo - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  [X] " + task.get(taskNo - 1).getDescription());
            } else{
                Task newTask = new Task(input);
                task.add(newTask);
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again!");

    }
}
