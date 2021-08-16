import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "[X]" : "[ ]");
        }

        public void setDone() {
            this.isDone = true;
        }

        public String getDescription() {
            return this.description;
        }
    }

    public static void main(String[] args) {

        final String INTRO = "Hello! I'm Duke\n" +
                "What can I do for you?";

        final String OUTRO = "Bye. Hope to see you again soon!";
        Scanner sc = new Scanner(System.in);

        ArrayList<Task> lst = new ArrayList<>();
        String command;

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        System.out.println(INTRO);
        while (true) {
            command = sc.nextLine();

            if (command.equals("bye")) {
                System.out.println(OUTRO);
                break;

            } else if (command.equals("list")) {
                for (int i = 0; i < lst.size(); i++) {
                    Task currentTask = lst.get(i);
                    System.out.println((i + 1) + "." + currentTask.getStatusIcon() + " " + currentTask.getDescription());
                }

            }  else if (command.substring(0, 4).equals("done")) {
            int index = Integer.parseInt(command.substring(5)) - 1;
            String subtext = "Nice! I've marked this task as done:\n";

            Task currentTask = lst.get(index);
            currentTask.setDone();

            System.out.println(subtext + currentTask.getStatusIcon() + " " + currentTask.getDescription());

            } else {
                lst.add(new Task(command));
                System.out.println("added: " + command);
            }
        }
    }
}
