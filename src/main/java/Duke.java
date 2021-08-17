import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    ArrayList<String> toDoList;

    public Duke() {
        this.toDoList = new ArrayList<String>(100);
    }

    public static void main(String[] args) {

        Duke duke = new Duke();
        duke.greeting();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                duke.bye();
                break;
            } else if (command.equals("list")) {
                duke.list();
            } else {
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

    public void add(String command) {
        if (toDoList.size() < 100) {
            toDoList.add(command);
            System.out.println(String.format("added: %s", command));
        }

    }

    public void list() {
        int num = 1;
        for (String task: toDoList) {
            System.out.println(String.format("%d. %s", num, task));
            num++;
        }
    }
}
