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
     * Lists the elements of a given array of Tasks and enumerates them
     *
     * @param arr the given array of Tasks
     * @return a String that looks like an ordered list of Tasks
     */
    private static String listTaskArr(Task[] arr) {
        StringBuilder res = new StringBuilder();
        int i = 0;

        while (arr[i] != null) {
            res.append(i + 1).append(".").append(arr[i]);
            if (i < arr.length - 1 && arr[i + 1] != null) {
                res.append("\n");
            }
            i++;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        // initialise scanner and String array
        Scanner sc = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int idx = 0; // pointer for string array
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
                    int num = Integer.parseInt(input.substring(5));
                    if (num < 1 || num > idx) throw new DukeException("you typed an invalid number: " + num);
                    taskList[num - 1].markDone();
                    msg = "Nice! I've marked this task as done:\n  " + taskList[num - 1];
                } else if (input.startsWith("todo")) {
                    if (input.length() < 5 || input.substring(5).isBlank()) throw new DukeException("the description of the todo cannot be empty!");
                    taskList[idx++] = new ToDo(input.substring(5));
                    msg = "Got it. I've added this task:\n  " + taskList[idx - 1] + "\nNow you have " + idx;
                    msg = idx == 1 ? msg + " task in the list" : msg + " tasks in the list.";
                } else if (input.startsWith("deadline")) {
                    if (input.length() < 9 || input.substring(9).isBlank()) throw new DukeException("the description of the deadline cannot be empty!");
                    if (input.indexOf("/by") == -1) throw new DukeException("you are missing the /by keyword");
                    taskList[idx++] = new Deadline(input.substring(9));
                    msg = "Got it. I've added this task:\n  " + taskList[idx - 1] + "\nNow you have " + idx;
                    msg = idx == 1 ? msg + " task in the list" : msg + " tasks in the list.";
                } else if (input.startsWith("event")) {
                    if (input.length() < 9 || input.substring(9).isBlank()) throw new DukeException("the description of the event cannot be empty!");
                    if (input.indexOf("/at") == -1) throw new DukeException("you are missing the /at keyword");
                    taskList[idx++] = new Event(input.substring(6));
                    msg = "Got it. I've added this task:\n  " + taskList[idx - 1] + "\nNow you have " + idx;
                    msg = idx == 1 ? msg + " task in the list" : msg + " tasks in the list.";
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
