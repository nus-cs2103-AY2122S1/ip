import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        level6DukeBot();
    }

    /**
     * Duke now recognised to dos, events and deadlines and adds them to the list accordingly.
     * Any errors are now handled by Duke also.
     * Inputs are taken by a scanner from the user's keyboard.
     */
    public static void level6DukeBot() {

        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println("-----------------------------------------");
        System.out.println(" Hello! I am Duke");
        System.out.println(" What can I do for you?");
        System.out.println("-----------------------------------------");
        System.out.println();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            String[] inputValue = command.split(" ");
            System.out.println("    -----------------------------------------");

            if (command.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    -----------------------------------------");
                break;
            } else if (command.equals("list")) {
                getList(taskList);
            } else if (inputValue[0].equals("done") && inputValue.length == 2) {
                //treat as unknown command if there is more than 1 number after "done".
                done(taskList, inputValue);
            } else if (inputValue[0].equals("delete") && inputValue.length == 2) {
                //treat as unknown command if there is more than 1 number after "delete".
                delete(taskList, inputValue);
            } else if (inputValue[0].equals("deadline")) {
                deadline(taskList, inputValue, command);
            } else if (inputValue[0].equals("event")) {
                event(taskList, inputValue, command);
            } else if (inputValue[0].equals("todo")) {
               toDo(taskList, inputValue, command);
            } else {
                System.out.println("     OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("    -----------------------------------------");
            System.out.println();
        }
        sc.close();
    }

    /**
     * This method allows Duke to store tasks entered by the user and display them back to the user when requested.
     * @param taskList An ArrayList of type Task used to contain the user's tasks.
     */
    public static void getList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) { //if list empty
            System.out.println("    There are no tasks in your list");
        } else { //if list !empty
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                int currNum = i + 1;
                Task currTask = taskList.get(i);
                System.out.println("     " + currNum + ". " + currTask.toString());
            }
        }
    }

    /**
     * This method gives Duke the ability to mark tasks as done.
     * @param taskList An ArrayList of type Task used to contain the user's tasks.
     * @param inputValue A String[] formed when the user gives an input to Duke.
     */
    public static void done(ArrayList<Task> taskList, String[] inputValue) {
        try {
            int idx = Integer.parseInt(inputValue[1]);
            Task taskToComplete = taskList.get(idx - 1);
            if (taskToComplete.getIsDone()) { //if task is already completed
                System.out.println("     I have already marked this task as completed!");
            } else {
                taskToComplete.setIsDone(true);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + taskToComplete.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("     OOPS!!! Please ensure a number is entered after done (eg: done 2)");
        } catch (IndexOutOfBoundsException e) {
           if (Integer.parseInt(inputValue[1]) <= 1) {
                System.out.println("     OOPS!!! You do not have any task in the list");
            } else {
               System.out.println("     OOPS!!! You do not have " + inputValue[1] + " tasks in the list");
            }
        }
    }

    /**
     * This method gives Duke the ability to delete tasks.
     * @param taskList An ArrayList of type Task used to contain the user's tasks.
     * @param inputValue A String[] formed when the user gives an input to Duke.
     */
    public static void delete(ArrayList<Task> taskList, String[] inputValue) {
        try {
            int idx = Integer.parseInt(inputValue[1]);
            Task taskToDelete = taskList.remove(idx -1);
            System.out.println("     Noted. I've removed this task:");
            System.out.println("       " + taskToDelete.toString());
            System.out.println("     Now you have " + taskList.size()
                    + (taskList.size() == 1 ? " task" : " tasks") + " in the list.");
        } catch (NumberFormatException e) {
            System.out.println("     OOPS!!! Please ensure a number is entered after delete (eg: delete 2)");
        } catch (IndexOutOfBoundsException e) {
            if (Integer.parseInt(inputValue[1]) <= 1) {
                System.out.println("     OOPS!!! You do not have any task in the list");
            } else {
                System.out.println("     OOPS!!! You do not have " + inputValue[1] + " tasks in the list");
            }
        }
    }

    /**
     * This method gives Duke the ability to track Deadlines (a type of Task).
     * @param taskList An ArrayList of type Task used to contain the user's tasks.
     * @param inputValue A String[] formed when the user gives an input to Duke.
     * @param command An input of type String that the user gave to Duke.
     */
    public static void deadline(ArrayList<Task> taskList, String[] inputValue, String command) {
        if (inputValue.length == 1) { //first check if deadline is empty
            System.out.println("     OOPS!!! The description and date/time of a deadline cannot be empty.");
        } else if (!command.contains("/by")) { //check for date separator
            System.out.println("     Invalid deadline format! " +
                    "Please ensure you specify your date and time after a /by " +
                    "Eg: deadline Submit Assignment /by Thursday 2359");
        } else {
            int dateTimeIndex = command.indexOf("/");
            String description = command.substring(inputValue[0].length() + 1, dateTimeIndex).strip();
            String dateTime = command.substring(dateTimeIndex + 3).strip();

            if (description.equals("") || dateTime.equals("")) { //catches the case if users just type "deadline /by"
                System.out.println("     OOPS!!! The description and date/time of a deadline cannot be empty.");
            } else {
                Task deadline = new Deadline(description, dateTime);
                taskList.add(deadline);
                System.out.println("     Got it. I have added this task:");
                System.out.println("       " + deadline.toString());
                System.out.println("     Now you have " + taskList.size()
                        + (taskList.size() == 1 ? " task" : " tasks") + " in the list.");
            }
        }
    }

    /**
     * This method gives Duke the ability to track Events (a type of Task).
     * @param taskList An ArrayList of type Task used to contain the user's tasks.
     * @param inputValue A String[] formed when the user gives an input to Duke.
     * @param command An input of type String that the user gave to Duke.
     */
    public static void event(ArrayList<Task> taskList, String[] inputValue, String command) {
        if (inputValue.length == 1) { //first check for empty events
            System.out.println("     OOPS!!! The description and date/time of an event cannot be empty.");
        } else if (!command.contains("/at")) { //check for date separator
            System.out.println("     Invalid event format! " +
                    "Please ensure you specify your date and time after a /at " +
                    "Eg: event Attend physical lessons /at NUS Monday 0800");
        } else {
            int dateTimeIndex = command.indexOf("/");
            String description = command.substring(inputValue[0].length() + 1, dateTimeIndex).strip();
            String dateTime = command.substring(dateTimeIndex + 3).strip();

            if (description.equals("") || dateTime.equals("")) { //catches the case if user types "event /at"
                System.out.println("     OOPS!!! The description and date/time of a deadline cannot be empty.");
            } else {
                Task event = new Event(description, dateTime);
                taskList.add(event);
                System.out.println("     Got it. I have added this task:");
                System.out.println("       " + event.toString());
                System.out.println("     Now you have " + taskList.size()
                        + (taskList.size() == 1 ? " task" : " tasks") + " in the list.");
            }
        }
    }

    /**
     * This method gives Duke the ability to track ToDos (a type of Task).
     * @param taskList An ArrayList of type Task used to contain the user's tasks.
     * @param inputValue A String[] formed when the user gives an input to Duke.
     * @param command An input of type String that the user gave to Duke.
     */
    public static void toDo(ArrayList<Task> taskList, String[] inputValue, String command) {
        if (inputValue.length == 1) { //catch empty to do
            System.out.println("     OOPS!!! The description of a todo cannot be empty.");
        } else {
            String description = command.substring(inputValue[0].length() + 1);
            Task toDo = new ToDo(description);
            taskList.add(toDo);
            System.out.println("     Got it. I have added this task:");
            System.out.println("       " + toDo.toString());
            System.out.println("     Now you have " + taskList.size()
                    + (taskList.size() == 1 ? " task" : " tasks") + " in the list.");
        }
    }
}
