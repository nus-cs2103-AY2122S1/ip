import java.util.Scanner;

public class Duke {

    private String[] tasks = new String[100];

    private int count = 0;

    private void serve() {
        System.out.println("Good Day Sir/Mdm, I am Duke\nWhat can I do for you?\n");

        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("Goodbye Sir/Mdm. Hope to serve you again soon!\n");
                break;
            } else if (input.equals("list")) {
                this.list();
            } else {
                this.add(input);
                System.out.println("added: " + input + "\n");
            }

        }
    }

    private void add(String task) {
        tasks[this.count] = task;
        this.count++;
    }

    private void list() {
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) break;
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println();

    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.serve();
    }


}



