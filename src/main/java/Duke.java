import java.util.Scanner;

public class Duke {

    final static String EXIT = "bye";

    /**
     * Display formatted message.
     * @param message Message to be printed in console.
     */
    public static void display(String message) {
        System.out.println("    * * * * * * * * * * * * * * * * * * * *");
        System.out.println("    " + message);
        System.out.println("    * * * * * * * * * * * * * * * * * * * *\n");
    }

    /**
     * Display greeting message.
     */
    public static void greet() {
        display("Hi, I'm Sync-Me Sebby.\n    " +
                "I'm here to assist you with tracking and synchronizing of your personal tasks.\n    " +
                "Let me know how I can help?");
    }

    /**
     * Display exit message.
     */
    public static void exit() {
        display("Goodbye. See you again soon!");
    }

    /**
     *
     * @param userInput user input command.
     * @param splitText the command to split by.
     * @param splitTime the time to split by.
     * @return an array consisting of the description and the time of the task.
     */
    public static String[] processUserInput(String userInput, String splitText, String splitTime) {
        // split the user input string
        String[] splitInput = userInput.split(splitText)[1].split(splitTime);
        String desc = splitInput[0].trim();
        String time = splitInput[1];
        String[] arr = {desc, time};
        return arr;
    }

    public static void main(String[] args) {

        // initialize Scanner object
        Scanner scan = new Scanner(System.in);

        // initialize TaskList
        TaskList tasks = new TaskList();

        // print greeting message
        greet();

        // read user input
        String userInput = scan.nextLine();

        while (!userInput.equals(EXIT)) {
            if (userInput.equals("list")) {
                tasks.displayAllItems();
            } else if (userInput.startsWith("done")) {
                // get task number
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                Task task = tasks.getTask(taskNumber - 1);
                // mark done as done
                task.markAsDone();
                display("Nice! This task is marked as done: \n" + "      " + task);
            } else if (userInput.startsWith("todo")) {
                // get task description
                String desc = userInput.split("todo ")[1];
                // add task to list
                Todo todo = new Todo(desc);
                tasks.add(todo);
                display("Got it. I've added this task: \n" + "      " + todo + "\n    Now you have "
                        + tasks.getLength() + (tasks.getLength() == 1 ? " task" : " tasks") + " in the list.");
            } else if (userInput.startsWith("deadline")) {
                String[] arr = processUserInput(userInput,"deadline ", "/by ");
                Deadline dl = new Deadline(arr[0], arr[1]);
                tasks.add(dl);
                display("Got it. I've added this task: \n" + "      " + dl + "\n    Now you have "
                        + tasks.getLength() + (tasks.getLength() == 1 ? " task" : " tasks") + " in the list.");
            } else if (userInput.startsWith("event")) {
                String[] arr = processUserInput(userInput,"event ", "/at ");
                // add task to list
                Event event = new Event(arr[0], arr[1]);
                tasks.add(event);
                display("Got it. I've added this task: \n" + "      " + event + "\n    Now you have "
                        + tasks.getLength() + (tasks.getLength() == 1 ? " task" : " tasks") + " in the list.");
            }
            userInput = scan.nextLine();
        }

        scan.close();

        // print exit message
        exit();

    }

}
