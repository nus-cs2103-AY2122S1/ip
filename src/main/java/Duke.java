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
                int index = Integer.parseInt(words[1]) - 1;
                tasks.get(index).setIsDone();
                if (index >= tasks.size() || index < 0) {
                    System.out.println("Please input correct index, no such index :( ");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("You must've forgotten. Please indicate index :) ");
            }
            return "";
        } else {
            try {
                createTask(words);
            } catch (IllegalArgumentException e) {
                System.out.println("Please input in the form: <Type of Task> <Name of Task>" +
                        "and include keyword '/at' OR '/by' with date if relevant.");
            }
            return "";
        }
    }

    public static void createTask(String[] args) {
        if (args.length < 2) {
            System.out.println("     Oops, you have left out the task!");
        } else if (args[0].equalsIgnoreCase("todo")) {
            try {
                ToDo t = new ToDo(filterInfo(args));
                addTask(t);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("     You must've forgotten. Please indicate index :) ");
            }
        } else if (args[0].equalsIgnoreCase("deadline")) {
            try {
                Deadline d = new Deadline(filterInfo(args), lookForDeadline(args));
                addTask(d);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("     You must've forgotten. Please indicate index :) ");
            } catch (IllegalArgumentException e) {
                System.out.println("     " + e.getMessage());
            }
        } else if (args[0].equalsIgnoreCase("event")) {
            try {
                Event d = new Event(filterInfo(args), searchForEventDay(args));
                addTask(d);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("You must've forgotten. Please indicate index :) ");
            } catch (IllegalArgumentException e) {
                System.out.println("     " + e.getMessage());
            }
        }
    }

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

    public static String getDate(String[] s, int start) {
        String temp = "";
        for (int i = start; i < s.length; i++) {
            temp += s[i] + " ";
        }
        return temp;
    }

    /**
     * To add things to the stored list of tasks
     *
     * @param t the task to be added
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
        System.out.println("     The current list has these items:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i).getType() + "[" + tasks.get(i).getStatus() + "] "
                    + tasks.get(i).getTask());
        }
        System.out.println("     There are " +tasks.size() + " tasks now, keep up!");
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
                System.out.println("Well done! The task is completed!");
            } else {
                System.out.println("You have already completed this task before!");
            }
            System.out.println("       [" + isDone + "] " + this.getTask());
        }

        /**
         * To retrieve the status of the task
         * whether it is complete or not
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

        public String getType() {
            return "regular";
        }
    }

    public static class ToDo extends Task{
        String type = "[T]";

        public ToDo(String s) {
            super(s);
        }

        public String getType() {
            return this.type;
        }
    }

    public static class Deadline extends Task{
        String type = "[D]";
        String dueDate;

        public Deadline(String s, String date) {
            super(s);
            this.dueDate = date;
        }

        public String getType() {
            return this.type;
        }

        @Override
        public String getTask() {
            return super.getTask() + "(by: " + this.dueDate + ")";
        }
    }

    public static class Event extends Task{
        String type = "[E]";
        String date;

        public Event(String s, String date) {
            super(s);
            this.date = date;
        }

        public String getType() {
            return this.type;
        }

        @Override
        public String getTask() {
            return super.getTask() + "(at: " + this.date + ")";
        }
    }
}