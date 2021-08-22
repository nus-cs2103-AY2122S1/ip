import java.io.*;
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
    private static final String filePath = "../data/duke.txt";
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
        File data = new File("../data");
        File saves = new File(filePath);
        try {
            if (!data.exists() || !data.isDirectory()) {
                boolean dir = data.mkdir();
            } if (!saves.exists()) {
                boolean save = saves.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("something went wrong: " + e.getMessage() + "\nexiting program...");
            System.exit(0);
        }
        loadSaves();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String currentLine = sc.nextLine();
            System.out.println(currentLine);
            System.out.println(separator);
            String temp = "[\\w|\\d\\\\/]\\S*";
            Pattern p = Pattern.compile(temp);
            ArrayList<String> words = new ArrayList<>();
            Matcher m = p.matcher(currentLine);
            while (m.find()) {
                words.add(m.group());
            }
            chat(words);
            System.out.println(separator);
        }
        sc.close();
    }

    private static void loadSaves() {
        if (tasks == null){
            tasks = new ArrayList<>();
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String s = reader.readLine();
            while (s != null) {
                String[] words = s.split("\\|");
                if (words[0].equals("T")) {
                    ToDo t = new ToDo(words[1]);
                    if (words[words.length - 1].equals("1")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                }
                if (words[0].equals("D")) {
                    Deadline d = new Deadline(words[1], words[2]);
                    if (words[words.length - 1].equals("1")) {
                        d.markAsDone();
                    }
                    tasks.add(d);
                }
                if (words[0].equals("E")) {
                    Event e = new Event(words[1], words[2]);
                    if (words[words.length - 1].equals("1")) {
                        e.markAsDone();
                    }
                    tasks.add(e);
                }
                s = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void resetFile(ArrayList<Task> currTasks) {
        File file = new File(filePath);
        if (file.delete()) {
            System.out.println("     Updating list...");
        } else {
            System.out.println("something went wrong");
        }
        try {
            boolean other = file.createNewFile();
            FileWriter writer = new FileWriter(filePath, true);
            for (Task t : currTasks) {
                writer.write(t.getSaveFormat() + "\n");
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Unable to write, something went wrong");
        }

    }

    public enum Command{
        TODO, DEADLINE, EVENT, LIST, DONE, DELETE, BYE
    }

    /**
     * To determine the appropriate response from input command
     * by verifying if a valid command has been keyed
     *
     * @param s input words that is read by scanner in 'main'
     */
    public static void chat(ArrayList<String> s) {
        if (s == null || s.isEmpty()) {
            helperMessage();
        } else if (s.get(0).equalsIgnoreCase("bye")) {
            Command c = Command.BYE;
            doSomething(c, new ArrayList<>());
        } else if (s.get(0).equalsIgnoreCase("list")) {
            Command c = Command.LIST;
            doSomething(c, new ArrayList<>());
        } else if (s.get(0).equalsIgnoreCase("done")) {
            Command c = Command.DONE;
            doSomething(c, s);
        } else if (s.get(0).equalsIgnoreCase("delete")) {
            Command c = Command.DELETE;
            doSomething(c, s);
        } else if (s.get(0).equalsIgnoreCase("todo")) {
            Command c = Command.TODO;
            doSomething(c, s);
        } else if (s.get(0).equalsIgnoreCase("deadline")) {
            Command c = Command.DEADLINE;
            doSomething(c, s);
        } else if (s.get(0).equalsIgnoreCase("event")) {
            Command c = Command.EVENT;
            doSomething(c, s);
        } else {
            System.out.println("     Invalid input :(");
            helperMessage();
        }
    }

    /**
     * The key method that calls other relevant methods
     * depending on instructions as per input by user
     *
     * @param c the command specific to a certain sequence of method calls
     * @param doWhat the user's input
     */
    public static void doSomething(Command c, ArrayList<String> doWhat) {
        switch (c) {
        case BYE:
            System.out.println("     Bye. Hope to see you again soon!");
            System.exit(0);
            break;
        case LIST:
            getList();
            break;
        case DONE:
            try {
                if (doWhat.size() == 1) {
                    throw new IllegalArgumentException("Please input index :)");
                }
                if (doWhat.size() > 2) {
                    throw new IllegalArgumentException("Please input in the form: 'done <index>'.");
                }
                int index = Integer.parseInt(doWhat.get(1)) - 1;
                if (index >= tasks.size() || index < 0) {
                    throw new IllegalArgumentException("No such index. Please input correct index, no such index :(");
                }
                tasks.get(index).setIsDone();
                resetFile(tasks);
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
            if (doWhat.size() == 1) {
                System.out.println("     Unable to delete task without an index. Please input index :)");
                System.out.println("     Please input in the form: 'delete <task index>'.");
                System.out.println("     Note: list can be used to see the current tasks.");
            } else {
                if (tasks.isEmpty()) {
                    System.out.println("     List is empty, no tasks to delete, looking good!");
                } else if (doWhat.size() > 2) {
                    System.out.println("Please input in the form: 'delete <index>'.");
                } else {
                    try {
                        int index = Integer.parseInt(doWhat.get(1)) - 1;
                        deleteTask(index);
                    } catch (NumberFormatException e) {
                        System.out.println("     Please use a number instead :(");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("     Please input a valid index :)");
                        System.out.println("     Note: 'list' can be used to see the current tasks.");
                    }
                }
            }
            break;
        case TODO:
            if (doWhat.size() < 2) {
                System.out.println("     Oops, you have left out the task description for todo!");
            } else {
                ToDo t = new ToDo(filterInfo(doWhat));
                addTask(t);
            }
            break;
        case DEADLINE:
            try {
                Deadline d = new Deadline(filterInfo(doWhat), lookForDeadline(doWhat));
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
            try {
                Event e = new Event(filterInfo(doWhat), searchForEventDay(doWhat));
                addTask(e);
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
        }
    }

    /**
     * To delete a task from the list
     *
     * @param ind the index of task to be deleted
     */
    public static void deleteTask(int ind) {
        Task t = tasks.remove(ind);
        resetFile(tasks);
        System.out.println("     Noted, the following task has been deleted: ");
        System.out.println("       " + t.getType() + t.getStatus() + " " + t.getTask());
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
    }

    /**
     * To get the String representation of the date/deadline as input by user
     *
     * @param words the String array representation of the input by user
     * @return the concatenated String that is the date specified by user
     */
    public static String filterInfo(ArrayList<String> words) {
        String temp = "";
        for (int i = 1; i < words.size(); i++) {
            if (!(words.get(i).equals("/at") || words.get(i).equals("/by"))
                    || words.get(0).equalsIgnoreCase("todo")) {
                temp += words.get(i) + " ";
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
    public static String searchForEventDay(ArrayList<String> args) throws IllegalArgumentException {
        for (int i = 2; i < args.size(); i++) {
            if (args.get(i).equals("/at")) {
                if  (i + 1 >= args.size()) {
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
    public static String lookForDeadline(ArrayList<String> arg) throws IllegalArgumentException {
        for (int i = 2; i < arg.size(); i++) {
            if (arg.get(i).equals("/by")) {
                if  (i + 1 >= arg.size()) {
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
    public static String getDate(ArrayList<String> s, int start) {
        String temp = "";
        for (int i = start; i < s.size(); i++) {
            if (i + 1 < s.size()) {
                temp += s.get(i) + " ";
            } else {
                temp += s.get(i);
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
        resetFile(tasks);
        System.out.println("     Successfully added:\n" + "       " + t.getType() + t.getStatus() + " " + t.getTask());
    }

    /**
     * To display the entire list of tasks sequentially
     */
    public static void getList() {
        if (tasks != null && !tasks.isEmpty()) {
            System.out.println("     The current list has these items:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("     " + (i + 1) + "." + tasks.get(i).getType() + tasks.get(i).getStatus() + " "
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
            this.isDone = "[ ]";
        }

        /**
         * To mark a task as complete by changing
         * the String representation
         */
        public void setIsDone() {
            if (this.isDone.equals("[ ]")) {
                this.isDone = "[X]";
                System.out.println("     Well done! The task is completed!");
            } else {
                System.out.println("     You have already completed this task before!");
            }
            System.out.println("       " + this.getType() + isDone + " " + this.getTask());
        }

        public void markAsDone() {
            this.isDone = "[X]";
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

        public String getSaveFormat() { return "regular"; }
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
        @Override
        public String getType() {
            return this.type;
        }

        @Override
        public String getSaveFormat() {
            if (super.getStatus().equals("[ ]")) {
                return "T" + "|" + this.getTask().strip() + "|" + 0;
            } else {
                return "T" + "|" + this.getTask().strip() + "|" + 1;
            }
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

        public String getActualTask() { return super.getTask(); }

        @Override
        public String getSaveFormat() {
            if (super.getStatus().equals("[ ]")) {
                return "D" + "|" + this.getActualTask().strip() + "|" + this.dueDate + "|" + 0;
            } else {
                return "D" + "|" + this.getActualTask().strip() + "|" + this.dueDate+ "|" + 1;
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

        public String getActualTask() {
            return super.getTask();
        }

        @Override
        public String getSaveFormat() {
            if (super.getStatus().equals("[ ]")) {
                return "E" + "|" + this.getActualTask().strip() + "|" + this.date + "|" + 0;
            } else {
                return "E" + "|" + this.getActualTask().strip() + "|" + this.date + "|" + 1;
            }
        }
    }
}