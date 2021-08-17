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

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        while (active) {
            String input = scanner.nextLine();
            if (input.split(" ")[0].equals("done")) {
                String[] splitString = input.split(" ");
                String taskItemNumber = splitString[1];
                Duke.markAsFinished(taskItemNumber);
            }   else if (input.split(" ")[0].equals("todo")) {
                input = input.replace(input.split(" ")[0], "");
                ToDo newToDo = new ToDo(input);
                Duke.addToList(newToDo);
            }   else if (input.split(" ")[0].equals("deadline")) {
                input = input.replace(input.split(" ")[0], "");
                String by = input.split("/")[1].split(" ", 2)[1];
                String description = input.split("/")[0];
                Deadline dead = new Deadline(description, by);
                Duke.addToList(dead);
            }   else if (input.split(" ")[0].equals("event")) {
                input = input.replace(input.split(" ")[0], "");
                String date = input.split("/")[1].split(" ")[1];
                String time = input.split("/")[1].split(" ")[2];
                String description = input.split("/")[0];
                Event someEvent = new Event(description, date, time);
                Duke.addToList(someEvent);
            }   else if (input.equals("list")) {
                Duke.printList();
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
        System.out.println(taskList[taskNumber - 1].toString());
        System.out.println("____________________________________________________________");

    }

    public static void addToList(TaskItem taskItem) {
        Duke.taskList[Duke.listIndex] = taskItem;

        Duke.listIndex++;
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(taskItem.toString());
        if (listIndex == 1) System.out.println("Now you have " + 1 + " task in the list.");
        if (listIndex > 1) System.out.println("Now you have " + (Duke.listIndex) + " tasks in your list.");
        System.out.println("____________________________________________________________");
    }

    public static void printList() {
        int number = 1;
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.length; i++) {
            if (taskList[i] != null) {
//                if (!taskList[i].isCompleted()) {
//                    System.out.println(number + ".[ ] " + taskList[i].toString());
//                }
//
//                if (taskList[i].isCompleted()) {
//                    System.out.println(number + ".[X] " + taskList[i].toString());
//                }

                System.out.println(number + "." + taskList[i].toString());

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
