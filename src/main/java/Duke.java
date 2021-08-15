import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private final ArrayList<String> taskList = new ArrayList<>();

    public void run() {

        greet();

        String input;
        Scanner sc = new Scanner(System.in);

        while(!(input = sc.nextLine()).equals("bye")) {
            if (input.equals("list")) {
                displayList();
            } else {
                addToList(input);
            }
        }

        sc.close();

        exit();
    }

    private void greet() {
        System.out.println("__________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("__________________________________");
    }

    private void exit() {
        System.out.println("__________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("__________________________________");
    }

    private void addToList(String task) {
        this.taskList.add(task);
        System.out.println("__________________________________");
        System.out.println("added: " + task);
        System.out.println("__________________________________");
    }

    private void displayList() {
        System.out.println("__________________________________");
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i));
        }
        System.out.println("__________________________________");
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
