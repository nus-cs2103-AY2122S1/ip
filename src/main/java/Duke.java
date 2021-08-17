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
            } else {
                System.out.println("here");
                duke.add(command);
            }
        }

    }

    public void greeting() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void add(String description) {
        if (toDoList.size() < 100) {
            toDoList.add(new Task(description));
            System.out.println(String.format("added: %s", description));
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
