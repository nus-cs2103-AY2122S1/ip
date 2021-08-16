import java.util.Locale;

public class Duke {

    public static boolean active;
    public static String startMessage = "Hello! I'm Duke \nWhat can I do for you?";
    public static String endMessage = "Bye! Hope to see you again soon!";
    //public static String[] list = new String[100];
    public static TaskItem[] taskList = new TaskItem[100];
    public static int listIndex = 0;


    public static void awaken() {
        Duke.active = true;

        Duke.sendStartMessage();
        while (active) {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String input = scanner.nextLine();

            if (input.split(" ")[0].equals("done")) {
                String[] splitString = input.split(" ");
                String taskItemNumber = splitString[1];
                Duke.markAsFinished(taskItemNumber);
            } else if (input.equals("list")) {
                Duke.printList();
            } else if (!input.equals("list") && !input.equals("bye")) {
                Duke.addToList(input);
            } else if (input.equals("bye")) {
                Duke.active = false;
                Duke.sendEndMessage();
                break;
            }


        }
    }

    public static void markAsFinished(String taskItemNumber) {
        int taskNumber = Integer.parseInt(taskItemNumber);

        taskList[taskNumber - 1].completeTask();

        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + taskList[taskNumber - 1].describeTaskItem());
        System.out.println("____________________________________________________________");

    }

    public static void addToList(String input) {
        Duke.taskList[Duke.listIndex] = new TaskItem(input);
        Duke.listIndex++;
        System.out.println("____________________________________________________________");
        System.out.println("added: " + input);
        System.out.println("____________________________________________________________");
    }

    public static void printList() {
        int number = 1;
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.length; i++) {
            if (taskList[i] != null) {
                if (!taskList[i].isCompleted()) {
                    System.out.println(number + ".[ ] " + taskList[i].describeTaskItem());
                }

                if (taskList[i].isCompleted()) {
                    System.out.println(number + ".[X] " + taskList[i].describeTaskItem());
                }

                number++;
            }
        }
        System.out.println("____________________________________________________________");
    }

    public static void sendStartMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(startMessage);
        System.out.println("____________________________________________________________");
    }

    public static void sendEndMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(endMessage);
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
    /*
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");


        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String input = "";
        input = scanner.nextLine();

        while (!input.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________");

            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye! Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
        }
     */

        Duke.awaken();
    }
}
