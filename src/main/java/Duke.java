import java.util.ArrayList;
import java.util.Scanner;

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
     * terminates if user has given the correct keyword
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
            String message = chat(currentLine);
            if (!message.equals("")) {
                System.out.println("     " + message);
            }
            System.out.println(separator);
            if (message.equals("Bye. Hope to see you again soon!")){
                System.exit(0);
            }
        }
        System.exit(0);
    }

    /**
     * To determine the appropriate response from input command
     *
     * @param s input commands that is read by scanner in 'main'
     * @return the corresponding response message as a String
     */
    public static String chat(String s) {
        String check = s.replaceAll(" ", "");
        String[] words = s.split(" ");
        if (check.equalsIgnoreCase("bye")) {
            return "Bye. Hope to see you again soon!";
        } else if (check.equalsIgnoreCase("list")) {
            getList();
            return "";
        } else if (words[0].equalsIgnoreCase("done")) {
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
                    System.out.println("     You must've forgotten, please input index :)");
            } catch (NumberFormatException e) {
                System.out.println("     Please use a number instead :(");
            } catch (IllegalArgumentException e) {
                System.out.println("     " + e.getMessage());
            }
            return "";
        } else {
            try {
                createTask(words);
            } catch (IllegalArgumentException e) {
                System.out.println("     " + e.getMessage());
                System.out.println("     Please input in the form: <Type of Task> <Name of Task>" +
                        " and include keyword '/at' OR '/by' with date if relevant.");
            }
            return "";
        }
    }

    /**
     * To create a Task object that may be ToDO, Deadline or Event
     * depending on user's input, before adding it to the list of tasks
     *
     * @param args the String array representation of the input by user
     */
    public static void createTask(String[] args) {
        if (args.length < 2) {
            if (!args[0].equalsIgnoreCase("todo") && !args[0].equalsIgnoreCase("deadline")
            && !args[0].equalsIgnoreCase("event")) {
                System.out.println("     Invalid input :(");
            } else {
                System.out.println("     Oops, you have left out the task description!");
            }
            System.out.println("     Please input in the form: <Type of Task> <Name of Task>" +
                    " and include keyword '/at' OR '/by' with date if relevant.");
        } else if (args[0].equalsIgnoreCase("todo")) {
            ToDo t = new ToDo(filterInfo(args));
            addTask(t);
        } else if (args[0].equalsIgnoreCase("deadline")) {
            Deadline d = new Deadline(filterInfo(args), lookForDeadline(args));
            addTask(d);
        } else if (args[0].equalsIgnoreCase("event")) {
            Event d = new Event(filterInfo(args), searchForEventDay(args));
            addTask(d);
        } else {
            System.out.println("     Invalid input :(");
            System.out.println("     Please input in the form: <Type of Task> <Name of Task>" +
                    " and include keyword '/at' OR '/by' with date if relevant.");
        }
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
        return temp;
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
        throw new IllegalArgumentException("Please input in the form: <Event> <Name> /at <Date>.");
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
        throw new IllegalArgumentException("Please input in the form: <Deadline> <Name> /by <Date>.");
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
            System.out.println("     There are " + tasks.size() + " tasks now, keep up!");
        } else {
            System.out.println("     There are no items in your list, keesfep adding them!");
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

        /**
         * Constructor for a Deadline task
         *
         * @param s the input string to describe the Deadline task
         * @param date the do-by-date
         */
        public Deadline(String s, String date) {
            super(s);
            this.dueDate = date;
        }

        /**
         * To retrieve the information on the type of Task
         *
         * @return the String description of the type of Task
         */
        public String getType() {
            return this.type;
        }

        /**
         * To retrieve the description of the Deadline task
         *
         * @return the String that is the description of the Deadline task
         */
        @Override
        public String getTask() {
            return super.getTask() + "(by: " + this.dueDate + ")";
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
        final String type = "[E]";

        /**
         * The String to store the date information
         * that identifies an Event task
         */
        final String date;

        /**
         * Constructor for an Event task
         * @param s the input string to describe the Event task
         * @param date the date of the event
         */
        public Event(String s, String date) {
            super(s);
            this.date = date;
        }

        /**
         * To retrieve the information on the type of Task
         *
         * @return the String description of the type of Task
         */
        public String getType() {
            return this.type;
        }

        /**
         * To retrieve the description of the Event task
         *
         * @return the String that is the description of the Event task
         */
        @Override
        public String getTask() {
            return super.getTask() + "(at: " + this.date + ")";
        }
    }
}