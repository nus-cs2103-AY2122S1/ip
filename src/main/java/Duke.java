import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> tasks = new ArrayList<>();


    public static void main(String[] args) {
        System.out.println("Hello, I am Duke. What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        waitResponse(scanner);
        scanner.close();
    }

    private static void waitResponse(Scanner scanner) {
        if (scanner.hasNext()) {
            String command = scanner.next();
            String action = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                return;
            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(String.format("%d. %s", i + 1, tasks.get(i)));
                }
                waitResponse(scanner);
            } else if (command.equals("done")) {
                int taskNumber = Integer.parseInt(action.trim());
                if (taskNumber <= tasks.size()) {
                    Task taskToComplete = tasks.get(taskNumber - 1);
                    taskToComplete.complete();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskToComplete);
                } else {
                    System.out.println("This task does not exist! Use the list command to see your tasks.");
                }
                waitResponse(scanner);
            } else if (command.equals("todo")) {
                Task newTask = new Todo(action.trim());
                tasks.add(newTask);
                System.out.println(String.format(
                        "Got it. I've added this task: \n\t %s \nNow you have %d task in the list.",
                        newTask, tasks.size()));
                waitResponse(scanner);
            } else if (command.equals("deadline")) {
                String[] deadlineInputs = action.trim().split("/by", 2);
                Task newTask = new Deadline(deadlineInputs[0].trim(), deadlineInputs[1].trim());
                tasks.add(newTask);
                System.out.println(String.format(
                        "Got it. I've added this task: \n  %s \nNow you have %d task in the list.",
                        newTask, tasks.size()));
                waitResponse(scanner);
            } else if (command.equals("event")) {
                String[] eventInputs = action.trim().split("/at", 2);
                Task newTask = new Event(eventInputs[0], eventInputs[1]);
                tasks.add(newTask);
                System.out.println(String.format(
                        "Got it. I've added this task: \n\t %s \nNow you have %d task in the list.",
                        newTask, tasks.size()));
                waitResponse(scanner);
            } else {
                System.out.println("Sorry! What is this command?");
                waitResponse(scanner);
            }
        }
    }
}
