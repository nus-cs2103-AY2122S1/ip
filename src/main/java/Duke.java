import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String divider = "____________________________________________________________";



    private static class Task {
        private final String name;
        private boolean isDone;

        protected Task(String name) {
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



    public static class ToDo extends Task {
        private static final char symbol = 'T';

        public ToDo(String name) { super(name); }

        @Override
        public String toString() {
            return "[" + symbol + "]" + super.toString();
        }
    }



    public static class Deadline extends Task {
        private static final char symbol = 'D';

        private final String dueDate;

        public Deadline(String name, String dueDate) {
            super(name);
            this.dueDate = dueDate;
        }

        @Override
        public String toString() {
            return "[" + symbol + "]" + super.toString()
                    + " (by: " + dueDate + ")";
        }
    }



    public static class Event extends Task {
        private static final char symbol = 'E';

        private final String eventDate;

        public Event(String name, String eventDate) {
            super(name);
            this.eventDate = eventDate;
        }

        @Override
        public String toString() {
            return "[" + symbol + "]" + super.toString()
                    + " (at: " + eventDate + ")";
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

            String[] input = s.split("\\s+", 2);

            if (input.length == 1 && input[0].contentEquals("list")) {

                // list command
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }

            } else if (input[0].contentEquals("done")) {

                // done command
                if (input.length > 1) {
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
                    System.out.println("Must have an argument!");
                }

            } else {
                String command = input[0];

                Task added = null;

                switch (command) {
                    case "todo" : {
                        if (input.length <= 1) {
                            System.out.println("Must have an argument!");
                            break;
                        }

                        added = new ToDo(input[1]);
                        break;
                    }
                    case "deadline" : {
                        if (input.length <= 1) {
                            System.out.println("Must have an argument!");
                            break;
                        }

                        String[] tmp = input[1].split(" /by ", 2);
                        if (tmp.length <= 1) {
                            System.out.println("Must have a due date");
                            break;
                        }

                        added = new Deadline(tmp[0], tmp[1]);
                        break;
                    }
                    case "event" : {
                        if (input.length <= 1) {
                            System.out.println("Must have an argument!");
                            break;
                        }

                        String[] tmp = input[1].split(" /at ", 2);
                        if (tmp.length <= 1) {
                            System.out.println("Must have a date");
                            break;
                        }

                        added = new Event(tmp[0], tmp[1]);
                        break;
                    }
                }

                if (added != null) {
                    taskList.add(added);
                    System.out.println("added: " + added);
                } else {
                    System.out.println("Invalid input");
                }
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
