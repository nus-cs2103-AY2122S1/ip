import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String divider = "____________________________________________________________";

    public static class Task {
        private final String name;
        private boolean isDone;

        public Task(String name) {
            this.name = name;
            this.isDone = false;
        }

        private String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(this);
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.name;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println("Hello from\n" + logo +"\n");
        System.out.println("What can I do for you?");
        System.out.println(divider);

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();

        while (!s.contentEquals("bye")) {

            System.out.println(divider);

            String[] input = s.split("\\s+");

            if (input.length == 1 && input[0].contentEquals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
            } else if (input.length > 1 && input[0].contentEquals("done")){

                for (int i = 1; i < input.length; i++) {
                    try {
                        int listIndex = Integer.parseInt(input[i]);
                        if (listIndex <= 0 || listIndex > taskList.size()) {
                            System.out.println("Invalid Argument: " + listIndex + "; Index out of bounds!");
                        } else {
                            taskList.get(listIndex - 1).markAsDone();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(e + ": Argument must be an Integer!");
                    }
                }

            } else {
                taskList.add(new Task(s));
                System.out.println("added: " + s);
            }

            System.out.println(divider);
            s = in.nextLine();
        }

        in.close();

        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
}
