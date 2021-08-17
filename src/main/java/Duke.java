import java.util.Scanner;

public class Duke {
    private static Task[] taskList = new Task[100];
    private static int counter = 0;

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\t Hello! I'm Duke");
        getCommand();
    }

    private static void getCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t What would you like me to do?\n");
        String command = scanner.nextLine();

        // Checks if user commands for specific commands
        if (command.toLowerCase().equals("bye")) {
            sayBye();
        } else if (command.toLowerCase().equals("list")) {
            showList();
        } else if(command.toLowerCase().contains("done ")) {
            int taskDone = Character.getNumericValue(command.charAt(command.length() - 1)) - 1;
            finishTask(taskDone);
        } else {
            addTask(command);
        }
    }

    // Function for when user inputs "bye"
    private static void sayBye() {
        // User command is bye, print bye message and programme stops
        System.out.println("\t Bye. Hope to see you again soon");
    }

    // Function for when user inputs "list"
    private static void showList() {
        // User command is list, print current list of commands and continues asking for commands
        if (counter == 0) {
            System.out.println("Nothing has been added to the list");
        }
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + ". " + taskList[i]);
        }
        getCommand();
    }

    // Function for when user inputs "done"
    private static void finishTask(int taskDone) {
        taskList[taskDone].markAsDone();
        System.out.println("Good job! This task has been completed:\n");
        System.out.println(taskList[taskDone].toString());
        getCommand();
    }

    // Function for when user command is something else
    private static void addTask(String command) {
        // User command is something else, add command to command list and continue asking for commands
        System.out.println("\t added: " + command);
        Task t = new Task(command);
        taskList[counter] = t;
        counter++;
        getCommand();
    }
}
