import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> toDoList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        start();
    }

    public static void start() {
        System.out.println("\nInput:");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        try {
            parseInput(userInput);
        }
        catch(DukeException e) {
            reply(e.getMessage());
        }
        finally {
            start();
        }
    }

    public static void reply(String dukeReply) {
        String lines = "--------------------------------------------------------------------------------------------";
        String newString = lines + "\nOutput: \n" + dukeReply + "\n" + lines + "\n";
        System.out.println(newString);
        start();
    }

    public static void parseInput(String userInput) throws DukeException{
        if (userInput.equals("bye")) {
            String lines = "--------------------------------------------------------------------------------------------";
            String newString = lines + "\nOutput: \n" + "Good bye, see you soon!" + "\n" + lines + "\n";
            System.out.println(newString);
            System.exit(0); //Should have no error message, hence 0
        } else if (userInput.equals("list")) {
            if (toDoList.isEmpty()) {
                reply("There are no tasks to complete!");
            } else{
                String toDoListToPrint = "";
                for (int pos = 0; pos < toDoList.size(); pos++) {
                    if (pos == toDoList.size() - 1) {
                        toDoListToPrint = addToStringToPrint(pos, toDoListToPrint);
                    } else {
                        toDoListToPrint = addToStringToPrint(pos, toDoListToPrint) + "\n";
                    }
                }
                reply(toDoListToPrint);
            }
        } else {
            String[] parsedUserInput = userInput.split(" ", 2);
            if (parsedUserInput[0].equals("done")) { // Mark task as completed
                int taskDone = Integer.parseInt(parsedUserInput[1]) - 1;
                toDoList.get(taskDone).markAsCompleted();
                reply("Nice! I've marked this task as done: \n" + addToStringToPrint(taskDone, ""));
            } else if (parsedUserInput[0].equals("delete")) { // todo
                int taskDone = Integer.parseInt(parsedUserInput[1]) - 1;
            } else if (parsedUserInput[0].equals("todo")) {
                if (parsedUserInput.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                Task newTask = new ToDo(parsedUserInput[1]);
                addTaskToList(newTask, parsedUserInput[1]);
            } else if (parsedUserInput[0].equals("deadline")) { // Add deadline
                if (parsedUserInput.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                } else if (!parsedUserInput[1].contains("/by")) {
                    throw new DukeException("Please include the keyword \"/by\" if you want to add a deadline.");
                } else {
                    String[] parsedDeadlineInput = parsedUserInput[1].split("/by", 2);
                    Task newTask = new Deadline(parsedDeadlineInput[0], parsedDeadlineInput[1]);
                    addTaskToList(newTask, parsedUserInput[1]);
                }
            } else if (parsedUserInput[0].equals("event")) { // Add event
                if (parsedUserInput.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                } else if (!parsedUserInput[1].contains("/at")) {
                    throw new DukeException("Please include the keyword \"/at\" if you want to add an event.");
                } else {
                    String[] parsedDeadlineInput = parsedUserInput[1].split("/at", 2);
                    Task newTask = new Event(parsedDeadlineInput[0], parsedDeadlineInput[1]);
                    addTaskToList(newTask, parsedUserInput[1]);
                }
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void addTaskToList(Task newTask, String taskName) {
        toDoList.add(newTask);
        reply("Got it. I've added this task: \n" + newTask.toString() +
                "     \nNow you have " + toDoList.size() + " tasks in the list.");
    }

    public static String addToStringToPrint(int pos, String toDoListToPrint) {
        Task currentTask = toDoList.get(pos);
        return toDoListToPrint + (pos + 1) + "." + currentTask.toString();
    }
}
