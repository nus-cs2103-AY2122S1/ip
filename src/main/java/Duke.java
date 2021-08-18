import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String getCommand(String input) {
        String[] strArr = input.split(" ", 2);
        return strArr[0];
    }

    private static int taskNumber(String input) {
        String[] strArr = input.split(" ", 2);
        String number = strArr[1];
        return Integer.parseInt(number);
    }

    private static String getDescription(String input) {
        String[] strArr = input.split(" ", 2);
        return strArr[1];
    }

    private static String getDeadlineDescription(String input) {
        String[] strArr = input.split(" /by", 2);
        return strArr[0] + " (by:" + strArr[1] + ")";
    }

    private static String getEventDescription(String input) {
        String[] strArr = input.split(" /at", 2);
        return strArr[0] + " (at:" + strArr[1] + ")";
    }

    private static String getDueDate(String input) {
        String[] strArr = input.split("/by", 2);
        return strArr[1];
    }

    private static String getEventDetails(String input) {
        String[] strArr = input.split("/at", 2);
        return strArr[1];
    }

    private static void completeTask(String input, ArrayList<Task> arr) {
        System.out.println("---------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        arr.get(taskNumber(input) - 1).markAsDone();
        System.out.println("---------------------------------------------");
    }
    
    private static void addTask(Task newTask, ArrayList<Task> arr) {
        arr.add(newTask);
        System.out.println("---------------------------------------------\n"
                + "     Got it. I've added this task:\n"
                + "[" + newTask.getTaskType() + "] "
                + "[" + newTask.getStatusIcon() + "] "
                + newTask.description + "\n"
                + "Now you have " + arr.size() + " task(s) in the list.\n"
                + "---------------------------------------------");
    }

    private static void listItems(ArrayList<Task> arr) {
        System.out.println("---------------------------------------------");
        for (int i = 0; i < arr.size(); i++) {
            int j = i + 1;
            System.out.println("     " + j + ". ["
                    + arr.get(i).getTaskType() + "]"
                    + "[" + arr.get(i).getStatusIcon() + "] "
                    + arr.get(i).description);
        }
        System.out.println("---------------------------------------------");
    }

    private static Task identifyType(String input) {
        String command = getCommand(input);
        String description = getDescription(input);
        if (command.equals("todo")) {
            Todo todo = new Todo(description);
            return todo;
        } else if (command.equals("deadline")) {
            String deadlineDescription = getDeadlineDescription(description);
            Deadline deadline = new Deadline(deadlineDescription, getDueDate(input));
            return deadline;
        } else if (command.equals("event")) {
            String eventDescription = getEventDescription(description);
            Event event = new Event(eventDescription, getEventDetails(input));
            return event;
        } else {
            return new Task("NA");
        }
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        ArrayList<Task> toDoList = new ArrayList<>();

        System.out.println("---------------------------------------------");
        System.out.println("Hello! I'm Duke!\n" + "What can I do for you?");
        System.out.println("---------------------------------------------");

        while (true) {
            Scanner input = new Scanner (System.in);
            String action = input.nextLine();

            if (getCommand(action).equals("done")) { //mark task as done
                Duke.completeTask(action, toDoList);
            } else if (getCommand(action).equals("todo")
                    || getCommand(action).equals("deadline")
                    || getCommand(action).equals("event")){ // add task to to-do list
                Duke.addTask(identifyType(action), toDoList);
            } else if (action.equals("list")){ // list all items
                Duke.listItems(toDoList);
            } else { // exit
                System.out.println("---------------------------------------------\n"
                        + "     Bye. Hope to see you again soon!" + "\n"
                        + "---------------------------------------------");
                break;
            }
        }
        
    }
}
