import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();
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
                finishTask(command);
            } else if (command.toLowerCase().contains("delete")) {
                 deleteTask(command);
            } else if (command.toLowerCase().contains("todo")) {
                addTodo(command);
            } else if (command.toLowerCase().contains("deadline")) {
                addDeadline(command);
            } else if (command.toLowerCase().contains("event")) {
                addEvent(command);
            } else {
                String errorMessage = "\t Sorry I do not understand this command \n";
                errorMessage += "\t Please use one of the following commands: \n";
                errorMessage += "\t \t list - To list the added tasks so far\n";
                errorMessage += "\t \t todo {description} - To add a ToDo task\n";
                errorMessage += "\t \t deadline {description} /by {time} - To add a Deadline task\n";
                errorMessage += "\t \t event {description} /at {time} - To add an Event task\n";
                errorMessage += "\t \t done {number} - To mark the indicated task as done\n";
                errorMessage += "\t \t delete {number} - To delete the indicated task\n";
                errorMessage += "\t \t bye (To exit programme)\n";
                throw new DukeException(errorMessage);
                //otherCommand(command);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            getCommand();
        }
    }

    private static void sayBye() {
        // User command is bye, print bye message and programme stops
        System.out.println("\t Bye. Hope to see you again soon");
    }

    private static void showList() {
        // User command is list, print current list of commands and continues asking for commands
        if (counter == 0) {
            System.out.println("Nothing has been added to the list");
        }
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        getCommand();
    }

    private static void finishTask(String command) throws DukeException {
        if (command.length() < 5 || isValidString(command.substring(5))) {
            String errorMessage = "\t Invalid command, please key in number of the task to be done as follows:\n";
            errorMessage += "\t \t done {number}";
            throw new DukeException(errorMessage);
        }

        int taskDone = Integer.parseInt(command.substring(5)) - 1;
        if (taskDone < 0 || taskDone > counter - 1) {
            String errorMessage = "\t List number out of range, please enter a valid number\n";
            throw new DukeException(errorMessage);
        }

        taskList.get(taskDone).markAsDone();
        System.out.println("\t Good job! This task has been completed:");
        System.out.println("\t \t" + taskList.get(taskDone).toString());
        getCommand();
    }

    private static void deleteTask(String command) throws DukeException {
        if (command.length() < 7 || isValidString(command.substring(7))) {
            String errorMessage = "\t Invalid command, please key in number of the task to be deleted as follows:\n";
            errorMessage += "\t \t delete {number}";
            throw new DukeException(errorMessage);
        }

        int deleteTask = Integer.parseInt(command.substring(7)) - 1;
        if (deleteTask < 0 || deleteTask > counter - 1) {
            String errorMessage = "\t List number out of range, please enter a valid number\n";
            throw new DukeException(errorMessage);
        }

        System.out.println("\t Noted. The task has been removed!");
        System.out.println("\t \t" + taskList.get(deleteTask).toString());
        taskList.remove(deleteTask);
        counter--;
        getCommand();
    }

    private static void addTodo(String command) throws DukeException {
        // Take the substring containing the task description of the command
        String errorMessage = "\t Invalid command, description is required, please follow template:\n";
        errorMessage += "\t \t todo {description}";

        if (command.length() < 5) {
            throw new DukeException(errorMessage);
        }

        String description = command.substring(5);
        if (description.isEmpty() || description.isBlank()) {
            throw new DukeException(errorMessage);
        }

        ToDo t = new ToDo(description);

        System.out.println("\t I have added to the list: \n\t \t" + t.toString());
        taskList.add(t);
        counter++;
        System.out.println("\t There are " + counter + " items in the list");
        getCommand();
    }

    private static void addDeadline(String command) throws DukeException {
        // Template of command: deadline {description} /by {time}
        // Find the end of description, which is the start of byWhen
        String errorMessage = "\t Invalid command, description and time is required, please follow template:\n";
        errorMessage += "\t \t deadline {description} /by {time}";

        int endOfDescription = command.indexOf("/");
        if (endOfDescription == -1 || command.length() < 9) {
            throw new DukeException(errorMessage);
        }

        // Find substring containing task description
        String description = command.substring(9, endOfDescription);
        if (description.isEmpty() || description.isBlank()) {
            throw new DukeException(errorMessage);
        }

        String time = command.substring(endOfDescription + 1);
        if (isValidString(time)  || !time.substring(0,2).equals("by") || isValidString(time.substring(2))) {
            throw new DukeException(errorMessage);
        }

        Deadline t = new Deadline(description, time);

        System.out.println("\t I have added to the list: \n\t \t" + t.toString());
        taskList.add(t);
        counter++;
        System.out.println("\t There are " + counter + " items in the list");
        getCommand();
    }

    private static void addEvent(String command) throws DukeException{
        // Template of command: event {description} /by {time}
        // Find the end of description, which is the start of time
        String errorMessage = "\t Invalid command, description and time is required, please follow template:\n";
        errorMessage += "\t \t event {description} /at {time}";

        int endOfDescription = command.indexOf("/");
        if (endOfDescription == -1 || command.length() < 6) {
            throw new DukeException(errorMessage);
        }

        // Find substring containing task description
        String description = command.substring(6, endOfDescription);
        if (description.isEmpty() || description.isBlank()) {
            throw new DukeException(errorMessage);
        }

        String time = command.substring(endOfDescription + 1);
        if (isValidString(time) || !time.substring(0,2).equals("at") || isValidString(time.substring(2))) {
            throw new DukeException(errorMessage);
        }

        Event t = new Event(description, time);

        System.out.println("\t I have added to the list: \n\t \t" + t.toString());
        taskList.add(t);
        counter++;
        System.out.println("\t There are " + counter + " items in the list");
        getCommand();
    }

    private static boolean isValidString(String s) {
        return s.isBlank() || s.isEmpty();
    }
}
