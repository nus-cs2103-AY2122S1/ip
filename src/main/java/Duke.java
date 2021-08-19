import java.util.Scanner;

public class Duke {

    //This function prints the horizontal line
    public static void printHorizontalLine() {
        System.out.println("          ___________________________________________________________________________");
    }

    //This function prints the welcome message
    public static void printWelcomeMessage() {
        printHorizontalLine();
        System.out.println("            Hello! I'm Duke");
        System.out.println("            What can I do for you?");
        printHorizontalLine();
    }

    //This function prints the bye message
    public static void printByeMessage() {
        printHorizontalLine();
        System.out.println("            Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    //This function will print the list of items entered by the user
    public static void printListOfItems(int numOfItems, String[] items) {
        printHorizontalLine();
        for (int i = 0; i < numOfItems; i++) {
            System.out.println("            " + (i + 1) + ". " + items[i]);
        }
        printHorizontalLine();

    }

    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner in = new Scanner(System.in);

        String str;

        Task listOfTasks = new Task();

        str = in.nextLine();

        while (!str.equals("bye")) {
            if (str.equals("list")) {
                printHorizontalLine();
                System.out.println("            Here are the tasks in your list:");
                listOfTasks.printTasks();
                printHorizontalLine();
            } else if(str.matches("^done \\d+")){
                int position = Integer.parseInt(str.split(" ")[1]);
                listOfTasks.markStatusAsDone(position);
                printHorizontalLine();
                System.out.println("            Nice! I've marked this task as done: ");
                System.out.println("             [✗] " + listOfTasks.getTasks(position));
                printHorizontalLine();
            } else {
                listOfTasks.addNewTask(str);
                printHorizontalLine();
                System.out.println("            added: " + str);
                printHorizontalLine();
            }

            str = in.nextLine();

        }

        printByeMessage();
    }
}
