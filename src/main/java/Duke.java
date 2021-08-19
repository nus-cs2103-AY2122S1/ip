import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean away = false;
        int listLen = 0;
        Task[] list = new Task[100];
        String bar = " -------------------------------------------------------------";
        System.out.println(bar + "\n    Hello! I'm SaDOS\n" +
                "    What can I do for you?\n" +
                "    Send \"bye\" to exit,\n" +
                "    I won't hold it against you\n" +
                "    (Mark tasks as done by sending \"/done x\", where x is item number)\n" +
                bar);
        Scanner sc = new Scanner(System.in);
        do {
            String str = sc.nextLine();
            if (str.equalsIgnoreCase("bye")) {
                away = true;
                System.out.println(bar + "\n    Oh you've got to go?\n" +
                        "    Alright, I'll just be here...\n" +
                        "    Waiting....\n" +
                        "    You'll be back right?\n" +
                        bar);
            } else if (str.equalsIgnoreCase("list")) {
                System.out.println(bar);
                for (int i = 1; i <= listLen; i++) {
                    System.out.println("    " + i + "." + list[i - 1].toString());
                }
                System.out.println(bar);
            } else{
                if (str.indexOf(' ') > 0) { // checking if command given was >= two words
                    String[] splitted = str.split(" ", 2);
                    str = splitted[1];
                    if (splitted[0].equalsIgnoreCase("/done") && splitted[1] != null) { // done-ing item
                        int num = Integer.parseInt(splitted[1]);
                        if (0 < num && num <= listLen) {
                            list[num - 1].markDone();
                            System.out.println(bar + "\n    Nice! I've marked this task as done:\n    " + list[num - 1].toString() + "\n" + bar);
                        } else {
                            System.out.println(bar + "\n    Item number not present. Try again?\n" + bar);
                        }
                    } else if (splitted[0].equalsIgnoreCase("todo")) { // adding todo
                        list[listLen] = new Todo(str);
                        listLen++;
                        System.out.println(bar + "\n    added: " + str + "\n    Now you have " + listLen + " tasks in your list\n" +
                                bar);
                    } else if (splitted[0].equalsIgnoreCase("deadline")) { // adding deadline
                        String[] deadlineSplit = str.split(" /by ");
                        if (deadlineSplit.length == 1) {
                            System.out.println(bar + "\n    Deadline not provide. FORMAT: \" TASK /by DEADLINE\"\n" + bar);
                        } else {
                            list[listLen] = new Deadline(deadlineSplit[0], deadlineSplit[1]);
                            listLen++;
                            System.out.println(bar + "\n    Deadline added: " + deadlineSplit[0] + "\n    Now you have " + listLen + " tasks in your list\n" +
                                    bar);
                        }
                    } else if (splitted[0].equalsIgnoreCase("event")) { // adding event
                        String[] eventSplit = str.split(" /at ");
                        if (eventSplit.length == 1) {
                            System.out.println(bar + "\n    Date not provide. FORMAT: \" TASK /at DATE\"\n" + bar);
                        } else {
                            list[listLen] = new Event(eventSplit[0], eventSplit[1]);
                            listLen++;
                            System.out.println(bar + "\n    added: " + eventSplit[0] + "\n    Now you have " + listLen + " tasks in your list\n" +
                                    bar);
                        }
                    }
                    else { // no task type provided
                        System.out.println(bar + "\n    You have not specified the type of task this is! Type \"help\" for help\n" + bar);
                    }
                }
                else { // only one word
                    if (str.equalsIgnoreCase("help")) {
                        System.out.println(bar + "\n    ToDos: tasks without any date/time attached to it e.g., visit new theme park\n" +
                                "      FORMAT: todo TASKNAME\n" +
                                "    Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm\n" +
                                "      FORMAT: deadline TASKNAME /by DEADLINE\n" +
                                "    Events: tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm\n" +
                                "      FORMAT: todo TASKNAME /by DATE\n" +
                                "    \"/done x\" where x is the task number to mark task as done.\n" + bar);
                    } else {
                        System.out.println(bar + "\n    You have not specified the type of task this is! Type \"help\" for help\n" + bar);
                    }
                }
            }
        }
        while (!away);
    }
    public static class Task {
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
    public static class Deadline extends Task {

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
    public static class Todo extends Task {

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }
    public static class Event extends Task {

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
}
