import java.util.Scanner; // import the Scanner class
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<Task>(100);
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        boolean end = false;
        Task task;
        while (!end) {
            String userInput = input.nextLine();
            // task = new Task(userInput);
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                end = true;
            } else if (userInput.equals("list")) {
                int count = 1;
                for (Task t : tasks) {
                    System.out.println(count + "." + t);
                    count += 1;
                }
            } else if (userInput.startsWith("done")) {
                String[] split = userInput.split(" ");
                int index = Integer.parseInt(split[1]);
                Task doneTask = tasks.get(index - 1);
                doneTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(doneTask);
            }
            else if (userInput.startsWith("todo")) {
                String[] split = userInput.split("todo ");
                task = new Todo(split[1]);
                tasks.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (userInput.startsWith("deadline")) {
                String[] split = userInput.split("deadline ");
                String description = split[1].split(" /")[0];
                String time = split[1].split("/by ")[1];
                System.out.println(description);
                System.out.println(time);
                task = new Deadline(description, time);
                tasks.add(task);
                System.out.println(task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (userInput.startsWith("event")) {
                String[] split = userInput.split("event ");
                String description = split[1].split(" /")[0];
                String time = split[1].split("/at ")[1];
                System.out.println(description);
                System.out.println(time);
                task = new Event(description, time);
                tasks.add(task);
                System.out.println(task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                System.out.println("Invalid input!");
            }
        }
    }
}
