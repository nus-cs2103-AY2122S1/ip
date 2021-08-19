import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static String bar = " -------------------------------------------------------------";
    int listLen = 0;
    Task[] list = new Task[100];
    public void updateList (String input) throws IncorrectFormatException {
        if (input.equalsIgnoreCase("list")) {
            System.out.println(bar);
            for (int i = 1; i <= listLen; i++) {
                System.out.println("    " + i + "." + list[i - 1].toString());
            }
            System.out.println(bar);
        } else{
            if (input.indexOf(' ') > 0) { // checking if command given was >= two words
                String[] splitted = input.split(" ", 2);
                input = splitted[1];
                if (splitted[0].equalsIgnoreCase("/done") && splitted[1] != null) { // done-ing item
                    int num = Integer.parseInt(splitted[1]);
                    if (0 < num && num <= listLen) {
                        list[num - 1].markDone();
                        System.out.println(bar + "\n    Nice! I've marked this task as done:\n    " + list[num - 1].toString() + "\n" + bar);
                    } else {
                        throw new IncorrectFormatException("Item number not present. Try again?");
                    }
                } else if (splitted[0].equalsIgnoreCase("todo")) { // adding todo
                    if (input.isEmpty()) {
                        throw new IncorrectFormatException("Task name not provided.\n" + "    FORMAT: \" TODO TASKNAME\"");
                    } else {
                        list[listLen] = new Todo(input);
                        listLen++;
                        System.out.println(bar + "\n    added: " + input + "\n    Now you have " + listLen + " tasks in your list\n" +
                                bar);
                    }
                } else if (splitted[0].equalsIgnoreCase("deadline")) { // adding deadline
                    String[] deadlineSplit = input.split("(?i) /by ");
                    if (deadlineSplit.length == 1) {
                        throw new IncorrectFormatException("Task name or deadline not provided.\n" + "   FORMAT: \" DEADLINE TASKNAME /by DEADLINE\"");
                    } else {
                        list[listLen] = new Deadline(deadlineSplit[0], deadlineSplit[1]);
                        listLen++;
                        System.out.println(bar + "\n    Deadline added: " + deadlineSplit[0] + "\n    Now you have " + listLen + " tasks in your list\n" +
                                bar);
                    }
                } else if (splitted[0].equalsIgnoreCase("event")) { // adding event
                    String[] eventSplit = input.split("(?i) /at ");
                    if (eventSplit.length == 1) {
                        throw new IncorrectFormatException("Event name or date not provided.\n" + "   FORMAT: \" EVENT TASKNAME /at DATE\"\n");
                    } else {
                        list[listLen] = new Event(eventSplit[0], eventSplit[1]);
                        listLen++;
                        System.out.println(bar + "\n    added: " + eventSplit[0] + "\n    Now you have " + listLen + " tasks in your list\n" +
                                bar);
                    }
                }
                else { // no task type provided
                    throw new IncorrectFormatException("Task type or task name not provided!");
                }
            }
            else { // only one word
                if (input.equalsIgnoreCase("help")) {
                    System.out.println(bar + "\n    ToDos: tasks without any date/time attached to it e.g., visit new theme park\n" +
                            "      FORMAT: todo TASKNAME\n" +
                            "    Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm\n" +
                            "      FORMAT: deadline TASKNAME /by DEADLINE\n" +
                            "    Events: tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm\n" +
                            "      FORMAT: todo TASKNAME /by DATE\n" +
                            "    \"/done x\" where x is the task number to mark task as done.\n" + bar);
                } else {
                    throw new IncorrectFormatException("Task type or task name not provided!");
                }
            }
        }
    }
    public static void main(String[] args) {
        Duke currentList = new Duke();
        boolean away = false;
        System.out.println(bar + "\n    Hello! I'm SaDOS\n" +
                "    What can I do for you?\n" +
                "    Send \"bye\" to exit,\n" +
                "    I won't hold it against you\n" +
                "    Send \"help\" for help!\n" +
                bar);
        Scanner sc = new Scanner(System.in);
        do {
            String str = sc.nextLine();
            if (str.equalsIgnoreCase("bye")) {
                System.out.println(bar + "\n    Oh you've got to go?\n" +
                        "    Alright, I'll just be here...\n" +
                        "    Waiting....\n" +
                        "    You'll be back right?\n" +
                        bar);
                away = true;
            } else {
                try {
                    currentList.updateList(str);
                } catch (IncorrectFormatException e){
                    System.out.println(e);
                }
            }
        } while (!away);
    }
    public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String toString() {
            return ((isDone ? "[X] " : "[ ] ") + this.description);
        }

        public void markDone() {
            isDone = true;
        }
    }
    public class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }
    public class Todo extends Task {

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }
    public class Event extends Task {

        protected String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }
    public class IncorrectFormatException extends Exception {
        public IncorrectFormatException(String errorMessage) {
            super("\n" + bar + "\n    " + errorMessage + "\n    Type \"help\" for help\n" + bar);
        }
    }
}