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
            //implementation in main ensures that words[0] will not be null
            try {
                int index = Integer.parseInt(words[1]) - 1;
                tasks.get(index).setIsDone();
                if (index >= tasks.size() || index < 0) {
                    throw new IllegalArgumentException("Please input correct index, no such index :( ");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please input correct index, no such index :( ");
            } catch (IllegalArgumentException e) {
                System.out.println("     " + e.getMessage());
            }
            return "";
        } else {
            return addTask(s);
        }
    }

    /**
     * To add things to the stored list of tasks
     *
     * @param s the name of task to be added
     * @return message as confirmation of adding operation
     */
    public static String addTask(String s) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        Task t = new Task(s);
        tasks.add(t);
        return "added: " + s;
    }

    /**
     * To display the entire list of tasks sequentially
     */
    public static void getList() {
        System.out.println("The current list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + "[" + tasks.get(i).getStatus() + "]"
                    + tasks.get(i).getTask());
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
         * @param what the input string to describe the task
         */
        public Task(String what) {
            this.task = what;
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
    }
}