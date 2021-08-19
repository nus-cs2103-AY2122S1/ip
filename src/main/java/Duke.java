import java.util.Scanner;


public class Duke {

    public static Task[] taskList = new Task[100];
    public static int num = 0;

    //This function prints the horizontal line
    public static void printHorizontalLine() {
        System.out.println("___________________________________________________________________________");
    }

    //This function prints the welcome message
    public static void printWelcomeMessage() {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    //This functions prints the acknowledgement message after a new task has been added
    public static void printAddTask() {
        printHorizontalLine();
        System.out.print("Got it. I've added this task:\n  ");
    }

    //This functions prints the number of tasks
    public static void printNumOfTasks() {
        System.out.println("Now you have " + num + " tasks in the list.");
        printHorizontalLine();
    }

    //This function prints errors statements
    public static void printError() {
        printHorizontalLine();
        System.out.println("Command not recognized");
        printHorizontalLine();
    }

    //This function prints the bye message
    public static void printByeMessage() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    //This function is used to acknowledge input
    public static void printAcknowledgement() {
        printAddTask();
        taskList[num - 1].printTask();
        printNumOfTasks();
    }

    //This function is used to add a to do task to the list of tasks
    public static void addTodo(String str) {
        taskList[num] = new Todo(str);
        num++;
        printAcknowledgement();
    }

    //This function is used to add a Deadline task to the list of tasks
    public static void addDeadline(String str) {
        String description = str.replaceAll(RegexType.GET_DESCRIPTION_REGEX.getRegexType(), "");
        String by = str.replaceAll(RegexType.GET_BY_REGEX.getRegexType(), "");
        taskList[num] = new Deadline(description, by);
        num++;
        printAcknowledgement();
    }

    //This function is used to add an Event task to the list of tasks
    public static void addEvent(String str) {
        String description = str.replaceAll(RegexType.GET_DESCRIPTION_REGEX.getRegexType(), "");
        String at = str.replaceAll(RegexType.GET_AT_REGEX.getRegexType(), "");
        taskList[num] = new Event(description, at);
        num++;
        printAcknowledgement();
    }

    //This function is used to print the list of all the tasks
    public static void printList() {
        printHorizontalLine();
        System.out.println("Here are the tasks in your list");
        for(int i = 0; i < num; i++) {
            System.out.print((i+1) + ".");
            taskList[i].printTask();
        }
        printHorizontalLine();
    }

    //This function is used to mark the task at the given index as done
    public static void markStatusAsDone(int i) {
        if(taskList[i].isDone()) {
            printHorizontalLine();
            System.out.println("The task has already been completed.");
            printHorizontalLine();
            return;
        }
        taskList[i].markAsDone();

        printHorizontalLine();
        System.out.print("Nice! I've marked this task as done:\n  ");
        taskList[i].printTask();
        printHorizontalLine();
    }

    //This function is used to read the input and process the command
    public static void readInput(String str) {
        String command = str.split(" ", 2)[0];
        str = str.replaceAll(RegexType.START_LINE_REGEX.getRegexType() + command + RegexType.SPACE_REGEX.getRegexType(), "");

        if(str.equals(InputType.LIST.getInputType())) {
            printList();
        } else if(command.equals(InputType.DONE.getInputType()) && str.matches(RegexType.DIGITS_REGEX.getRegexType())) {
            int i = Integer.parseInt(str) - 1;
            if(i < num) {
                markStatusAsDone(i);
            } else {
                printError();
            }
        } else if(command.equals(InputType.TODO.getInputType())) {
            addTodo(str);
        } else if(command.equals(InputType.DEADLINE.getInputType()) && str.matches(RegexType.DEADLINE_REGEX.getRegexType())) {
            addDeadline(str);
        } else if (command.equals(InputType.EVENT.getInputType()) && str.matches(RegexType.EVENT_REGEX.getRegexType())) {
            addEvent(str);
        } else {
            printError();
        }
    }



    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner in = new Scanner(System.in);

        String str;



        str = in.nextLine();

        while(!str.equals(String.valueOf(InputType.BYE))) {
            readInput(str);
            str = in.nextLine();
        }



        printByeMessage();
    }
}
