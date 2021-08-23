import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String getCommand(String input) {
        String[] strArr = input.split(" ", 2);
        return strArr[0];
    }

    private static int taskNumber(String input) throws MissingTaskNumberException {
        String[] strArr = input.split(" ", 2);
        if (strArr.length < 2) {
            System.out.println("---------------------------------------------");
            System.out.println("OOPS!!! To delete a task, the task number must be stated.");
            System.out.println("---------------------------------------------");
            throw new MissingTaskNumberException();
        } else {
            String number = strArr[1];
            return Integer.parseInt(number);
        }
    }

    private static String getDescription(String input) throws MissingDescriptionException {
        String[] strArr = input.split(" ", 2);
        if (strArr.length < 2) {
            System.out.println("---------------------------------------------");
            switch (strArr[0]) {
                case "todo":
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    break;
                case "deadline":
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                    break;
                case "event":
                    System.out.println("OOPS!!! The description of an event cannot be empty.");
                    break;
            }
            System.out.println("---------------------------------------------");
            throw new MissingDescriptionException();
        } else {
            return strArr[1];
        }
    }

    private static void completeTask(String input, ArrayList<Task> arr) throws MissingTaskNumberException {
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
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < arr.size(); i++) {
            int j = i + 1;
            System.out.println("     " + j + ". ["
                    + arr.get(i).getTaskType() + "]"
                    + "[" + arr.get(i).getStatusIcon() + "] "
                    + arr.get(i).description);
        }
        System.out.println("---------------------------------------------");
    }
    
    private static Task identifyType(String input) throws Exception {
        String command = getCommand(input);
        String description = getDescription(input);

        switch (command) {
            case "todo":
                return new Todo(description);
            case "deadline":
                String deadlineDescription = Deadline.getDeadlineDescription(description);
                Deadline deadline = new Deadline(deadlineDescription, Deadline.getDueDate(input));
                return deadline;
            case "event":
                String eventDescription = Event.getEventDescription(description);
                Event event = new Event(eventDescription, Event.getEventDetails(input));
                return event;
            default:
                return new Task("NA");
        }
    }

    private static void deleteTask(int taskID, ArrayList<Task> arr) throws IllegalArgumentException{
        if (taskID <=0 || taskID > arr.size()) {
            System.out.println("---------------------------------------------");
            System.out.println("No such task exists.");
            System.out.println("---------------------------------------------");
            throw new IllegalArgumentException();
        } else {
            System.out.println("---------------------------------------------");
            System.out.println("Noted I've removed this task:");
            System.out.println(arr.get(taskID - 1).toString());
            arr.remove(taskID - 1);
            System.out.println("Now you have " + arr.size() + " task(s) in the list.");
            System.out.println("---------------------------------------------");
        }
    }

    public static void main(String[] args) throws Exception {
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

        Scanner input = new Scanner (System.in);

        while (true) {
            String action = input.nextLine();

            if (getCommand(action).equals("done")) { //mark task as done
                Duke.completeTask(action, toDoList);
            } else if (getCommand(action).equals("todo")
                    || getCommand(action).equals("deadline")
                    || getCommand(action).equals("event")) { // add task to to-do list
                Duke.addTask(identifyType(action), toDoList);
            } else if (action.equals("list")) { // list all items
                Duke.listItems(toDoList);
            } else if (action.equals("bye")) { // exit
                System.out.println("---------------------------------------------\n"
                        + "     Bye. Hope to see you again soon!" + "\n"
                        + "---------------------------------------------");
                break;
            } else if (getCommand(action).equals("delete")) { // delete task
                deleteTask(taskNumber(action), toDoList);
            } else { // if there is an invalid input
                System.out.println("-------------------------------------------------------\n"
                        + "OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n"
                        + "-------------------------------------------------------");
                throw new IllegalArgumentException();
            }
        }
        
    }
}
