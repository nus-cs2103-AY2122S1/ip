import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** This class implements the Duke assistant/chat-bot
 * @author damithc
 * edited by Wanyu
 */
public class Duke {

    /**
     * List of tasks that are stored
     */
    private static ArrayList<Task> tasks;

    /**
     * Greets the user as well as reads user's inputs with a scanner
     * calls the 'chat' method to respond accordingly
     *
     * @param args the command-line argument for the program to execute
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String separator = "     _______________________________________________________";
        System.out.println("Hello from\n" + logo);
        System.out.println(separator);
        System.out.println("     Hi! I am Duke!\n" + "     What can I do for you?");
        System.out.println(separator);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String currentLine = sc.nextLine();
            System.out.println(currentLine);
            System.out.println(separator);
            chat(currentLine);
            System.out.println(separator);
        }
    }

    private static String checkForDate(String s){
        String temp = "^[0-9]{1,2}[\\\\/][0-9]{1,2}[\\\\/][0-9]{4}\\s[0-9]{4}$";
        Pattern p = Pattern.compile(temp);
        Matcher m = p.matcher(s);
        if (m.find()) {
           return m.group();
        }
        return "";
    }

    private static LocalDate convertDate(String s) {
        String[] date = s.substring(0,s.length() - 4).split("/");
        int day = Integer.parseInt(date[0].replaceAll(" ", ""));
        int month = Integer.parseInt(date[1].replaceAll(" ", ""));
        int year = Integer.parseInt(date[2].replaceAll(" ", ""));
        return LocalDate.of(year, month, day);
    }

    private static void anyItemsDue(String s) {
        ArrayList<Task> dueItems = new ArrayList<>();
        if (tasks.isEmpty()) {
            System.out.println("     No tasks yet!");
        } else {
            String[] date = s.split("/");
            LocalDate ref = LocalDate.parse(date[0] + "-" + date[1] + "-" + date[2]);
            for (Task t : tasks) {
                if (!(t instanceof ToDo)) {
                    LocalDate temp = t.getLocalDate();
                    if (temp != null) {
                        if (temp.equals(ref)) {
                            dueItems.add(t);
                        }
                    }
                }
            }
            if (dueItems.isEmpty()) {
                System.out.println("     Nothing due on this day!");
            } else {
                System.out.println("     The items due are: ");
                for (int i = 0; i < dueItems.size(); i++) {
                    System.out.println("     " + (i + 1) + "." + dueItems.get(i).getType() + "["
                            + dueItems.get(i).getStatus() + "] " + tasks.get(i).getTask());
                }
            }
        }
    }


    public enum Command{
        TODO, DEADLINE, EVENT, LIST, DONE, DELETE, BYE, DUE
    }

    /**
     * To determine the appropriate response from input command
     * by verifying if a valid command has been keyed
     *
     * @param s input words that is read by scanner in 'main'
     */
    public static void chat(String s) {
        String check = s.replaceAll(" ", "");
        String[] words = s.split(" ");
        if (check.equalsIgnoreCase("bye")) {
            Command c = Command.BYE;
            doSomething(c, "");
        } else if (check.equalsIgnoreCase("list")) {
            Command c = Command.LIST;
            doSomething(c, "");
        } else if (words[0].equalsIgnoreCase("done")) {
            Command c = Command.DONE;
            doSomething(c, s);
        } else if (words[0].equalsIgnoreCase("delete")) {
            Command c = Command.DELETE;
            doSomething(c, s);
        } else if (words[0].equalsIgnoreCase("todo")) {
            Command c = Command.TODO;
            doSomething(c, s);
        } else if (words[0].equalsIgnoreCase("deadline")) {
            Command c = Command.DEADLINE;
            doSomething(c, s);
        } else if (words[0].equalsIgnoreCase("event")) {
            Command c = Command.EVENT;
            doSomething(c, s);
        } else if (words[0].equalsIgnoreCase("due")) {
            Command c = Command.DUE;
            doSomething(c, s);
        } else {
            System.out.println("     Invalid input :(");
            helperMessage();
        }
    }

    /**
     * The key method that calls other relevant methods
     * depending on instructions as per input by user
     * @param c the command specific to a certain sequence of method calls
     * @param doWhat the user's input
     */
    public static void doSomething(Command c, String doWhat) {
        switch (c) {
            case BYE:
                System.out.println("     Bye. Hope to see you again soon!");
                System.exit(0);
                break;
            case LIST:
                getList();
                break;
            case DONE:
                String[] words = doWhat.split(" ");
                try {
                    if (words.length == 1) {
                        throw new IllegalArgumentException("Please input index :)");
                    }
                    int index = Integer.parseInt(words[1]) - 1;
                    tasks.get(index).setIsDone();
                    if (index >= tasks.size() || index < 0) {
                        throw new IllegalArgumentException("No such index. Please input correct index, no such index :(");
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("     Please input a valid index :)");
                    System.out.println("     Note: 'list' can be used to see the current tasks.");
                } catch (NumberFormatException e) {
                    System.out.println("     Please use a number instead :(");
                } catch (IllegalArgumentException e) {
                    System.out.println("     " + e.getMessage());
                }
                break;
            case DELETE:
                String[] which = doWhat.split(" ");
                if (which.length == 1) {
                    System.out.println("     Unable to delete task without an index. Please input index :)");
                    System.out.println("     Please input in the form: 'delete <task index>'.");
                    System.out.println("     Note: list can be used to see the current tasks.");
                } else {
                    if (tasks.isEmpty()) {
                        System.out.println("     List is empty, no tasks to delete, looking good!");
                    } else {
                        try {
                            int index = Integer.parseInt(which[1]) - 1;
                            deleteTask(index);
                        } catch (NumberFormatException e) {
                            System.out.println("     Please use a number instead :(");
                        }
                    }
                }
                break;
            case TODO:
                String[] toDoWhat = doWhat.split(" ");
                if (toDoWhat.length < 2) {
                    System.out.println("     Oops, you have left out the task description for todo!");
                } else {
                    ToDo t = new ToDo(filterInfo(toDoWhat));
                    addTask(t);
                }
                break;
            case DEADLINE:
                String[] deadlineDoWhat = doWhat.split(" ");
                try {
                    Deadline d = new Deadline(filterInfo(deadlineDoWhat), lookForDeadline(deadlineDoWhat));
                    addTask(d);
                } catch (IllegalArgumentException e) {
                    if (e.getMessage().equals("deadline")) {
                        System.out.println("     Invalid input :(");
                        System.out.println("     Please input in the form: 'deadline <Name> /by <Date>'.");
                    } else {
                        System.out.println("     " + e.getMessage());
                        System.out.println("     Hey, no deadline recorded does not mean no deadline >:(");
                    }
                }
                break;
            case EVENT:
                String[] whatEvent = doWhat.split(" ");
                try {
                    Event d = new Event(filterInfo(whatEvent), searchForEventDay(whatEvent));
                    addTask(d);
                } catch (IllegalArgumentException e) {
                    if (e.getMessage().equals("event")) {
                        System.out.println("     Invalid input :(");
                        System.out.println("     Please input in the form: 'event <Name> /at <Date>'.");
                    } else {
                        System.out.println("     " + e.getMessage());
                        System.out.println("     I can't add an event without a date!");
                    }
                }
                break;
            case DUE:
                String[] checkWhen = doWhat.split(" ");
                try {
                    anyItemsDue(checkWhen[1]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("     Invalid input :(");
                    helperMessage();
                }
                break;
        }
    }

    /**
     * To delete a task from the list
     *
     * @param ind the index of task to be deleted
     */
    public static void deleteTask(int ind) {
        //because of how this method is called, ind will be valid
        Task t = tasks.remove(ind);
        System.out.println("     Noted, the following task has been deleted: ");
        System.out.println("       " + t.getType() + "[" + t.getStatus() + "] " + t.getTask());
        System.out.println("     Nice! there are " + tasks.size() + " task(s) left." );
    }

    /**
     * To display commands to help user with input as much as possible
     */
    public static void helperMessage() {
        System.out.println("     Types of tasks: 'todo', 'deadline', 'event'");
        System.out.println("     If you wish to add a task," +
                " please input in the form: '<Type of Task> <Name of Task>'" +
                " and include keyword '/at' OR '/by' followed by <Date> if relevant.");
        System.out.println("     If you wish to delete a task, "
                + "please input in the form: 'delete <task index>'.");
        System.out.println("     If you wish to see the current tasks, please input 'list'.");
        System.out.println("     If you wish to mark a task as done, please input 'done <task index>.'");
        System.out.println("     If you wish to terminate the program, please input 'bye'.");
        System.out.println("     If you wish to check items due on a particular day, please " +
                "input 'due YYYY/MM/DD'.");
    }

    /**
     * To get the String representation of the date/deadline as input by user
     *
     * @param words the String array representation of the input by user
     * @return the concatenated String that is the date specified by user
     */
    public static String filterInfo(String[] words) {
        String temp = "";
        for (int i = 1; i < words.length; i++) {
            if (!(words[i].equals("/at")||words[i].equals("/by"))) {
                temp += words[i] + " ";
            } else {
                break;
            }
        }
        return temp.equals("") ? temp : temp.substring(0, temp.length()-1);
    }

    /**
     * To identify the start of String representation of date of Event
     * by finding the "/at" expression
     *
     * @param args the String array representation of the input by user
     * @return the String representation of the date by passing it to method
     * getDate which requires a starting index as an argument
     * @throws IllegalArgumentException thrown when encountering a String Array without
     * the presence of "/at" expression, which is not a valid input for Event
     */
    public static String searchForEventDay(String[] args) throws IllegalArgumentException {
        for (int i = 1; i < args.length; i++) {
            if (args[i].equals("/at")) {
                if  (i + 1 >= args.length) {
                    throw new IllegalArgumentException("Oh no, date is missing :(");
                } else {
                    return getDate(args, i + 1);
                }
            }
        }
        throw new IllegalArgumentException("event");
    }

    /**
     * To identify the start of String representation of date for Deadline
     * by finding the "/by" expression
     *
     * @param arg the String array representation of the input by user
     * @return the String representation of the date by passing it to method
     * getDate which requires a starting index as an argument
     * @throws IllegalArgumentException thrown when encountering a String Array without
     * the presence of "/by" expression, which is not a valid input for Deadline
     */
    public static String lookForDeadline(String[] arg) throws IllegalArgumentException {
        for (int i = 1; i < arg.length; i++) {
            if (arg[i].equals("/by")) {
                if  (i + 1 >= arg.length) {
                    throw new IllegalArgumentException("Uh oh, deadline is missing :(");
                } else {
                    return getDate(arg, i + 1);
                }
            }
        }
        throw new IllegalArgumentException("deadline");
    }

    /**
     * To create a String representation of date by
     * concatenating a String from the starting index to the last index
     *
     * @param s the String array representation of the input by user
     * @param start the starting index
     * @return String representation of date as input by user
     */
    public static String getDate(String[] s, int start) {
        String temp = "";
        for (int i = start; i < s.length; i++) {
            if (i + 1 < s.length) {
                temp += s[i] + " ";
            } else {
                temp += s[i];
            }
        }
        return temp;
    }

    /**
     * To add things to the stored list of tasks
     *
     * @param t the task to be added/e
     */
    public static void addTask(Task t) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(t);
        System.out.println("     Successfully added:\n" + "     " + t.getType() + "[ ] " + t.getTask());
    }

    /**
     * To display the entire list of tasks sequentially
     */
    public static void getList() {
        if (tasks != null && !tasks.isEmpty()) {
            System.out.println("     The current list has these items:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("     " + (i + 1) + "." + tasks.get(i).getType() + "[" + tasks.get(i).getStatus() + "] "
                        + tasks.get(i).getTask());
            }
            System.out.println("     There are " + tasks.size() + " task(s) now, keep up!");
        } else {
            System.out.println("     There are no items in your list, keep adding them!");
        }
    }

    /**
     * This class implements the tasks to be tracked by the
     * Duke assistant
     */
    public static class Task{
        /**
         * Description of the task
         */
        private final String task;

        /**
         * Status of the task represented by String
         */
        private String isDone;

        /**
         * Constructor for a Task
         * @param s the input string to describe the task
         */
        public Task(String s) {
            this.task = s;
            this.isDone = " ";
        }

        /**
         * To mark a task as complete by changing
         * the String representation
         */
        public void setIsDone() {
            if (this.isDone.equals(" ")) {
                this.isDone = "X";
                System.out.println("     Well done! The task is completed!");
            } else {
                System.out.println("     You have already completed this task before!");
            }
            System.out.println("       [" + isDone + "] " + this.getTask());
        }

        /**
         * To retrieve the status of the task
         * whether it is complete or not
         *
         * @return the string representation of the task's state
         */
        public String getStatus() {
            return this.isDone;
        }

        /**
         * To retrieve the description of the task
         *
         * @return the String that is the description of the task
         */
        public String getTask() {
            return this.task;
        }

        /**
         * To retrieve the information on the type of Task
         *
         * @return the String description of the type of Task
         */
        public String getType() {
            return "regular";
        }

        public LocalDate getLocalDate() {
            return null;
        }
    }

    /**
     * The ToDo class is a child class for Task
     * to support different specificities of a task
     * as input by user
     */
    public static class ToDo extends Task{
        /**
         * The String to store the type of task information
         * that identifies a ToDo task
         */
        final String type = "[T]";

        /**
         * Constructor for a ToDo task
         *
         * @param s the input string to describe the ToDO task
         */
        public ToDo(String s) {
            super(s);
        }

        /**
         * To retrieve the information on the type of Task
         *
         * @return the String description of the type of Task
         */
        public String getType() {
            return this.type;
        }
    }

    /**
     * The Deadline class is a child class for Task
     * to support different specificities of a task
     * as input by user
     */
    public static class Deadline extends Task{
        /**
         * The String to store the type of task information
         * that identifies a Deadline task
         */
        final String type = "[D]";

        /**
         * The String to store the do-by-date information
         * that identifies a Deadline task
         */
        final String dueDate;

        private LocalDate localDate;

        /**
         * Constructor for a Deadline task
         *
         * @param s the input string to describe the Deadline task
         * @param date the do-by-date
         */
        public Deadline(String s, String date) {
            super(s);
            this.dueDate = date;
            String day = checkForDate(date);
            if (!day.equals("")) {
                LocalDate ld = convertDate(day);
                setLocalDate(ld);
            }
        }

        /**
         * To retrieve the information on the type of Task
         *
         * @return the String description of the type of Task
         */
        public String getType() {
            return this.type;
        }

        private void setLocalDate(LocalDate localDate) { this.localDate = localDate; }

        @Override
        public LocalDate getLocalDate() { return this.localDate; }

        /**
         * To retrieve the description of the Deadline task
         *
         * @return the String that is the description of the Deadline task
         */
        @Override
        public String getTask() {
            if (this.localDate == null) {
                return super.getTask() + "(by: " + this.dueDate + ")";
            } else {
                return super.getTask() + "(by: " + Month.of(this.localDate.getMonthValue()) + " "
                        + this.localDate.getDayOfMonth() + " " + this.localDate.getYear() + ")";
            }
        }
    }

    /**
     * The Event class is a child class for Task
     * to support different specificities of a task
     * as input by user
     */
    public static class Event extends Task{
        /**
         * The String to store the type of task information
         * that identifies an Event task
         */
        private final String type = "[E]";

        /**
         * The String to store the date information
         * that identifies an Event task
         */
        private final String date;

        private LocalDate localDate;

        /**
         * Constructor for an Event task
         * @param s the input string to describe the Event task
         * @param date the date of the event
         */
        public Event(String s, String date) {
            super(s);
            this.date = date;
            String day = checkForDate(date);
            if (!day.equals("")) {
                LocalDate ld = convertDate(day);
                setLocalDate(ld);
            }
        }

        /**
         * To retrieve the information on the type of Task
         *
         * @return the String description of the type of Task
         */
        public String getType() { return this.type; }

        private void setLocalDate(LocalDate localDate) { this.localDate = localDate; }

        @Override
        public LocalDate getLocalDate() { return this.localDate; }

        /**
         * To retrieve the description of the Event task
         *
         * @return the String that is the description of the Event task
         */
        @Override
        public String getTask() {
            if (this.localDate == null) {
                return super.getTask() + "(at: " + this.date + ")";
            } else {
                return super.getTask() + "(at: " + Month.of(this.localDate.getMonthValue()) + " "
                        + this.localDate.getDayOfMonth() + " " + this.localDate.getYear() + ")";
            }
        }
    }
}

