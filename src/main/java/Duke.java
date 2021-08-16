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
                        System.out.printf("  %d.%s%n", i, task.get(i - 1));
                    }
                }
            } else if (input.substring(0, 4).equals("done")) {
                int taskNo = Integer.parseInt(input.substring(input.length() - 1));
                task.get(taskNo - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + task.get(taskNo - 1));
            } else{
                Task newTask = new Task("");
                if (input.substring(0, 4).equals("todo")) {
                    newTask = new Todo(input.substring(5));
                } else if (input.substring(0, 8).equals("deadline")) {
                    int index = input.indexOf("by");
                    newTask = new Deadline(input.substring(9, index - 2), input.substring(index + 3));
                } else if (input.substring(0, 5).equals("event")) {
                    int index = input.indexOf("at");
                    newTask = new Event(input.substring(6, index - 2), input.substring(index + 3));
                } else {
                    newTask = new Task(input);
                }
                task.add(newTask);
                System.out.println(String.format("Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.",
                        newTask, task.size()));
            }

            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again!");

    }
}
