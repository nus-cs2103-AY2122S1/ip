import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * A task that can be mark as completed.
     */
    private static class Task {
        /**
        * The description of the task.
        */
        private String description;

        /**
         * If true, the task is completed. Else, the task is not completed.
         */
        private boolean isCompleted = false;

        /**
         * A private constructor used to initialize the task.
         *
         * @param description the description of the task.
         */
        private Task(String description) {
            this.description = description;
        }

        /**
         * Marks the task as done.
         */
        private void markAsDone() {
            this.isCompleted = true;
        }

        /**
         * Return the string representation of task.
         *
         * @return the string representation of task.
         */
        @Override
        public String toString() {
            String displayCompletion = isCompleted ? "[X]" : "[]";
            return displayCompletion + ' ' + description;
        }
    }

    /**
     * The list of tasks that are inputted by the user.
     */
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Prints a line.
     */
    private static void printLine() {
        System.out.println("_________________________________________");
    }

    /**
     * Prints the greeting message.
     */
    private static void greetUser() {
        printLine();
        System.out.println("Greetings! I am Duke.");
        System.out.println("How can I assist you?");
        printLine();
    }

    /**
     * Prints the farewell message.
     */
    private static void farewellUser() {
        printLine();
        System.out.println("Bye! See you soon!");
        printLine();
    }

    /**
     * Adds the task to the list and prints the added task.
     *
     * @param task the task that will be added to the list
     */
    private static void addToList(Task task) {
        taskList.add(task);
        System.out.printf("added: %s%n", task.description);
    }

    /**
     * Prints all the tasks in the list in order.
     */
    private static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, taskList.get(i));
        }
    }

    /**
     * Marks the task with taskNo specified and prints the task completed.
     *
     * @param taskNo the task number.
     */
    private static void markTaskAsDone(int taskNo) {
        Task task = taskList.get(taskNo - 1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("  %s%n", task);
    }

    /**
     * Interacts with the user.
     *
     * @param args array of strings.
     */
    public static void main(String[] args) {
        greetUser();

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String[] splitInput = userInput.split(" ");
        while (!userInput.equalsIgnoreCase("bye")) {
            printLine();
            if (userInput.equals("list")) {
                printTasks();
            } else if (splitInput[0].equals("done")) {
                markTaskAsDone(Integer.parseInt(splitInput[1]));
            } else {
                addToList(new Task(userInput));
            }
            printLine();
            userInput = scanner.nextLine();
            splitInput = userInput.split(" ");
        }
        scanner.close();

        farewellUser();
    }
}
