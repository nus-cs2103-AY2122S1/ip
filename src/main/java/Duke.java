import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Duke {

//    private static String logo = " ____        _        \n"
//            + "|  _ \\ _   _| | _____ \n"
//            + "| | | | | | | |/ / _ \\\n"
//            + "| |_| | |_| |   <  __/\n"
//            + "|____/ \\__,_|_|\\_\\___|\n";


    /**
     * Wraps a given message above and below with lines and prints it
     *
     * @param msg the message to wrap and print
     */
    private static void wrapPrint(String msg) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(msg);
        System.out.println(line);
    }

    /**
     * Lists the elements of a given ArrayList of Tasks and enumerates them
     *
     * @param lst the given array of Tasks
     * @return a String that looks like an ordered list of Tasks
     */
    private static String listTaskArr(ArrayList<Task> lst) {
        StringBuilder res = new StringBuilder();
        ListIterator<Task> iter = lst.listIterator();

        while (iter.hasNext()) {
            res.append(iter.nextIndex() + 1).append(".").append(iter.next());
        }
        return res.toString();
    }

    public static void main(String[] args) {
        // initialise scanner and String array
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        String msg = "";

        // Greet
        wrapPrint("Hello! I'm Bob\nWhat can I do for you?");

        // Add, List and Mark done
        String input = sc.nextLine();
        while (!input.equals("bye")){
            try {
                if (input.equals("list")) {
                    msg = listTaskArr(taskList);
                } else if (input.startsWith("done")) {
                    // input validation
                    int num = Integer.parseInt(input.substring(5));
                    if (num < 1 || num > taskList.size()) throw new DukeException("you typed an invalid number: " + num);

                    taskList.get(num - 1).markDone();
                    msg = "Nice! I've marked this task as done:\n  " + taskList.get(num - 1);
                } else if (input.startsWith("todo")) {
                    // input validation
                    if (input.length() < 5 || input.substring(5).isBlank()) throw new DukeException("the description of the todo cannot be empty!");

                    ToDo newTodo = new ToDo(input.substring(5));
                    taskList.add(newTodo);
                    msg = "Got it. I've added this task:\n  " + newTodo + "\nNow you have " + taskList.size();
                    msg = taskList.size() == 1 ? msg + " task in the list" : msg + " tasks in the list.";
                } else if (input.startsWith("deadline")) {
                    // input validation
                    if (input.length() < 9 || input.substring(9).isBlank()) throw new DukeException("the description of the deadline cannot be empty!");
                    if (!input.contains("/by")) throw new DukeException("you are missing the /by keyword");

                    Deadline newDeadline = new Deadline(input.substring(9));
                    taskList.add(newDeadline);
                    msg = "Got it. I've added this task:\n  " + newDeadline + "\nNow you have " + taskList.size();
                    msg = taskList.size() == 1 ? msg + " task in the list" : msg + " tasks in the list.";
                } else if (input.startsWith("event")) {
                    // input validation
                    if (input.length() < 6 || input.substring(6).isBlank()) throw new DukeException("the description of the event cannot be empty!");
                    if (!input.contains("/at")) throw new DukeException("you are missing the /at keyword");

                    Event newEvent = new Event(input.substring(6));
                    taskList.add(newEvent);
                    msg = "Got it. I've added this task:\n  " + newEvent + "\nNow you have " + taskList.size();
                    msg = taskList.size() == 1 ? msg + " task in the list" : msg + " tasks in the list.";
                } else {
                    throw new DukeException("you typed in something i don't recognise");
                }
                wrapPrint(msg);
            } catch (NumberFormatException e) {
                wrapPrint("oh no! you typed in an invalid input! " + e.getMessage());
            } catch (DukeException e) {
                wrapPrint(e.getMessage());
            }
            input = sc.nextLine();
        }

        // Exit
        wrapPrint("Bye. Hope to see you again soon!");
    }
}
