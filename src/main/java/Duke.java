import java.util.Scanner;

public class Duke {
    public static boolean isInt(String str) { // check if a string is integer
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println("*******************************************");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("*******************************************");

        Scanner myScanner= new Scanner(System.in); // open scanner
        boolean hasQuit = false;
        Task[] tasks = new Task[100];
        int numOfTasks = 0;

        while (!hasQuit && myScanner.hasNextLine()) {
            String userInput = myScanner.nextLine();

            if (userInput.equals("bye")) { // if user enters "bye"
                hasQuit = true;
                System.out.println("Bye. Hope to see you again soon!");
            } else if (userInput.equals("list")) { // if user enters "list"
                for (int i = 0; i < numOfTasks; i++) {
                    Task curr = tasks[i];
                    int taskNum = i + 1;
                    System.out.println(taskNum + "." + curr.toString());
                }
            } else if (userInput.length() > 5
                    && userInput.substring(0, 4).equals("done")
                    && isInt(userInput.substring(5))
            ) { // if user wants to mark a task as done
                int taskNum = Integer.parseInt(userInput.substring(5));

                if (taskNum > numOfTasks || taskNum < 1) {
                    System.out.println("There is no task number " + taskNum);
                } else {
                    Task curr = tasks[taskNum - 1];
                    curr.markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n  "
                            + curr.toString());
                }
            } else if (userInput.length() > 5
                    && userInput.substring(0, 5).equals("todo ")
            ) {
                Task curr = new ToDo(userInput.substring(5));
                tasks[numOfTasks] = curr;
                numOfTasks += 1;
                System.out.println("Got it. I've added this task:\n  "
                        + curr.toString() + "\nNow you have " + numOfTasks
                        + " task(s) in the list.");
            } else if (userInput.length() >= 13
                    && userInput.substring(0, 6).equals("event ")
            ) {
                int stopAt = 7;
                while (stopAt + 5 < userInput.length() && !userInput.substring(stopAt, stopAt + 5).equals(" /at ")) {
                    stopAt += 1;
                }

                if (!userInput.substring(stopAt, stopAt + 5).equals(" /at ")) {
                    System.out.println("Wrong input!");
                } else {
                    Task curr = new Event(userInput.substring(6, stopAt), userInput.substring(stopAt + 5));
                    tasks[numOfTasks] = curr;
                    numOfTasks += 1;
                    System.out.println("Got it. I've added this task:\n  "
                            + curr.toString() + "\nNow you have " + numOfTasks
                            + " task(s) in the list.");
                }
            } else if (userInput.length() >= 16
                    && userInput.substring(0, 9).equals("deadline ")
            ) {
                int stopAt = 10;
                while (stopAt + 5 < userInput.length() && !userInput.substring(stopAt, stopAt + 5).equals(" /by ")) {
                    stopAt += 1;
                }

                if (!userInput.substring(stopAt, stopAt + 5).equals(" /by ")) {
                    System.out.println("Wrong input!");
                } else {
                    Task curr = new Deadline(userInput.substring(9, stopAt), userInput.substring(stopAt + 5));
                    tasks[numOfTasks] = curr;
                    numOfTasks += 1;
                    System.out.println("Got it. I've added this task:\n  "
                            + curr.toString() + "\nNow you have " + numOfTasks
                            + " task(s) in the list.");
                }
            } else { // any other input from user
                System.out.println("Wrong input");
            }
            System.out.println("*******************************************");
        }
        myScanner.close(); // close scanner
    }
}

