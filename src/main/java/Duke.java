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
        String res = "";
        int i = 0;

        while (arr[i] != null) {
            res = res + (i + 1) + "." + arr[i];
            if (i < arr.length - 1 && arr[i + 1] != null) {
                res = res + "\n";
            }
            i++;
        }
        return res;
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
            if (input.equals("list")) {
                msg = listTaskArr(taskList);
            } else if (input.startsWith("done")) {
                int num = Integer.parseInt(input.substring(5));
                taskList[num - 1].markDone();
                msg = "Nice! I've marked this task as done:\n  " + taskList[num - 1];
            } else if (input.startsWith("todo")) {
                taskList[idx++] = new ToDo(input.substring(5));
                msg = "Got it. I've added this task: \n  " + taskList[idx - 1] + "\nNow you have " + idx;
                msg = idx == 1 ? msg + " task in the list" : msg + " tasks in the list.";
            } else if (input.startsWith("deadline")) {
                taskList[idx++] = new Deadline(input.substring(9));
                msg = "Got it. I've added this task: \n  " + taskList[idx - 1] + "\nNow you have " + idx;
                msg = idx == 1 ? msg + " task in the list" : msg + " tasks in the list.";
            } else if (input.startsWith("event")) {
                taskList[idx++] = new Event(input.substring(6));
                msg = "Got it. I've added this task: \n  " + taskList[idx - 1] + "\nNow you have " + idx;
                msg = idx == 1 ? msg + " task in the list" : msg + " tasks in the list.";
            } else {
                msg = "sorry i don't know what this means: " + input;
            }
            wrapPrint(msg);
            msg = "";
            input = sc.nextLine();
        }

        // Exit
        wrapPrint("Bye. Hope to see you again soon!");
    }
}
