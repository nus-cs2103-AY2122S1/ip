import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private final List<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task.resetMaxId();
        Duke duke = new Duke();

        duke.printInitialGreeting();
        String input = sc.nextLine();
        while(!input.equals("bye")) {
            duke.response(input);
            input = sc.nextLine();
        }
        duke.close();
    }

    public void response(String input) {
        String[] words = input.split(" ");
        String command = words[0];
        switch (command) {
            case "list":
                printList();
                break;
            case "done":
                int taskNumber = Integer.parseInt(words[1]);
                markAsDone(taskNumber);
                break;
            default:
                addToList(input);
                break;
        }
    }

    public void printInitialGreeting() {
        System.out.println("Hello I'm Duke\n" +
                "What can I do for you?");
    }

    public void close() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void printList() {
        System.out.println("Here are the tasks in your list:");
        for (Task t: list) {
            System.out.println(t);
        }
    }

    private void addToList(String input) {
        this.list.add(new Task(input));
        System.out.println("added: " + input);
    }

    private void markAsDone(int id) {
        Task currentTask = list.get(id - 1);
        currentTask.markAsCompleted();
        System.out.println("Nice! I've marked this task as done:\n "
                + currentTask.details());
    }

    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
