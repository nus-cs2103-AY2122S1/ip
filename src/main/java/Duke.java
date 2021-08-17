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
        try {
            if (command.toLowerCase().equals("bye")) {
                sayBye();
            } else if (command.toLowerCase().equals("list")) {
                showList();
            } else if (command.toLowerCase().contains("done")) {
                int taskDone = Character.getNumericValue(command.charAt(command.length() - 1)) - 1;
                finishTask(taskDone);
            } else if (command.toLowerCase().contains("todo")) {
                addTodo(command);
            } else if (command.toLowerCase().contains("deadline")) {
                addDeadline(command);
            } else if (command.toLowerCase().contains("event")) {
                addEvent(command);
            } else {
                String errorMessage = "Sorry I do not understand this command \n";
                errorMessage += "Please use one of the following commands: \n";
                errorMessage += "\t list - To list the added tasks so far\n";
                errorMessage += "\t todo {description} - To add a ToDo task\n";
                errorMessage += "\t deadline {description} /by {time} - To add a Deadline task\n";
                errorMessage += "\t event {description} /at {time} - To add an Event task\n";
                errorMessage += "\t done {number} - To mark the indicated task as done\n";
                errorMessage += "\t bye (To exit programme)\n";
                throw new DukeException(errorMessage);
                //otherCommand(command);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            getCommand();
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
        System.out.println("\t Good job! This task has been completed:");
        System.out.println("\t \t" + taskList[taskDone].toString());
        getCommand();
    }

    private static void addTodo(String command) {
        // Take the substring containing the task description of the command
        String description = command.substring(5);
        ToDo t = new ToDo(description);

        System.out.println("\t I have added to the list: \n\t \t" + t.toString());
        taskList[counter] = t;
        counter++;
        System.out.println("\t There are " + counter + " items in the list");
        getCommand();
    }

    private static void addDeadline(String command) {
        // Template of command: deadline {description} /by {byWhen}
        // Find the end of description, which is the start of byWhen
        int endOfDescription = command.indexOf("/");

        // Find substring containing task description
        String description = command.substring(9, endOfDescription);

        String byWhen = command.substring(endOfDescription + 1);
        Deadline t = new Deadline(description, byWhen);

        System.out.println("\t I have added to the list: \n\t \t" + t.toString());
        taskList[counter] = t;
        counter++;
        System.out.println("\t There are " + counter + " items in the list");
        getCommand();
    }

    private static void addEvent(String command) {
        // Template of command: event {description} /by {startTime}
        // Find the end of description, which is the start of startTime
        int endOfDescription = command.indexOf("/");

        // Find substring containing task description
        String description = command.substring(6, endOfDescription);

        String startTime = command.substring(endOfDescription + 1);
        Event t = new Event(description, startTime);

        System.out.println("\t I have added to the list: \n\t \t" + t.toString());
        taskList[counter] = t;
        counter++;
        System.out.println("\t There are " + counter + " items in the list");
        getCommand();
    }

    // Function for when user command is something else
    private static void otherCommand(String command) {
        // User command is something else, add command to command list and continue asking for commands
        System.out.println(command);
        getCommand();
    }
}
