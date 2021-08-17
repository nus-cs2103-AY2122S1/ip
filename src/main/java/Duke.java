import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private ArrayList<String> tasks;

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

    void run() {
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
            } else {
                this.addTask(input);
            }
            System.out.println("\t____________________________");
        }
    }

    void addTask(String task) {
        this.tasks.add(task);
        System.out.println("\tadded: " + task);
    }

    void list() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + this.tasks.get(i));
        }
    }

    void exit() {
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________");
    }
}
