package main.java;
import java.util.Scanner;

/**
 * Duke is a Personal Assistant Chatbot that helps a person
 * keep track of various things.
 */
public class Duke {
    //Duke initialisation
    private static final String line = "\t____________________________________________________________";
    private static final String intro = "Hello! I'm Duke\n\t What can I do for you?";
    private static final String bye = "Bye. Hope to see you again soon!";
    private static final String done = "Nice! I've marked this task as done: \n\t  ";
    private static Task[] list = new Task[100];
    private static int listCount = 0;

    /**
     * A task contains its description and a boolean of whether it is done or not.
     */
    public static class Task {
        private String description;
        private boolean isDone = false;

        /**
         * Constructor for task.
         *
         * @param description
         */
        public Task(String description) {
            this.description = description;
        }

        /**
         * Returns the status icon of the task.
         *
         * @return status icon
         */
        private String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        /**
         * Setter to change the done status of the task.
         */
        public void setDone() {
            isDone = true;
        }

        /**
         * Returns the string representation of the task.
         *
         * @return the string representation of the task
         */
        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
    }

    /**
     * Provides horizontal lines with indentation.
     *
     * @param str
     * @return formatted reply
     */
    private static void reply(String str) {
         System.out.println(line);
         System.out.println("\t " + str);
         System.out.println(line + "\n");
    }

    /**
     * Prints out the list.
     */
    private static void printList() {
        System.out.println(line);
        if (listCount == 0) {
            System.out.println("\t List is empty");
        } else {
            for (int i = 0; i < listCount; i++) {
                System.out.println("\t " + (i + 1) + "." + list[i]);
            }
        }
        System.out.println(line + "\n");
    }

    public static void main(String[] args) {
        reply(intro);
        Scanner sc = new Scanner(System.in);
        boolean on = true;
        while (on) {
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                    on = false;
                    reply(bye);
                    sc.close();
                    break;
                case "list":
                    printList();
                    break;
                default:
                    if (input.length() > 5 && input.substring(0, 4).equals("done")) {
                        Task task = list[Integer.valueOf(input.substring(5)) - 1];
                        task.setDone();
                        reply(done + task);
                    } else {
                        list[listCount++] = new Task(input);
                        reply("added: " + input);
                    }
            }
        }
    }
}
