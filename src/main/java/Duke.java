import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> taskList = new ArrayList<>();

    private static void displayTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.println(i + "." + taskList.get(i - 1).formatTask());
        }
    }

    private static void doTask(String idx) {
        Task t = taskList.get(Integer.parseInt(idx) - 1);
        t.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("\t" + t.formatTask());
    }

    private static void addTask(String description) {
        taskList.add(new Task(description));
        System.out.println("added: " + description);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String[] params = sc.nextLine().split(" ", 2);
        String firstParam = params[0];

        while (!firstParam.equals("bye")) {
            if (firstParam.equals("list")) {
                displayTasks();
            } else if (firstParam.equals("done")) {
                doTask(params[1]);
            }  else {
               addTask(firstParam);
            }
            params = sc.nextLine().split(" ", 2);
            firstParam = params[0];
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}