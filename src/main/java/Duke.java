import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.*;

public class Duke {

    enum Category {
        TODO,
        EVENT,
        DEADLINE
    }

    public class Display {

        /** Boolean variable to exit the program */
        private boolean bye = false;

       /** ArrayList to store all tasks */
        private ArrayList<TaskList> arr = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        /**
         * Method to return the initial default greeting
         * @return The initial String greeting
         */
        public String initGreeting() {
            return "_____________________________________________________________________" + "\n"
                    + "    Hi! I'm Muts!" + "\n" + "    Just a little intro : I'm not a bot, I am as real as any of your human friends!"
                    + "\n" + "    How can I help to make your day as amazing as you are?" + "\n"
                    + "_____________________________________________________________________";
        }

        /**
         * Execution method to take input and execute the rest
         * of the program flow based on the input
         * @throws DukeException
         */
        public void execInput() throws DukeException {
            System.out.println(initGreeting());
            while (!bye) {
                String inp = sc.nextLine();

                if (inp.equals("list")) {
                    System.out.println("_____________________________________________________________________");
                    System.out.println("    Here are the tasks in your list:");
                    for (int j = 0; j < arr.size(); j++) {
                        System.out.println("    " + (j + 1) + ". " + arr.get(j).toString());
                    }
                    System.out.println("_____________________________________________________________________");
                } else if (inp.startsWith("todo")) {
                    String newInp = inp.replaceAll("\\s", "");
                    if (newInp.equals("todo")) {
                        throw new TodoException("OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        TaskList t = new TaskList(inp, Category.TODO);
                        arr.add(t);
                        System.out.println("_____________________________________________________________________");
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("        " + t.toString());
                        System.out.println("    Now you have " + arr.size() + " tasks in the list.");
                        System.out.println("_____________________________________________________________________");
                    }
                } else if (inp.startsWith("deadline ")) {
                    TaskList t = new TaskList(inp, Category.DEADLINE);
                    arr.add(t);
                    System.out.println("_____________________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("        " + t.toString());
                    System.out.println("    Now you have " + arr.size() + " tasks in the list.");
                    System.out.println("_____________________________________________________________________");
                } else if (inp.startsWith("event ")) {
                    TaskList t = new TaskList(inp, Category.EVENT);
                    arr.add(t);
                    System.out.println("_____________________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("        " + t.toString());
                    System.out.println("    Now you have " + arr.size() + " tasks in the list.");
                    System.out.println("_____________________________________________________________________");
                } else if (inp.startsWith("done ")) {
                    System.out.println("_____________________________________________________________________");
                    System.out.println("    Nice! I've marked this task as done:");
                    int ind = Integer.parseInt((inp.split("\\s"))[1]) - 1;
                    arr.get(ind).markAsDone();
                    System.out.println("        " + arr.get(ind).toString());
                    System.out.println("_____________________________________________________________________");
                } else if (inp.startsWith("delete ")) {
                    System.out.println("_____________________________________________________________________");
                    System.out.println("    Noted. I've removed this task:");
                    int ind = Integer.parseInt((inp.split("\\s"))[1]) - 1;
                    System.out.println("        " + arr.get(ind).toString());
                    arr.remove(ind);
                    System.out.println("    Now you have " + arr.size() + " tasks in the list.");
                    System.out.println("_____________________________________________________________________");
                } else if (inp.equals("bye")) {
                    System.out.println("_____________________________________________________________________");
                    System.out.println("    Bye. Hope to see you again soon! Just a little reminder : YOU ARE AWESOME :))");
                    System.out.println("_____________________________________________________________________");
                    bye = false;
                    break;
                } else {
                    throw new InvalidInputException("OOPS!!! I'm sorry, but I don't know what that means :(");
                }
            }
        }
    }

    public class TaskList {

        /** The content of the task */
        private String description;

        /** Boolean value storing whether the task is done */
        private Boolean isDone;

        /** The category of the task in Enum */
        private Category cat;

        /**
         * Constructor for various tasks in the TaskList
         * @param description The content of the task
         * @param cat The category of the task
         */
        public TaskList(String description, Category cat) {
            this.description = description;
            this.isDone = false;
            this.cat = cat;
        }

        /**
         * Method to mark a task in the list as done
         */
        public void markAsDone() {
            this.isDone = true;
        }

        /**
         * Method to extract the task content from the entire task String
         * @return The actual content of the task
         */
        public String getTask() {
                String desc = ((description.split("\\s",2)[1]).split("/"))[0];
                return desc;
        }

        /**
         * Method to extract the timelines from the deadline and event task categories
         * @return The time the task needs to be done by
         */
        public String getTime() {
            String atByTime = ((description.split("\\s",2)[1]).split("/"))[1];
            String time = atByTime.split("\\s", 2)[1];
            return time;
        }

        /**
         * Method to change the status of the task icon depending on
         * whether the task is done or not
         * @return The status icon of the task
         */
        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        /**
         * Method to custom print the task based on category
         * @return String comprising the type and content of the task
         */
        public String toString() {
            if (this.cat == Category.TODO) {
                return "[T]" + "[" + this.getStatusIcon() + "] " + getTask();
            } else if (this.cat == Category.DEADLINE) {
                return "[D]" + "[" + this.getStatusIcon() + "] " + getTask() + " (by: " + this.getTime() + ")";
            } else {
                return "[E]" + "[" + this.getStatusIcon() + "] " + getTask() + " (at: " + this.getTime() + ")";
            }
        }
    }

    public Display disp(){
        return new Display();
    }

    public static void main(String[] args) throws DukeException {
        Display ob = new Duke().disp();
        ob.execInput();
    }
}
