import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;


/**
 * General class for the Duke thing
 *
 * @author A0217769M
 */

public class Duke {
    static String bar = " -------------------------------------------------------------";
    int listLen = 0;
    ArrayList<Task> list = new ArrayList<>();

    /**
     * General method for handling commands, decided against splitting up the method as it made it too messy
     * @param input input of user
     * @throws IncorrectFormatException error if command input is formatted wrongly
     */
    public void updateList (String input) throws IncorrectFormatException {
        if (input.equalsIgnoreCase("list")) {
            System.out.println(bar);
            for (int i = 1; i <= listLen; i++) {
                System.out.println("    " + i + "." + list.get(i - 1).toString());
            }
            System.out.println(bar);
        } else {
            if (input.indexOf(' ') > 0) { // checking if command given was >= two words
                String[] splitted = input.split(" ", 2);
                input = splitted[1];
                if (splitted[0].equalsIgnoreCase("/done")) { // done-ing item
                    if (!checkNum(splitted[1])) {
                        throw new IncorrectFormatException("Item number not detected. Try again?");
                    } else {
                        int num = Integer.parseInt(splitted[1]);
                        if (0 < num && num <= listLen) {
                            list.get(num - 1).markDone();
                            System.out.println(bar + "\n    Nice! I've marked this task as done:\n    " + list.get(num - 1).toString() + "\n" + bar);
                        } else {
                            throw new IncorrectFormatException("Item number not present. Try again?");
                        }
                    }
                } else if (splitted[0].equalsIgnoreCase("/delete")) { // removing item
                    if (!checkNum(splitted[1])) {
                        throw new IncorrectFormatException("List does not contain this item number. Try again?");
                    } else {
                        int num = Integer.parseInt(splitted[1]);
                        if (0 < num && num <= listLen) {
                            listLen--;
                            System.out.println(bar + "\n    Nice! I've removed this task off the face of the Earth:\n    " + list.get(num - 1).toString() +
                                    "\n    Now you have " + listLen + " tasks in the list.\n" + bar);
                            list.remove(num - 1);
                        } else {
                            throw new IncorrectFormatException("Item number not present. Try again?");
                        }
                    }
                } else if (splitted[0].equalsIgnoreCase("/check")) { // checking date
                    LocalDateTime date;
                    String original = splitted[1];
                    try {
                        date = LocalDate.parse(original).atTime(00, 00);
                    } catch (DateTimeException e){
                        throw new IncorrectFormatException("Date format provided incorrectly.\n    Date format: yyyy-mm-dd");
                    }
                    System.out.println(bar + "\n    Here are your " + printDate(date) + " tasks: \n");
                    for (Task i : list) {
                        if (i instanceof Event) {
                            Event o = (Event) i;
                            if (o.date.getYear() == date.getYear() && o.date.getMonth().equals(date.getMonth()) && o.date.getDayOfMonth() == date.getDayOfMonth()) {
                                System.out.println("    " + i.toString());
                            }
                        } else if (i instanceof Deadline) {
                            Deadline o = (Deadline) i;
                            if (o.date.getYear() == date.getYear() && o.date.getMonth().equals(date.getMonth()) && o.date.getDayOfMonth() == date.getDayOfMonth()) {
                                System.out.println("    " + i.toString());
                            }
                        }
                    }
                    System.out.println(bar + "\n");
                } else if (splitted[0].equalsIgnoreCase("todo")) { // adding todo
                    if (input.isEmpty()) {
                        throw new IncorrectFormatException("Task name not provided.\n" + "    FORMAT: \" TODO TASKNAME\"");
                    } else {
                        list.add(new Todo(input));
                        listLen++;
                        System.out.println(bar + "\n    added: " + input + "\n    Now you have " + listLen + " tasks in your list\n" +
                                bar);
                    }
                } else {
                    String[] splited;
                    boolean isDeadline = false;
                    if (splitted[0].equalsIgnoreCase("deadline")) { // adding deadline
                        splited = input.split("(?i) /by ");
                        isDeadline = true;
                    } else if (splitted[0].equalsIgnoreCase("event")) { // adding event{
                        splited = input.split("(?i) /at ");
                    } else {
                        throw new IncorrectFormatException("Task type or task name not provided!");
                    }
                    if (splited.length == 1) {
                        throw new IncorrectFormatException("Task name or date not provided.");
                    } else {
                        String[] timesplit= {"0000-00-00", "0000"};
                        if (splited[1].indexOf(' ') > 0) {
                            timesplit = splited[1].split(" ", 2);
                        } else {
                            timesplit[0] = splited[1];
                            timesplit[1] = "0000";
                        }
                        LocalDateTime date;
                        try {
                            int hour = Integer.parseInt(timesplit[1].substring(0, 1));
                            int minute = Integer.parseInt(timesplit[1].substring(2, 3));
                            date = LocalDate.parse(timesplit[0]).atTime(hour, minute);
                        } catch (DateTimeException e){
                            throw new IncorrectFormatException("Date format provided incorrectly.\n    Date format: yyyy-mm-dd TIME ((optional) in 24hrs format).");
                        }
                        list.add(isDeadline ? new Deadline(splited[0], date) : new Event(splited[0], date));
                        listLen++;
                        System.out.println(bar + (isDeadline ? "\n    Deadline added: " : "\n    Event added: ") + list.get(listLen - 1).toString() + "\n    Now you have " + listLen + " tasks in your list\n" +
                                bar);
                    }
                }
            } else { // only one word
                if (input.equalsIgnoreCase("help")) {
                    System.out.println(bar + "\n    ToDos: tasks without any date/time attached to it e.g., visit new theme park\n" +
                            "      FORMAT: todo TASKNAME\n" +
                            "    Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm\n" +
                            "      FORMAT: deadline TASKNAME /by DEADLINE\n" +
                            "    Events: tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm\n" +
                            "      FORMAT: todo TASKNAME /by DATE\n" +
                            "    Date format: yyyy-mm-dd TIME ((optional) in 24hrs format).\n" +
                            "    \"/done x\" where x is the task number to mark task as done.\n" +
                            "    \"/check DATE\" where DATE is the date you want to query.\n" +
                            "    \"/delete x\" where x is the task number to be deleted. \n" + bar);
                } else {
                    throw new IncorrectFormatException("Command not complete.");
                }
            }
        }
    }
    /**
     * Method to allow for checking if the String is a number, to prevent errors while deleting/ done-ing tasks
     * @param wool String to be checked
     * @return boolean true if String is num.
     */
    public boolean checkNum (String wool) {
        return (wool != null && wool.matches("^[0-9]*$"));
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
                    e.printStackTrace();
                }
            }
        } while (!away);
    }

    /**
     * Parent class Task, contains doneness and task name
     */
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

    /**
     * Child class of Task, adds deadline field.
     */
    public class Deadline extends Task {

        protected LocalDateTime date;

        public Deadline(String description, LocalDateTime by) {
            super(description);
            this.date = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + printDate(date) + ")";
        }
    }

    /**
     * Child class of Task
     */
    public class Todo extends Task {

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    /**
     * Child class of Task, adds date field.
     */
    public class Event extends Task {

        protected LocalDateTime date;

        public Event(String description, LocalDateTime at) {
            super(description);
            this.date = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + printDate(date) + ")";
        }
    }

    public String printDate(LocalDateTime raw) {
        String month = raw.getMonth().toString().substring(0,3);
        return (month + " " + raw.getDayOfMonth() + " " + raw.getYear() + " " + raw.getHour() + "h " + raw.getMinute()+ "min.");
    }

    /**
     * Exception that handles improperly formatted command.
     */
    public class IncorrectFormatException extends Exception {
        public IncorrectFormatException(String errorMessage) {
            super("\n" + bar + "\n    " + errorMessage + "\n    Type \"help\" for help\n" + bar);
        }
    }
}