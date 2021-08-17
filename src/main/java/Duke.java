import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    protected ArrayList<Task> toDoList;

    public Duke() {
        this.toDoList = new ArrayList<Task>(100);
    }

    public static void main(String[] args) {

        Duke duke = new Duke();
        duke.greeting();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine().trim();
            if (command.equals("bye")) {
                duke.bye();
                break;
            } else if (command.equals("list")) {
                duke.list();
            } else if (command.startsWith("done")) {
                duke.done(command.substring(5));
            } else if (command.startsWith("todo")) {
                duke.add(command.substring(5), "todo");
            } else if (command.startsWith("deadline")) {
                duke.add(command.substring(9), "deadline");
            } else if (command.startsWith("event")) {
                duke.add(command.substring(6), "event");
            } else {
                break;
            }
        }

    }

    public void greeting() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void add(String description, String taskType) {
        if (toDoList.size() < 100) {

            System.out.println("Got it. I've added this task:");

            if (taskType == "todo") {
                Task task = new Todo(description);
                toDoList.add(task);
                System.out.println(task);

            } else if (taskType == "deadline") {
                String newDescription = description.substring(0, description.indexOf(" /by "));
                String by = description.substring(description.indexOf("/by ") + 4);
                Task task = new Deadline(newDescription, by);
                toDoList.add(task);
                System.out.println(task);

            } else {
                String newDescription = description.substring(0, description.indexOf(" /at "));
                String at = description.substring(description.indexOf("/at ") + 4);
                Task task = new Event(newDescription, at);
                toDoList.add(task);
                System.out.println(task);
            }

            if (toDoList.size() > 1) {
                System.out.println(String.format("Now you have %d tasks in the list.", toDoList.size()));
            } else {
                System.out.println(String.format("Now you have 1 task in the list."));
            }
        }

    }

    public void list() {
        System.out.println("Here are the tasks in your list:");
        int num = 1;
        for (Task task: toDoList) {
            System.out.println(String.format("%d.%s", num, task));
            num++;
        }
    }

    public void done(String num) {
        int listNum = Integer.parseInt(num);
        if (listNum <= toDoList.size()) {
            toDoList.get(listNum - 1).setIsDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(toDoList.get(listNum - 1));
        }
    }
}
