import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private ArrayList<Task> tasks;

    Duke() {
        this.tasks = new ArrayList<>();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from Duke\n" + logo);
        System.out.println("\t____________________________");
        System.out.println("\tHello!, I'm Duke\n\tWhat can I do for you?");
        System.out.println("\t____________________________");
        Duke duke = new Duke();
        duke.run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            System.out.println("\t____________________________");
            input = input.toLowerCase();
            if (input.equals("bye") || input.equals("exit")) {
                this.exit();
                break;
            } else if (input.equals("list")) {
                this.list();
            } else if (input.split(" ")[0].equals("done")) {
                this.markDone(input.split(" ")[1]);
            } else {
                this.addTask(input);
            }
            System.out.println("\t____________________________");
        }
    }

    private void addTask(String task) {
        this.tasks.add(new Task(task));
        System.out.println("\tadded: " + task);
    }

    private void list() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + this.tasks.get(i).toString());
        }
    }

    private void exit() {
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________");
    }

    private void markDone(String i) {
        try {
            int index = Integer.parseInt(i);
            this.tasks.get(index - 1).markAsDone();
            System.out.println("\tNice! I\'ve marked this task as done:");
            System.out.println(" \t" + this.tasks.get(index - 1).toString());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("\tPlease input the index of the task");
        }
    }
}
