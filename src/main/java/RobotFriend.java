import java.util.Scanner;

public class RobotFriend {

    private final static String ROBOT_ICON = "[~o_o~]";
    private final static String BYE = "bye";
    private final static String LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private final static String ROBOT_TEXT_SPACE = "         ";

    private static final Task[] list = new Task[100];
    private static int listIndex = 0;

    /**
     * Prints greeting text of the robotFriend.
     */
    private static void greet() {
        System.out.println(LINE);
        System.out.println(ROBOT_ICON + ": Hello I am your RobotFriend.\n" + ROBOT_TEXT_SPACE + "How can i help you?");
        System.out.println(ROBOT_TEXT_SPACE + "Commands:");
        System.out.println(ROBOT_TEXT_SPACE + "bye: terminate session:");
        System.out.println(ROBOT_TEXT_SPACE + "list: view all tasks in the list.");
        System.out.println(ROBOT_TEXT_SPACE + "done i: mark task number i as complete and view the task:");
        System.out.println(LINE);
    }

    /**
     * Echos the user inputted String in a robot way.
     *
     * @param userInput user inputted String
     */
    private static void echo(String userInput) {
        System.out.println(LINE);
        System.out.println(ROBOT_ICON + ": " + userInput);
        System.out.println(LINE);
    }

    /**
     * Prints the exiting text in a robot way.
     */
    private static void bye() {
        System.out.println(LINE);
        System.out.println(ROBOT_ICON + ": " + "Bye~ Hope to see you soon :)");
        System.out.println(LINE);
    }

    /**
     * Adds user inputted String to global list and prints the user added tasks and the current number of tasks.
     *
     * @param userInput user inputted String
     */
    private static void addToList(String userInput) {
        Task newTask;
        String[] tokens = userInput.split(" ");
        System.out.println(tokens[0]);
        switch (tokens[0]) {
            case "todo":
                newTask = new ToDo(userInput.substring("todo".length() + 1));
                break;
            case "deadline":
                int indexOfBy = userInput.indexOf("/by");
                newTask = new Deadline(userInput.substring("deadline".length() + 1, indexOfBy - 1),
                        userInput.substring(indexOfBy + "/by".length() + 1));
                break;
            case "event":
                int indexOfAt = userInput.indexOf("/at");
                newTask = new Event(userInput.substring("event".length() + 1, indexOfAt - 1),
                        userInput.substring(indexOfAt + "/by".length() + 1));
                break;
            default:
                newTask = new Task(userInput);

        }
        list[listIndex] = newTask;
        listIndex++;
        System.out.println(LINE);
        System.out.println(ROBOT_ICON + ": " + "You have added this following task to the list:");
        System.out.println(newTask.toString());
        System.out.println(ROBOT_TEXT_SPACE + "Now you have " + listIndex + " tasks!");
        System.out.println(LINE);
    }

    /**
     * Prints all the tasks in the list
     */
    private static void printList() {
        System.out.println(LINE);
        System.out.println(ROBOT_ICON + ": " + "Your list contains the following task/s:");
        for (int i = 0; i < listIndex; i++) {
            System.out.println((i + 1) + ". " + list[i].toString());
        }
        System.out.println(LINE);
    }

    /**
     * Marks the task as complete and prints out the task
     *
     * @param taskNumber the index of the task in list
     */
    private static void completeAndPrintTask(int taskNumber) {
        System.out.println(LINE);
        System.out.println(ROBOT_ICON + ": " + "You have completed the following task:");
        list[taskNumber - 1].completeTask();
        System.out.println(list[taskNumber - 1].toString());
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            String[] tokens = userInput.split(" ");
            boolean isTaskCompleteCommand = tokens.length == 2 && tokens[0].equals("done");
            if (userInput.equals(BYE)) {
                bye();
                break;
            } else if (userInput.equals("list")) {
                printList();
            } else if (isTaskCompleteCommand) {
                completeAndPrintTask(Integer.parseInt(tokens[1]));
            } else {
                addToList(userInput);
            }
        }
    }
}


