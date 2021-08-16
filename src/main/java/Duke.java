import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static class Task {

        private final String name;

        private boolean done;

        public Task(String name) {
            this.name = name;
            done = false;
        }

        public String getName() {
            return name;
        }

        public void completeTask() {
            done = true;
        }

        public boolean getDone() {
            return done;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        final String lineBreak = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

        List<Task> tasks = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo + "What can I do for you?");
        System.out.println(lineBreak);

        while (true) {
            String input = sc.nextLine();
            System.out.println(lineBreak);

            // Exit
            if (input.equals("bye")) {
                System.out.println("Duke says: Bye. Hope to see you again soon!");
                System.out.println(lineBreak);
                break;

            // List out tasks
            } else if (input.equals("list")) {
                System.out.println("Duke says: Here is your list of tasks :)");
                if (tasks.size() == 0) {
                    System.out.println("Looks like you don't have any pending tasks! Must be nice (-_-;)");
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + "."
                                + (tasks.get(i).done ? "(Done)" : "(Not done)")
                                + " " + tasks.get(i).getName());
                    }
                }

            // Complete tasks
            } else if (input.split(" ")[0].equals("done")) {
                //TODO: add in error checking here if input is "done wpeno" or sth not int
                int taskIndex = Integer.parseInt(String.valueOf(input.charAt(5)));

                if (taskIndex > tasks.size()) {
                    System.out.println("Duke says: You don't have that many tasks!");
                } else {
                    tasks.get(taskIndex - 1).completeTask();
                    System.out.println("Duke says: You have completed the task " +
                            tasks.get(taskIndex - 1).getName() + ". Well done!");
                }

            // Add tasks
            } else {
                System.out.println("Duke says: Task '" + input + "' added");
                tasks.add(new Task(input));
            }

            //System.out.println("Duke says: " + input);
            System.out.println(lineBreak);

        }
    }
}
